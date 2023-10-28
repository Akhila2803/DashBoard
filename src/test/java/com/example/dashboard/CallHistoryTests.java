package com.example.dashboard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


import com.example.dashboard.entities.CallHistory;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CallHistoryTests {


    @Test
    public void testCallHistoryEntity() {
        CallHistory callHistory = new CallHistory();
        callHistory.setName("John Doe");
        callHistory.setPhoneNumber("123-456-7890");
        callHistory.setStartTime("10:00 AM");
        callHistory.setEndTime("10:30 AM");
        assertEquals("John Doe", callHistory.getName());
        assertEquals("123-456-7890", callHistory.getPhoneNumber());
        assertEquals("10:00 AM", callHistory.getStartTime());
        assertEquals("10:30 AM", callHistory.getEndTime());
    }
    @Test
    public void testCalculateCallDurationWithValidData() {
        CallHistory callHistory = new CallHistory();
        String result = callHistory.calculateCallDuration();
    }

    @Test
    public void testCalculateCallDurationWithNullValues() {
        CallHistory callHistory = new CallHistory();
        String result = callHistory.calculateCallDuration();
        assertEquals("N/A", result);
    }

    @Test
    public void testCalculateCallDurationWithValidEndTimeBeforeStartTime() {
        CallHistory callHistory = new CallHistory();
        callHistory.setStartTime("11:00 AM");
        callHistory.setEndTime("10:30 AM");
    }

    @Test
    public void testCalculateCallDurationWithValidMidnightEndTime() {
        CallHistory callHistory = new CallHistory();
        callHistory.setStartTime("23:45");
        callHistory.setEndTime("00:15");
    }
    @Test
    public void testCalculateCallDurationWithParsingError() {
        CallHistory callHistory = new CallHistory();
        callHistory.setStartTime("invalidTime");
        callHistory.setEndTime("10:30 AM");
        String result = callHistory.calculateCallDuration();
        assertEquals("Error", result);
    }
}
