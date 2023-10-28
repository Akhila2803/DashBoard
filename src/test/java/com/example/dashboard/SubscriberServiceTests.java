
package com.example.dashboard;
import com.example.dashboard.servcie.SubscriberService;
import com.example.dashboard.entities.Plan;
import com.example.dashboard.entities.Subscriber;
import com.example.dashboard.repository.SubscriberRepository;
import com.example.dashboard.entities.Subscriber.Location;
import com.example.dashboard.entities.Subscriber.PlanType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.ListCrudRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SubscriberServiceTests {
    @InjectMocks
    private SubscriberService subscriberService;

    @Mock
    private SubscriberRepository subscriberRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
       
            MockitoAnnotations.openMocks(this);
            subscriberService = new SubscriberService(subscriberRepository);
        }
    @Test
    public void testCountSubscribersByPlanName() {
        // Create sample Subscriber objects with different plan names
        Plan plan1 = new Plan();
        plan1.setName("Plan A");

        Plan plan2 = new Plan();
        plan2.setName("Plan B");

        Subscriber subscriber1 = new Subscriber();
        subscriber1.setPlan(plan1);

        Subscriber subscriber2 = new Subscriber();
        subscriber2.setPlan(plan1);

        Subscriber subscriber3 = new Subscriber();
        subscriber3.setPlan(plan2);

        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(subscriber1);
        subscribers.add(subscriber2);
        subscribers.add(subscriber3);

        // Mock the behavior of the subscriberRepository's findAll method
        when(subscriberRepository.findAll()).thenReturn(subscribers);

        // Call the countSubscribersByPlanName method
        Map<String, Long> planCounts = subscriberService.countSubscribersByPlanName();

        // Verify the expected counts
        assertEquals(2, planCounts.size()); // Two different plans
        assertEquals(2L, planCounts.get("Plan A")); // Plan A appears twice
        assertEquals(1L, planCounts.get("Plan B")); // Plan B appears once
    }
    
        @Test
        public void testCreateSubscriber() {
            // Create a sample Subscriber object
            Subscriber subscriber = new Subscriber();
            subscriber.setName("John Doe");
            subscriber.setPhoneNumber("1234567890");
    
            // Mock the behavior of the subscriberRepository's save method
            when(subscriberRepository.save(subscriber)).thenReturn(subscriber);
    
            // Call the createSubscriber method
            Subscriber createdSubscriber = subscriberService.createSubscriber(subscriber);
    
            // Verify that the subscriberRepository's save method was called
            verify(subscriberRepository, times(1)).save(subscriber);
    
            // Assert that the returned Subscriber matches the input Subscriber
            assertEquals(subscriber, createdSubscriber);
        }
    
    @Test
    public void testGetAllSubscribers() {
        // Create a mock SubscriberRepository
        SubscriberRepository subscriberRepository = Mockito.mock(SubscriberRepository.class);

        // Create a SubscriberService instance with the mock repository
        SubscriberService subscriberService = new SubscriberService(subscriberRepository);

        // Create sample subscriber data for testing
        List<Subscriber> sampleSubscribers = new ArrayList<>();
        sampleSubscribers.add(new Subscriber());
        sampleSubscribers.add(new Subscriber());

        // Mock the behavior of the subscriberRepository
        Mockito.when(subscriberRepository.findAll()).thenReturn(sampleSubscribers);

        // Call the service method
        List<Subscriber> result = subscriberService.getAllSubscribers();

        // Verify the result
        assertEquals(2, result.size());
    }

    @Test
    public void testGetPrepaidSubscribers() {
        // Create a mock SubscriberRepository
        SubscriberRepository subscriberRepository = Mockito.mock(SubscriberRepository.class);

        // Create a SubscriberService instance with the mock repository
        SubscriberService subscriberService = new SubscriberService(subscriberRepository);

        // Mock the behavior of the subscriberRepository
        Mockito.when(subscriberRepository.countByPlanType(PlanType.PREPAID)).thenReturn(2L);

        // Call the service method
        long result = subscriberService.getPrepaidSubscribers();

        // Verify the result
        assertEquals(2L, result);
    }

    @Test
    public void testGetPostpaidSubscribers() {
        // Create a mock SubscriberRepository
        SubscriberRepository subscriberRepository = Mockito.mock(SubscriberRepository.class);

        // Create a SubscriberService instance with the mock repository
        SubscriberService subscriberService = new SubscriberService(subscriberRepository);

        // Mock the behavior of the subscriberRepository
        Mockito.when(subscriberRepository.countByPlanType(PlanType.POSTPAID)).thenReturn(3L);

        // Call the service method
        long result = subscriberService.getPostpaidSubscribers();

        // Verify the result
        assertEquals(3L, result);
    }

    @Test
    public void testGetTotalSubscribers() {
        // Create a mock SubscriberRepository
        SubscriberRepository subscriberRepository = Mockito.mock(SubscriberRepository.class);

        // Create a SubscriberService instance with the mock repository
        SubscriberService subscriberService = new SubscriberService(subscriberRepository);

        // Mock the behavior of the subscriberRepository
        Mockito.when(subscriberRepository.countByPlanType(PlanType.PREPAID)).thenReturn(2L);
        Mockito.when(subscriberRepository.countByPlanType(PlanType.POSTPAID)).thenReturn(3L);

        // Call the service method
        long result = subscriberService.getTotalSubscribers();

        // Verify the result
        assertEquals(5L, result);
    }

    @Test
    public void testGetTotalRevenue() {
        // Create a mock SubscriberRepository
        SubscriberRepository subscriberRepository = Mockito.mock(SubscriberRepository.class);

        // Create a SubscriberService instance with the mock repository
        SubscriberService subscriberService = new SubscriberService(subscriberRepository);

        // Create sample subscriber data for testing
        List<Subscriber> sampleSubscribers = new ArrayList<>();
        Subscriber subscriber1 = new Subscriber();
        subscriber1.setPlan(new Plan());
        sampleSubscribers.add(subscriber1);
        Subscriber subscriber2 = new Subscriber();
        subscriber2.setPlan(new Plan());
        sampleSubscribers.add(subscriber2);

        // Mock the behavior of the subscriberRepository
        Mockito.when(subscriberRepository.findAll()).thenReturn(sampleSubscribers);

        // Call the service method
    

    }

    @Test
    public void testGetSubscriberCountByLocation() {
        // Create a mock SubscriberRepository
        SubscriberRepository subscriberRepository = Mockito.mock(SubscriberRepository.class);

        // Create a SubscriberService instance with the mock repository
        SubscriberService subscriberService = new SubscriberService(subscriberRepository);

        // Mock the behavior of the subscriberRepository
        Mockito.when(subscriberRepository.countByLocation(Location.HYDERABAD)).thenReturn(2L);
        Mockito.when(subscriberRepository.countByLocation(Location.BENGALURU)).thenReturn(3L);

        // Call the service method for different locations
        long resultHyderabad = subscriberService.getSubscriberCountByLocation(Location.HYDERABAD);
        long resultBengaluru = subscriberService.getSubscriberCountByLocation(Location.BENGALURU);

        // Verify the results
        assertEquals(2L, resultHyderabad);
        assertEquals(3L, resultBengaluru);
    }

    @Test
    public void testCountPrepaidSubscribersByLocation() {
        // Create a mock SubscriberRepository
        SubscriberRepository subscriberRepository = Mockito.mock(SubscriberRepository.class);

        // Create a SubscriberService instance with the mock repository
        SubscriberService subscriberService = new SubscriberService(subscriberRepository);

        // Mock the behavior of the subscriberRepository
        Mockito.when(subscriberRepository.countByPlanTypeAndLocation(PlanType.PREPAID, Location.HYDERABAD)).thenReturn(2L);
        Mockito.when(subscriberRepository.countByPlanTypeAndLocation(PlanType.PREPAID, Location.BENGALURU)).thenReturn(3L);

        // Call the service method for different locations
        long resultHyderabad = subscriberService.countPrepaidSubscribersByLocation(Location.HYDERABAD);
       // Verify the results
    assertEquals(2L, resultHyderabad);

    }
    
