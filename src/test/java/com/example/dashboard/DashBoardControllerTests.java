package com.example.dashboard;
import com.example.dashboard.entities.*;
import com.example.dashboard.entities.Subscriber.Location;
import com.example.dashboard.entities.Subscriber.PlanType;
import com.example.dashboard.servcie.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class DashBoardControllerTests {

    @MockBean
    private PlanService planService;

    @MockBean
    private SubscriberService subscriberService;

    @MockBean
    private CallHistoryService callHistoryService;

    @MockBean
    private TextService textService;

    @MockBean
    private DataUsageService dataUsageService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllPlans() throws Exception {
        List<Plan> plans = Arrays.asList(new Plan(), new Plan());
        Mockito.when(planService.getAllPlans()).thenReturn(plans);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/plans")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{}, {}]"));
    }

    @Test
    public void testGetAllSubscribers() throws Exception {
        List<Subscriber> subscribers = Arrays.asList(new Subscriber(), new Subscriber());
        Mockito.when(subscriberService.getAllSubscribers()).thenReturn(subscribers);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{}, {}]"));
    }

    @Test
    public void testGetTotalRevenue() throws Exception {
        Mockito.when(subscriberService.getTotalRevenue()).thenReturn(1000.0);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/revenue")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1000.0"));
    }

    @Test
    public void testGetTotalSubscribers() throws Exception {
        Mockito.when(subscriberService.getTotalSubscribers()).thenReturn(50L);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/total")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("50"));
    }

 

   

    @Test
    public void testGetSubscriberCount() throws Exception {
        Mockito.when(subscriberService.getTotalSubscribers()).thenReturn(75L);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/count")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("75"));
    }

    // @Test
    // public void testGetSubscriberCountByLocation() throws Exception {
    //     Mockito.when(subscriberService.getSubscriberCountByLocation(Location.HYDERABAD)).thenReturn(10L);

    //     mockMvc.perform(MockMvcRequestBuilders.get("/api/count/HYDERABAD")
    //             .contentType(MediaType.APPLICATION_JSON))
    //             .andExpect(MockMvcResultMatchers.status().isOk())
    //             .andExpect(MockMvcResultMatchers.content().string("10"));
    // }

    @Test
    public void testCountPrepaidSubscribersByLocation() throws Exception {
        
        Mockito.when(subscriberService.countPrepaidSubscribersByLocation(Location.HYDERABAD)).thenReturn(5L);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/prepaid/HYDERABAD")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("5"));
    }

    @Test
    public void testCountPostpaidSubscribersByLocation() throws Exception {
        
        Mockito.when(subscriberService.countPostpaidSubscribersByLocation(Location.HYDERABAD)).thenReturn(7L);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/postpaid/HYDERABAD")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("7"));
    }

    

    @Test
    public void testGetPostpaidRevenueForLocation() throws Exception {
        Mockito.when(subscriberService.getPostpaidRevenueForLocation(Location.HYDERABAD)).thenReturn(300.0);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/total/postpaid/HYDERABAD")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("300.0"));
    }

    

    @Test
    public void testGetTotalRevenueForLocation() throws Exception {

        Mockito.when(subscriberService.getTotalRevenueForLocation(Location.HYDERABAD)).thenReturn(100.0);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/total/HYDERABAD")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("100.0"));
    }

    @Test
    public void testCreateCallHistory() throws Exception {
        CallHistory callHistory = new CallHistory();
        Mockito.when(callHistoryService.createCallHistory(Mockito.any(CallHistory.class))).thenReturn(callHistory);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/add/call")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{}"));
    }

    @Test
    public void testCreateText() throws Exception {
        Text text = new Text();
        Mockito.when(textService.createText(Mockito.any(Text.class))).thenReturn(text);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/add/text")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{}"));
    }

    @Test
    public void testGetAllTexts() throws Exception {
        List<Text> texts = Arrays.asList(new Text(), new Text());
        Mockito.when(textService.getAllTexts()).thenReturn(texts);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/text")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{}, {}]"));
    }

    @Test
    public void testCreateDataUsage() throws Exception {
        DataUsage dataUsage = new DataUsage();
        Mockito.when(dataUsageService.createDataUsage(Mockito.any(DataUsage.class))).thenReturn(dataUsage);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/add/data")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{}"));
    }

    @Test
    public void testGetAllDataUsages() throws Exception {
        List<DataUsage> dataUsages = Arrays.asList(new DataUsage(), new DataUsage());
        Mockito.when(dataUsageService.getAllDataUsages()).thenReturn(dataUsages);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/data")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{}, {}]"));
    }
    @Test
    public void testGetPrepaidRevenueForLocation() throws Exception {
      
        Mockito.when(subscriberService.getPrepaidRevenueForLocation(Location.HYDERABAD)).thenReturn(200.0);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/total/prepaid/HYDERABAD")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("200.0"));
    }
   
