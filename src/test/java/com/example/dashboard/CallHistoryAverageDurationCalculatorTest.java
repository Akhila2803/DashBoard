
package com.example.dashboard;
import com.example.dashboard.controller.CallHistoryAverageDurationCalculator;
import com.example.dashboard.entities.CallHistory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallHistoryAverageDurationCalculatorTest {

    @Test
    public void testCalculateAverageCallDurationWithValidCalls() {
        List<CallHistory> callHistoryList = new ArrayList<>();
        callHistoryList.add(createCallHistoryWithDuration("1:30"));
        callHistoryList.add(createCallHistoryWithDuration("0:45"));
        callHistoryList.add(createCallHistoryWithDuration("2:15"));
    }

    @Test
    public void testCalculateAverageCallDurationWithNoValidCalls() {
        List<CallHistory> callHistoryList = new ArrayList<>();

        callHistoryList.add(createCallHistoryWithDuration("N/A"));
        callHistoryList.add(createCallHistoryWithDuration("Error"));

        double averageDuration = CallHistoryAverageDurationCalculator.calculateAverageCallDuration(callHistoryList);
        assertThat(averageDuration).isEqualTo(0.0);
    }

    @Test
public void testCalculateAverageCallDurationWithMixedDurations() {
    List<CallHistory> callHistoryList = new ArrayList<>();

    callHistoryList.add(createCallHistoryWithDuration("1:30"));
    callHistoryList.add(createCallHistoryWithDuration("N/A"));
    callHistoryList.add(createCallHistoryWithDuration("0:45"));
    callHistoryList.add(createCallHistoryWithDuration("Error"));
    callHistoryList.add(createCallHistoryWithDuration("2:15"));
}


    private CallHistory createCallHistoryWithDuration(String duration) {
        CallHistory callHistory = new CallHistory();
        callHistory.setCallDuration(duration);
        return callHistory;
    }

     @Test
    public void testCalculateAverageCallDuration() {
        List<CallHistory> callHistoryList = new ArrayList<>();
        CallHistory call1 = new CallHistory();
        call1.setStartTime("09:00");
        call1.setEndTime("09:30");
        callHistoryList.add(call1);

        CallHistory call2 = new CallHistory();
        call2.setStartTime("10:00");
        call2.setEndTime("10:45");
        callHistoryList.add(call2);
        double averageDuration = CallHistoryAverageDurationCalculator.calculateAverageCallDuration(callHistoryList);
        double expectedAverageDuration = (30 + 45) / 2.0; 
        assertEquals(expectedAverageDuration, averageDuration, 0.01); 
    }
}
