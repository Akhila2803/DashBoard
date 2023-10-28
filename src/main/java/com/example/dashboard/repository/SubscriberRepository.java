package com.example.dashboard.repository;

import com.example.dashboard.entities.Subscriber;
import com.example.dashboard.entities.Subscriber.Location;
import com.example.dashboard.entities.Subscriber.PlanType;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriberRepository extends MongoRepository<Subscriber, String> {
    long countByPlanType(Subscriber.PlanType planType);
    
    Subscriber findByPhoneNumber(String phoneNumber);
    List<Subscriber> findByLocation(Subscriber.Location location);
    long countByLocation(Location location);
    long countByLocationAndPlanType(Subscriber.Location location, Subscriber.PlanType planType);
     List<Subscriber> findByLocationAndPlanType(Location location, PlanType planType);
     List<Subscriber> findAllByPlanType(Subscriber.PlanType planType);
     List<Subscriber> findByPlanType(Subscriber.PlanType planType);
     List<Subscriber> findByPlanName(String planName);
    long countByPlanTypeAndLocation(PlanType postpaid, Location location);
    List<Subscriber> findByPlanDataLimit(String dataLimit);
     

    
}
