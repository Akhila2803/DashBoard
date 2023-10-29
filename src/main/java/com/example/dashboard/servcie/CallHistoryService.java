package com.example.dashboard.servcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dashboard.entities.CallHistory;
import com.example.dashboard.repository.CallHistoryRepository;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
public class CallHistoryService {

    private final CallHistoryRepository callHistoryRepository;

    @Autowired
    public CallHistoryService(CallHistoryRepository callHistoryRepository) {
        this.callHistoryRepository = callHistoryRepository;
    }
    public CallHistory createCallHistory(CallHistory callHistory) {
        return callHistoryRepository.save(callHistory);
    }
    
    public List<CallHistory> getAllCallHistories() {
        return callHistoryRepository.findAll();
    }

    public List<CallHistory> getCallHistoryByPhoneNumber(String phoneNumber) {
        return callHistoryRepository.findByPhoneNumber(phoneNumber);
    }
    public List<CallHistory> getCallHistoryWithDurations() {
        List<CallHistory> callHistories = callHistoryRepository.findAll();
        for (CallHistory callHistory : callHistories) {
            String callDuration = callHistory.calculateCallDuration();
            callHistory.setCallDuration(callDuration);
        }
        return callHistories;
    }

   
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm"); // Declare timeFormat at the class level

    public Map<String, Integer> countCallsInOneHourIntervals() {
        Map<String, Integer> intervalCounts = new LinkedHashMap<>();

        try {
            Date intervalStart = timeFormat.parse("00:00");
            Date intervalEnd = timeFormat.parse("01:00");

            for (int hour = 0; hour < 24; hour++) {
                int callCount = countCallsInInterval(intervalStart, intervalEnd);
                String intervalKey = timeFormat.format(intervalStart) + " to " + timeFormat.format(intervalEnd);
                intervalCounts.put(intervalKey, callCount);

                intervalStart.setTime(intervalStart.getTime() + 3600000); // Add 1 hour in milliseconds
                intervalEnd.setTime(intervalEnd.getTime() + 3600000);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return intervalCounts;
    }

    public int countCallsInInterval(Date start, Date end) {
        List<CallHistory> callHistoryList = callHistoryRepository.findAll();
        int callCount = 0;
        for (CallHistory call : callHistoryList) {
            String callStartTime = call.getStartTime();

        if (callStartTime != null && !callStartTime.isEmpty()) {
            try {
                Date callTime = timeFormat.parse(call.getStartTime());
                if (callTime.after(start) && callTime.before(end)) {
                    callCount++;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        
    }

       return callCount;
    }

}
