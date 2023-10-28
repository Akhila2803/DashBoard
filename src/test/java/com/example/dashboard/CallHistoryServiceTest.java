package com.example.dashboard;
import com.example.dashboard.entities.CallHistory;
import com.example.dashboard.repository.CallHistoryRepository;
import com.example.dashboard.servcie.CallHistoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CallHistoryServiceTest {

    @InjectMocks
    private CallHistoryService callHistoryService;

    @Mock
    private CallHistoryRepository callHistoryRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        callHistoryService = new CallHistoryService(callHistoryRepository);
    }

    @Test
    public void testCreateCallHistory() {
        CallHistory callHistory = new CallHistory();
        when(callHistoryRepository.save(callHistory)).thenReturn(callHistory);

        CallHistory createdCallHistory = callHistoryService.createCallHistory(callHistory);
        assertEquals(callHistory, createdCallHistory);
    }

    @Test
    public void testGetAllCallHistories() {
        List<CallHistory> callHistories = new ArrayList<>();
        callHistories.add(new CallHistory());
        callHistories.add(new CallHistory());

        when(callHistoryRepository.findAll()).thenReturn(callHistories);

        List<CallHistory> result = callHistoryService.getAllCallHistories();
        assertEquals(callHistories, result);
    }

    @Test
    public void testGetCallHistoryByPhoneNumber() {
        String phoneNumber = "1234567890";
        List<CallHistory> callHistories = new ArrayList<>();
        when(callHistoryRepository.findByPhoneNumber(phoneNumber)).thenReturn(callHistories);

        List<CallHistory> result = callHistoryService.getCallHistoryByPhoneNumber(phoneNumber);
        assertEquals(callHistories, result);
    }

    @Test
    public void testGetCallHistoryWithDurations() {
        // Create a list of CallHistory objects with different call durations
        List<CallHistory> callHistoryList = new ArrayList<>();
        callHistoryList.add(createCallHistoryWithDuration("1:30"));
        callHistoryList.add(createCallHistoryWithDuration("0:45"));
        callHistoryList.add(createCallHistoryWithDuration("N/A")); // Should be skipped
        callHistoryList.add(createCallHistoryWithDuration("2:15"));

        when(callHistoryRepository.findAll()).thenReturn(callHistoryList);

        List<CallHistory> result = callHistoryService.getCallHistoryWithDurations();


    }

    @Test
    public void testCountCallsInOneHourIntervals() throws ParseException {
        List<CallHistory> callHistoryList = new ArrayList<>();
        callHistoryList.add(createCallHistoryWithStartTime("08:30"));
        callHistoryList.add(createCallHistoryWithStartTime("09:45"));
        callHistoryList.add(createCallHistoryWithStartTime("12:15"));
        // Add call with no start time (should be skipped)
        callHistoryList.add(createCallHistoryWithStartTime(null));

        when(callHistoryRepository.findAll()).thenReturn(callHistoryList);

        // Mock SimpleDateFormat to parse time
        SimpleDateFormat timeFormat = Mockito.mock(SimpleDateFormat.class);
        Mockito.when(timeFormat.parse("00:00")).thenReturn(new Date(0));
        Mockito.when(timeFormat.parse("01:00")).thenReturn(new Date(3600000));

        List<CallHistory> result = callHistoryService.getCallHistoryWithDurations();
       
    }

    private CallHistory createCallHistoryWithDuration(String duration) {
        CallHistory callHistory = new CallHistory();
        callHistory.setCallDuration(duration);
        return callHistory;
    }

    private CallHistory createCallHistoryWithStartTime(String startTime) {
        CallHistory callHistory = new CallHistory();
        callHistory.setStartTime(startTime);
        return callHistory;
    }
    
}
