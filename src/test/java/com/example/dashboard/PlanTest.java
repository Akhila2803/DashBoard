package com.example.dashboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dashboard.entities.Plan;
import com.example.dashboard.entities.Subscriber.PlanType;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PlanTest {

   private Plan plan;

   @BeforeEach
   public void setUp() {
       plan = new Plan();
       plan.setId("1");
       plan.setName("Sample Plan");
       plan.setPlanType(PlanType.PREPAID);
       plan.setDataLimit("5GB");
       plan.setTotalData("10GB");
       plan.setVoiceLimit("500 minutes");
       plan.setSmsLimit("Unlimited");
       plan.setValidity("30 days");
       plan.setPrice(19.99);
   }

   @Test
   public void testGettersAndSetters() {
       assertEquals("1", plan.getId());
       assertEquals("Sample Plan", plan.getName());
        assertEquals(PlanType.PREPAID, plan.getPlanType());
       assertEquals("5GB", plan.getDataLimit());
       assertEquals("10GB", plan.getTotalData());
       assertEquals("500 minutes", plan.getVoiceLimit());
       assertEquals("Unlimited", plan.getSmsLimit());
       assertEquals("30 days", plan.getValidity());
       assertEquals(19.99, plan.getPrice(), 0.001); // The third argument is a delta for comparing double values
   }
}
