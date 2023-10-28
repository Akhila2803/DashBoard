package com.example.dashboard;

import com.example.dashboard.entities.Text;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextTest {

    @Test
    public void testTextGettersAndSetters() {
        // Create a new Text object
        Text text = new Text();

        // Set values using setter methods
        text.setId("1");
        text.setName("John Doe");
        text.setPhoneNumber("555-123-4567");
        text.setTime("08:00:00");
        text.setDate("2023-10-27");

        // Verify the values using getter methods
        assertEquals("1", text.getId());
        assertEquals("John Doe", text.getName());
        assertEquals("555-123-4567", text.getPhoneNumber());
        assertEquals("08:00:00", text.getTime());
        assertEquals("2023-10-27", text.getDate());
    }
}