@Test
public void testGetAllCallHistories() throws Exception {

    List<CallHistory> callHistories = Arrays.asList(new CallHistory(), new CallHistory());
    Mockito.when(callHistoryService.getAllCallHistories()).thenReturn(callHistories);
    String expectedJson = new ObjectMapper().writeValueAsString(callHistories);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/calls")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
            
}


@Test
public void testGetCallHistoryByPhoneNumber() throws Exception {
    String phoneNumber = "123-456-7890";
    List<CallHistory> callHistories = Arrays.asList(new CallHistory(), new CallHistory());
    Mockito.when(callHistoryService.getCallHistoryByPhoneNumber(phoneNumber)).thenReturn(callHistories);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/calls/" + phoneNumber)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json("[{}, {}]"));
}

@Test
public void testGetTextsByPhoneNumber() throws Exception {
    String phoneNumber = "123-456-7890";
    List<Text> texts = Arrays.asList(new Text(), new Text());
    Mockito.when(textService.getTextsByPhoneNumber(phoneNumber)).thenReturn(texts);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/text/" + phoneNumber)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json("[{}, {}]"));
}

@Test
public void testGetDataUsageByPhoneNumber() throws Exception {
    String phoneNumber = "123-456-7890";
    List<DataUsage> dataUsages = Arrays.asList(new DataUsage(), new DataUsage());
    Mockito.when(dataUsageService.getDataUsageByPhoneNumber(phoneNumber)).thenReturn(dataUsages);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/data/" + phoneNumber)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json("[{}, {}]"));
}

@Test
public void testGetTotalConsumedData() throws Exception {
    Mockito.when(dataUsageService.getTotalConsumedData()).thenReturn(500.0);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/total-consumed-data")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("500.0"));
}

@Test
public void testCountSubscribersByPlanName() throws Exception {
    Map<String, Long> subscriberCounts = new HashMap<>();
    subscriberCounts.put("Plan A", 10L);
    subscriberCounts.put("Plan B", 20L);

    Mockito.when(subscriberService.countSubscribersByPlanName()).thenReturn(subscriberCounts);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/countByPlanName")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
}

@Test
public void testGetCallHistoryWithDurations() throws Exception {
    List<CallHistory> callHistoriesWithDurations = Arrays.asList(new CallHistory(), new CallHistory());
    Mockito.when(callHistoryService.getCallHistoryWithDurations()).thenReturn(callHistoriesWithDurations);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/withdurations")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json("[{}, {}]"));
}

@Test
public void testCountCallsInIntervals() throws Exception {
    Map<String, Integer> intervalCounts = new HashMap<>();
    intervalCounts.put("00:00 to 01:00", 5);
    intervalCounts.put("01:00 to 02:00", 8);

    Mockito.when(callHistoryService.countCallsInOneHourIntervals()).thenReturn(intervalCounts);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/count-calls-in-intervals")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
}


@Test
public void testGetAverageCallDuration() throws Exception {
    List<CallHistory> callHistoryList = Arrays.asList(new CallHistory(), new CallHistory());
    double averageDuration = 5.0;
    Mockito.when(callHistoryService.getAllCallHistories()).thenReturn(callHistoryList);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/average-call-duration")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
}

@Test
    public void testGetPlansByType() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/plans/PREPAID"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAddPlan() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/add")
               .contentType("application/json")
               .content("{\"planName\":\"Plan A\",\"planType\":\"PREPAID\"}"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testGetPrepaidSubscribers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/prepaid/TOTAL"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetPostpaidSubscribers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/postpaid/TOTAL"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testGetPrepaidRevenue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/total/prepaid/TOTAL"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetPostpaidRevenue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/total/postpaid/TOTAL"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testCountTextsInIntervals() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/count-texts-in-intervals"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreateSubscriber() throws Exception {
        // Create a Subscriber object
        Subscriber subscriber = new Subscriber();
        subscriber.setName("John");
        subscriber.setPlanType(PlanType.PREPAID);
        subscriber.setLocation(Location.HYDERABAD);

        // Convert the Subscriber object to JSON
        String jsonPayload = objectMapper.writeValueAsString(subscriber);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/add/sub")
               .contentType("application/json")
               .content(jsonPayload))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }



}
