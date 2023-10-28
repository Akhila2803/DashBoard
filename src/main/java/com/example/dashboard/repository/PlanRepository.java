package com.example.dashboard.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.dashboard.entities.Plan;
import com.example.dashboard.entities.Subscriber.PlanType;

public interface PlanRepository extends MongoRepository<Plan,String> {
    List<Plan>findByPlanType(PlanType planType);
    
}
