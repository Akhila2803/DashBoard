package com.example.dashboard.entities;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "callhistory")
public class CallHistory {
    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String startTime; 
    private String endTime;   
    private String date;
    private String callDuration;
    public String getCallDuration() {
        return callDuration;
    }
    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String calculateCallDuration() {
        if (this.startTime == null || this.endTime == null) {
            return "N/A"; // Handle null values or missing data
        }
    
        // Parse start and end times
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = timeFormat.parse(this.startTime);
            endTime = timeFormat.parse(this.endTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error"; // Handle parsing error
        }
    
        // Calculate the duration in milliseconds
        long durationMillis = endTime.getTime() - startTime.getTime();
    
        // Convert duration to HH:mm format
        long hours = durationMillis / 3600000;
        long minutes = (durationMillis % 3600000) / 60000;
    
        return String.format("%02d:%02d", hours, minutes);
    }
    
    
}

    

