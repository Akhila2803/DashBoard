package com.example.dashboard;
import com.example.dashboard.servcie.PlanService;
import com.example.dashboard.entities.Plan;
import com.example.dashboard.entities.Subscriber.PlanType;
import com.example.dashboard.repository.PlanRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlanServiceTest {

    @Test
    public void testGetAllPlans() {
        // Create a mock PlanRepository
        PlanRepository planRepository = Mockito.mock(PlanRepository.class);

        // Create a PlanService instance with the mock repository
        PlanService planService = new PlanService(planRepository);

        // Create sample plans for testing
        List<Plan> samplePlans = new ArrayList<>();
        samplePlans.add(new Plan());
        samplePlans.add(new Plan());

        // Mock the behavior of the planRepository
        Mockito.when(planRepository.findAll()).thenReturn(samplePlans);

        // Call the service method
        List<Plan> result = planService.getAllPlans();

        // Verify the result
        assertEquals(2, result.size());
    }

    @Test
    public void testGetPlansByType() {
        // Create a mock PlanRepository
        PlanRepository planRepository = Mockito.mock(PlanRepository.class);

        // Create a PlanService instance with the mock repository
        PlanService planService = new PlanService(planRepository);

        // Create sample plans for testing
        List<Plan> samplePlans = new ArrayList<>();
        samplePlans.add(new Plan());
        samplePlans.add(new Plan());

        // Mock the behavior of the planRepository
        Mockito.when(planRepository.findByPlanType(PlanType.PREPAID)).thenReturn(samplePlans);

        // Call the service method
        List<Plan> result = planService.getPlansByType(PlanType.PREPAID);

        // Verify the result
        assertEquals(2, result.size());
    }

    @Test
    public void testAddPlan() {
        // Create a mock PlanRepository
        PlanRepository planRepository = Mockito.mock(PlanRepository.class);

        // Create a PlanService instance with the mock repository
        PlanService planService = new PlanService(planRepository);

        // Create a new plan to add
        Plan newPlan = new Plan();

        // Mock the behavior of the planRepository
        Mockito.when(planRepository.save(newPlan)).thenReturn(newPlan);

        // Call the service method to add the plan
        Plan addedPlan = planService.addPlan(newPlan);

        // Verify the result
        assertEquals(newPlan, addedPlan);
    }
}
