package com.example.dashboard.controller;
import com.example.dashboard.entities.CallHistory;
import com.example.dashboard.entities.DataUsage;
import com.example.dashboard.entities.Plan;
import com.example.dashboard.entities.Subscriber;
import com.example.dashboard.entities.Text;
import com.example.dashboard.entities.Subscriber.Location;
import com.example.dashboard.entities.Subscriber.PlanType;
import com.example.dashboard.servcie.CallHistoryService;
import com.example.dashboard.servcie.DataUsageService;
import com.example.dashboard.servcie.PlanService;
import com.example.dashboard.servcie.SubscriberService;
import com.example.dashboard.servcie.TextService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DashBoardController {
    private final PlanService planService;
    private final SubscriberService subscriberService;
    private final CallHistoryService callHistoryService;
    private final TextService textService;
    private final DataUsageService dataUsageService;
    @Autowired
    public DashBoardController(PlanService planService,SubscriberService subscriberService, CallHistoryService callHistoryService, TextService textService, DataUsageService dataUsageService) {
        this.planService = planService;
        this.subscriberService = subscriberService;
        this.callHistoryService=callHistoryService;
        this.textService=textService;
        this.dataUsageService = dataUsageService;
    }
     
    @GetMapping("/plans")
    public List<Plan> getAllPlans() {
        return planService.getAllPlans();
    }
    @GetMapping("/plans/{planType}")
    public List<Plan> getPlansByType(@PathVariable PlanType planType){
        return planService.getPlansByType(planType);
    }
    @GetMapping("/all")
    public List<Subscriber> getAllSubscribers() {
        return subscriberService.getAllSubscribers();
    }
    @PostMapping("/add")
    public Plan addPlan(@RequestBody Plan plan) {
        return planService.addPlan(plan);
    }

    @GetMapping("/revenue")
    public double getTotalRevenue() {
        return subscriberService.getTotalRevenue();
    }
    @GetMapping("/total")
    public long getTotalSubscribers() {
        return subscriberService.getTotalSubscribers(); 
    }
    @GetMapping("/prepaid/TOTAL")
    public long getPrepaidSubscribers() {
        return subscriberService.getPrepaidSubscribers();
    }
    @GetMapping("/postpaid/TOTAL")
    public long getPostpaidSubscribers() {
        return subscriberService.getPostpaidSubscribers();
    }
    @GetMapping("/count")
    public long getSubscriberCount() {
        return subscriberService.getTotalSubscribers();
    }
    @GetMapping("/count/{location}")
    public long getSubscriberCountByLocation(@PathVariable Location location) {
        return subscriberService.getSubscriberCountByLocation(location);
    }
    
    @GetMapping("/prepaid/{location}")
    public long countPrepaidSubscribersByLocation(@PathVariable("location") Subscriber.Location location) {
        return subscriberService.countPrepaidSubscribersByLocation(location);
    }

    @GetMapping("/postpaid/{location}")
    public long countPostpaidSubscribersByLocation(@PathVariable("location") Subscriber.Location location) {
        return subscriberService.countPostpaidSubscribersByLocation(location);
    }
    @GetMapping("total/prepaid/TOTAL")
    public double getPrepaidRevenue() {
        return subscriberService.calculatePrepaidRevenue();
    }

    @GetMapping("total/postpaid/TOTAL")
    public double getPostpaidRevenue() {
        return subscriberService.calculatePostpaidRevenue();
    }
    @GetMapping("/total/postpaid/{location}")
    public double getPostpaidRevenueForLocation(@PathVariable Subscriber.Location location) {
        return subscriberService.getPostpaidRevenueForLocation(location);
    }
    @GetMapping("/total/prepaid/{location}")
    public double getPrepaidRevenueForLocation(@PathVariable Subscriber.Location location) {
        return subscriberService.getPrepaidRevenueForLocation(location);
    }
    @GetMapping("/total/{location}")
    public double getTotalRevenueForLocation(@PathVariable Subscriber.Location location) {
        return subscriberService.getTotalRevenueForLocation(location);
    }

    @GetMapping("/calls")
    public List<CallHistory> getAllCallHistories() {
        return callHistoryService.getAllCallHistories();
    }
    @PostMapping("/add/call")
    public CallHistory createCallHistory( @RequestBody CallHistory callHistory) {
        return callHistoryService.createCallHistory(callHistory);
    } 

    @PostMapping("/add/text")
    public Text createText(@RequestBody Text text) {
        return textService.createText(text);
    }
    @GetMapping("/text")
    public List<Text> getAllTexts() {
        return textService.getAllTexts();
    }
    @PostMapping("/add/data")
    public DataUsage createDataUsage(@RequestBody DataUsage dataUsage) {
        return dataUsageService.createDataUsage(dataUsage);
    }
    @PostMapping("/add/sub")
    public Subscriber createSubscriber(@RequestBody Subscriber subscriber) {
        return subscriberService.createSubscriber(subscriber);
    }
    
    @GetMapping("/data")
    public List<DataUsage> getAllDataUsages() {
        return dataUsageService.getAllDataUsages();
    }
    @GetMapping("calls/{phoneNumber}")
    public List<CallHistory> getCallHistoryByPhoneNumber(@PathVariable String phoneNumber) {
        return callHistoryService.getCallHistoryByPhoneNumber(phoneNumber);
    }
     @GetMapping("text/{phoneNumber}")
    public List<Text> getTextsByPhoneNumber(@PathVariable String phoneNumber) {
        return textService.getTextsByPhoneNumber(phoneNumber);
    }
    @GetMapping("data/{phoneNumber}")
    public List<DataUsage> getDataUsageByPhoneNumber(@PathVariable String phoneNumber) {
        return dataUsageService.getDataUsageByPhoneNumber(phoneNumber);
    }

    @GetMapping("/total-consumed-data")
    public double getTotalConsumedData() {
        return dataUsageService.getTotalConsumedData();
    }
   

    @GetMapping("/countByPlanName")
    public Map<String, Long> countSubscribersByPlanName() {
        return subscriberService.countSubscribersByPlanName();
    }
    @GetMapping("/withdurations")
    public List<CallHistory> getCallHistoryWithDurations() {
        return callHistoryService.getCallHistoryWithDurations();
    }
    @GetMapping("/count-calls-in-intervals")
    public Map<String, Integer> countCallsInIntervals() {
        Map<String, Integer> intervalCounts = callHistoryService.countCallsInOneHourIntervals();
        return intervalCounts;
    }

    @GetMapping("/count-texts-in-intervals")
    public Map<String, Integer> countTextsInIntervals() {
        return textService.countTextsInOneHourIntervals();
    }
    @GetMapping("/average-call-duration")
    public double getAverageCallDuration() {
        List<CallHistory> callHistoryList = callHistoryService.getAllCallHistories(); // Implement this method in your service
        double averageDuration = CallHistoryAverageDurationCalculator.calculateAverageCallDuration(callHistoryList); // Use your calculation method
        return Math.round(averageDuration);
    }
}





