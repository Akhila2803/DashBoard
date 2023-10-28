package com.example.dashboard.controller;

import java.util.List;

import com.example.dashboard.entities.CallHistory;

public class CallHistoryAverageDurationCalculator {
            public static double calculateAverageCallDuration(List<CallHistory> callHistoryList) {
                int totalDurationInMinutes = 0;
                int totalCalls = callHistoryList.size();
        
                for (CallHistory call : callHistoryList) {
                    String callDuration = call.calculateCallDuration();
                    if (!"N/A".equals(callDuration) && !"Error".equals(callDuration)) {
                        String[] parts = callDuration.split(":");
                        int hours = Integer.parseInt(parts[0]);
                        int minutes = Integer.parseInt(parts[1]);
                        totalDurationInMinutes += (hours * 60) + minutes;
                    }
                }
        
                if (totalCalls > 0) {
                    double averageDurationInMinutes = (double) totalDurationInMinutes / totalCalls;
                    return averageDurationInMinutes;
                } else {
                    // Handle the case when there are no valid calls
                    return 0.0;
                }
            }
    }