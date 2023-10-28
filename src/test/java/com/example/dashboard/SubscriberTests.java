package com.example.dashboard;
import com.example.dashboard.entities.Subscriber;
import com.example.dashboard.entities.Subscriber.Location;
import com.example.dashboard.entities.Subscriber.PlanType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubscriberTests {

    @Test
    public void testSubscriberInstantiation() {
        // Create a new Subscriber object
        Subscriber subscriber = new Subscriber();

        // Verify that the subscriber object is not null
        // and that its attributes are initialized to default values
        assertEquals(null, subscriber.getId());
        assertEquals(null, subscriber.getName());
        assertEquals(null, subscriber.getPhoneNumber());
        assertEquals(null, subscriber.getEmail());
        assertEquals(null, subscriber.getPlanType());
        assertEquals(null, subscriber.getLocation());
        assertEquals(null, subscriber.getPlan());
    }

    @Test
    public void testSubscriberGettersAndSetters() {
        // Create a new Subscriber object
        Subscriber subscriber = new Subscriber();

        // Set values using setter methods
        subscriber.setId("123");
        subscriber.setName("John Doe");
        subscriber.setPhoneNumber("555-123-4567");
        subscriber.setEmail("johndoe@example.com");
        subscriber.setPlanType(PlanType.PREPAID);
        subscriber.setLocation(Location.HYDERABAD);
        // You can set other attributes as well

        // Verify the values using getter methods
        assertEquals("123", subscriber.getId());
        assertEquals("John Doe", subscriber.getName());
        assertEquals("555-123-4567", subscriber.getPhoneNumber());
        assertEquals("johndoe@example.com", subscriber.getEmail());
        assertEquals(PlanType.PREPAID, subscriber.getPlanType());
        assertEquals(Location.HYDERABAD, subscriber.getLocation());
        // Verify other attributes as needed
    }
}