@Test
public void testCountPostpaidSubscribersByLocation() {
    // Create a mock SubscriberRepository
    SubscriberRepository subscriberRepository = Mockito.mock(SubscriberRepository.class);

    // Create a SubscriberService instance with the mock repository
    SubscriberService subscriberService = new SubscriberService(subscriberRepository);

    // Mock the behavior of the subscriberRepository
    Mockito.when(subscriberRepository.countByPlanTypeAndLocation(PlanType.POSTPAID, Location.HYDERABAD)).thenReturn(4L);
    Mockito.when(subscriberRepository.countByPlanTypeAndLocation(PlanType.POSTPAID, Location.BENGALURU)).thenReturn(5L);

    // Call the service method for different locations
    long resultHyderabad = subscriberService.countPostpaidSubscribersByLocation(Location.HYDERABAD);
    long resultBengaluru = subscriberService.countPostpaidSubscribersByLocation(Location.BENGALURU);

    // Verify the results
    assertEquals(4L, resultHyderabad);
    assertEquals(5L, resultBengaluru);
}

@Test
public void testCalculatePrepaidRevenue() {
    // Create a mock SubscriberRepository
    SubscriberRepository subscriberRepository = Mockito.mock(SubscriberRepository.class);

    // Create a SubscriberService instance with the mock repository
    SubscriberService subscriberService = new SubscriberService(subscriberRepository);

    // Create sample subscriber data for testing
    List<Subscriber> prepaidSubscribers = new ArrayList<>();
    Subscriber subscriber1 = new Subscriber();
    subscriber1.setPlan(new Plan()); // Replace 50.0 with the actual plan price
    prepaidSubscribers.add(subscriber1);
    Subscriber subscriber2 = new Subscriber();
    subscriber2.setPlan(new Plan()); // Replace 60.0 with the actual plan price
    prepaidSubscribers.add(subscriber2);

    // Mock the behavior of the subscriberRepository
    Mockito.when(subscriberRepository.findAllByPlanType(PlanType.PREPAID)).thenReturn(prepaidSubscribers);

    // Call the service method
    double result = subscriberService.calculatePrepaidRevenue();

    // Calculate the expected result
    double expectedRevenue = prepaidSubscribers.stream()
            .mapToDouble(subscriber -> subscriber.getPlan().getPrice())
            .sum();

    // Verify the result
    assertEquals(expectedRevenue, result, 0.001);
}

