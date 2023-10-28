package com.example.dashboard.servcie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dashboard.entities.Subscriber;
import com.example.dashboard.entities.Subscriber.Location;
import com.example.dashboard.repository.SubscriberRepository;
@Service
public class SubscriberService {
    private final SubscriberRepository subscriberRepository;
    @Autowired
    public SubscriberService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
        
    }
    public List<Subscriber> getAllSubscribers() {
        return subscriberRepository.findAll();
    }
    public Subscriber createSubscriber(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    
    public long getPrepaidSubscribers() {
        return subscriberRepository.countByPlanType(Subscriber.PlanType.PREPAID);
    }

    public long getPostpaidSubscribers() {
        return subscriberRepository.countByPlanType(Subscriber.PlanType.POSTPAID);
    }
    public long getTotalSubscribers(){
         return getPostpaidSubscribers()+getPrepaidSubscribers();
    }
    public double getTotalRevenue() {
        List<Subscriber> subscribers = subscriberRepository.findAll();
        return subscribers.stream()
                .mapToDouble(subscriber -> subscriber.getPlan().getPrice())
                .sum();
    }  
    public long getSubscriberCountByLocation(Location location) {
        return subscriberRepository.countByLocation(location);
    }

    public long countPrepaidSubscribersByLocation(Subscriber.Location location) {
        return subscriberRepository.countByPlanTypeAndLocation(Subscriber.PlanType.PREPAID, location);
    }

    public long countPostpaidSubscribersByLocation(Subscriber.Location location) {
        return subscriberRepository.countByPlanTypeAndLocation(Subscriber.PlanType.POSTPAID, location);
    }
    public double calculatePrepaidRevenue() {
        List<Subscriber> prepaidSubscribers = subscriberRepository.findAllByPlanType(Subscriber.PlanType.PREPAID);
        return prepaidSubscribers.stream()
                .mapToDouble(subscriber -> subscriber.getPlan().getPrice())
                .sum();
    }

    public double calculatePostpaidRevenue() {
        List<Subscriber> postpaidSubscribers = subscriberRepository.findAllByPlanType(Subscriber.PlanType.POSTPAID);
        return postpaidSubscribers.stream()
                .mapToDouble(subscriber -> subscriber.getPlan().getPrice())
                .sum();
    }
    public double getPostpaidRevenueForLocation(Subscriber.Location location) {
        List<Subscriber> postpaidSubscribersInLocation = subscriberRepository.findByPlanType(Subscriber.PlanType.POSTPAID);
        return postpaidSubscribersInLocation.stream()
                .filter(subscriber -> subscriber.getLocation() == location)
                .mapToDouble(subscriber -> subscriber.getPlan().getPrice())
                .sum();
    }
    public double getPrepaidRevenueForLocation(Subscriber.Location location) {
        List<Subscriber> prepaidSubscribersInLocation = subscriberRepository.findByPlanType(Subscriber.PlanType.PREPAID);
        return prepaidSubscribersInLocation.stream()
                .filter(subscriber -> subscriber.getLocation() == location)
                .mapToDouble(subscriber -> subscriber.getPlan().getPrice())
                .sum();
    }
    public double getTotalRevenueForLocation(Location location) {
        List<Subscriber> subscribersInLocation = subscriberRepository.findByLocation(location);
        return subscribersInLocation.stream()
                .mapToDouble(subscriber -> subscriber.getPlan().getPrice())
                .sum();
    }
    public Map<String, Long> countSubscribersByPlanName() {
        List<Subscriber> subscribers = subscriberRepository.findAll();
        Map<String, Long> planCounts = new HashMap<>();

        for (Subscriber subscriber : subscribers) {
            String planName = subscriber.getPlan().getName();

            if (planCounts.containsKey(planName)) {
                planCounts.put(planName, planCounts.get(planName) + 1);
            } else {
                planCounts.put(planName, 1L);
            }
        }

        return planCounts;
    }

    }


    
