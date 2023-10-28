package com.example.dashboard.servcie;
import com.example.dashboard.entities.Plan;
import com.example.dashboard.entities.Subscriber.PlanType;
import com.example.dashboard.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {
    private final PlanRepository planRepository;

    @Autowired
    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }
    public List<Plan> getPlansByType(PlanType planType){
        return planRepository.findByPlanType(planType);
    }
    public Plan addPlan(Plan plan) {
        return planRepository.save(plan);
    }

}