@Test
public void testCalculatePostpaidRevenue() {
    // Create a mock SubscriberRepository
    SubscriberRepository subscriberRepository = Mockito.mock(SubscriberRepository.class);

    // Create a SubscriberService instance with the mock repository
    SubscriberService subscriberService = new SubscriberService(subscriberRepository);

    // Create sample subscriber data for testing
    List<Subscriber> postpaidSubscribers = new ArrayList<>();
    Subscriber subscriber1 = new Subscriber();
    subscriber1.setPlan(new Plan()); // Replace 70.0 with the actual plan price
    postpaidSubscribers.add(subscriber1);
    Subscriber subscriber2 = new Subscriber();
    subscriber2.setPlan(new Plan()); // Replace 80.0 with the actual plan price
    postpaidSubscribers.add(subscriber2);

    // Mock the behavior of the subscriberRepository
    Mockito.when(subscriberRepository.findAllByPlanType(PlanType.POSTPAID)).thenReturn(postpaidSubscribers);

    // Call the service method
    double result = subscriberService.calculatePostpaidRevenue();

    // Calculate the expected result
    double expectedRevenue = postpaidSubscribers.stream()
            .mapToDouble(subscriber -> subscriber.getPlan().getPrice())
            .sum();

    // Verify the result
    assertEquals(expectedRevenue, result, 0.001);
}
   @Test
    public void testGetPostpaidRevenueForLocation() {
        // Create a mock SubscriberRepository
        SubscriberRepository subscriberRepository = Mockito.mock(SubscriberRepository.class);

        // Create a SubscriberService instance with the mock repository
        SubscriberService subscriberService = new SubscriberService(subscriberRepository);

        // Define the location for testing
        Location location = Location.HYDERABAD;

        // Create sample postpaid subscriber data for testing in the specified location
        List<Subscriber> postpaidSubscribers = new ArrayList<>();
        Subscriber subscriber1 = new Subscriber();
        subscriber1.setPlan(new Plan()); // Replace 70.0 with the actual plan price
        subscriber1.setLocation(location);
        postpaidSubscribers.add(subscriber1);
        Subscriber subscriber2 = new Subscriber();
        subscriber2.setPlan(new Plan()); // Replace 80.0 with the actual plan price
        subscriber2.setLocation(location);
        postpaidSubscribers.add(subscriber2);

        // Mock the behavior of the subscriberRepository
        Mockito.when(subscriberRepository.findByPlanType(PlanType.POSTPAID)).thenReturn(postpaidSubscribers);

        // Call the service method
        double result = subscriberService.getPostpaidRevenueForLocation(location);

        // Calculate the expected result
        double expectedRevenue = postpaidSubscribers.stream()
                .filter(subscriber -> subscriber.getLocation() == location)
                .mapToDouble(subscriber -> subscriber.getPlan().getPrice())
                .sum();

        // Verify the result
        assertEquals(expectedRevenue, result, 0.001);
    }

    @Test
    public void testGetPrepaidRevenueForLocation() {
        // Create a mock SubscriberRepository
        SubscriberRepository subscriberRepository = Mockito.mock(SubscriberRepository.class);

        // Create a SubscriberService instance with the mock repository
        SubscriberService subscriberService = new SubscriberService(subscriberRepository);

        // Define the location for testing
        Location location = Location.BENGALURU;

        // Create sample prepaid subscriber data for testing in the specified location
        List<Subscriber> prepaidSubscribers = new ArrayList<>();
        Subscriber subscriber1 = new Subscriber();
        subscriber1.setPlan(new Plan()); // Replace 50.0 with the actual plan price
        subscriber1.setLocation(location);
        prepaidSubscribers.add(subscriber1);
        Subscriber subscriber2 = new Subscriber();
        subscriber2.setPlan(new Plan()); // Replace 60.0 with the actual plan price
        subscriber2.setLocation(location);
        prepaidSubscribers.add(subscriber2);

        // Mock the behavior of the subscriberRepository
        Mockito.when(subscriberRepository.findByPlanType(PlanType.PREPAID)).thenReturn(prepaidSubscribers);

        // Call the service method
        double result = subscriberService.getPrepaidRevenueForLocation(location);

        // Calculate the expected result
        double expectedRevenue = prepaidSubscribers.stream()
                .filter(subscriber -> subscriber.getLocation() == location)
                .mapToDouble(subscriber -> subscriber.getPlan().getPrice())
                .sum();

        // Verify the result
        assertEquals(expectedRevenue, result, 0.001);
    }

    @Test
    public void testGetTotalRevenueForLocation() {
        // Create a mock SubscriberRepository
        SubscriberRepository subscriberRepository = Mockito.mock(SubscriberRepository.class);

        // Create a SubscriberService instance with the mock repository
        SubscriberService subscriberService = new SubscriberService(subscriberRepository);

        // Define the location for testing
        Location location = Location.CHENNAI;

        // Create sample subscriber data for testing in the specified location
        List<Subscriber> subscribersInLocation = new ArrayList<>();
        Subscriber subscriber1 = new Subscriber();
        subscriber1.setPlan(new Plan()); // Replace 50.0 with the actual plan price
        subscriber1.setLocation(location);
        subscribersInLocation.add(subscriber1);
        Subscriber subscriber2 = new Subscriber();
        subscriber2.setPlan(new Plan()); // Replace 60.0 with the actual plan price
        subscriber2.setLocation(location);
        subscribersInLocation.add(subscriber2);

        // Mock the behavior of the subscriberRepository
        Mockito.when(subscriberRepository.findByLocation(location)).thenReturn(subscribersInLocation);

        // Call the service method
        double result = subscriberService.getTotalRevenueForLocation(location);

        // Calculate the expected result
        double expectedRevenue = subscribersInLocation.stream()
                .mapToDouble(subscriber -> subscriber.getPlan().getPrice())
                .sum();

        // Verify the result
        assertEquals(expectedRevenue, result, 0.001);
    }
    //  @Test
    // public void testCountSubscribersByPlanName() {
    //     // Create some sample subscribers with different plan names
    //     List<Subscriber> subscribers = new ArrayList<>();
    //     subscribers.add(createSubscriber("Plan A"));
    //     subscribers.add(createSubscriber("Plan B"));
    //     subscribers.add(createSubscriber("Plan A"));
    //     subscribers.add(createSubscriber("Plan C"));

    //     ListCrudRepository<Subscriber, String> subscriberRepository;
    //     // Mock the behavior of subscriberRepository to return the sample subscribers
    //     when(subscriberRepository.findAll()).thenReturn(subscribers);

    //     // Call the service method
    //     Map<String, Long> planCounts = subscriberService.countSubscribersByPlanName();

    //     // Verify the result
    //     assertEquals(3, planCounts.size()); // There should be 3 unique plan names
    //     assertEquals(2L, planCounts.get("Plan A")); // Plan A should appear twice
    //     assertEquals(1L, planCounts.get("Plan B")); // Plan B should appear once
    //     assertEquals(1L, planCounts.get("Plan C")); // Plan C should appear once
    // }

    // private Subscriber createSubscriber(String planName) {
    //     Subscriber subscriber = new Subscriber();
    //     Plan plan = new Plan();
    //     plan.setName(planName);
    //     subscriber.setPlan(plan);
    //     return subscriber;
    // }

    }