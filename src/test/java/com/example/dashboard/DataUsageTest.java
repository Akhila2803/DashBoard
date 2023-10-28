package com.example.dashboard;

import com.example.dashboard.entities.DataUsage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataUsageTest {

    @Test
    public void testDataUsageGettersAndSetters() {
        // Create a new DataUsage object
        DataUsage dataUsage = new DataUsage();

        // Set values using setter methods
        dataUsage.setId("1");
        dataUsage.setName("John Doe");
        dataUsage.setPhoneNumber("555-123-4567");
        dataUsage.setDate("2023-10-27");
       
        dataUsage.setDataPerDay("1GB");
        dataUsage.setConsumedData("500MB");
        dataUsage.setLeftData("500MB");

        // Verify the values using getter methods
        assertEquals("1", dataUsage.getId());
        assertEquals("John Doe", dataUsage.getName());
        assertEquals("555-123-4567", dataUsage.getPhoneNumber());
        assertEquals("2023-10-27", dataUsage.getDate());
       
        assertEquals("1GB", dataUsage.getDataPerDay());
        assertEquals("500MB", dataUsage.getConsumedData());
        assertEquals("500MB", dataUsage.getLeftData());
    }
}
