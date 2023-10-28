package com.example.dashboard.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.dashboard.entities.Subscriber.PlanType;
@Document(collection = "plans")
public class Plan {
    @Id
    private String id;
    private String name;
    private PlanType planType; 
    private String dataLimit;
    private String totalData;
    private String voiceLimit;
    private String smsLimit;
    private String validity;
    
    public String getValidity() {
        return validity;
    }
    public void setValidity(String validity) {
        this.validity = validity;
    }
    private double price;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public PlanType getPlanType() {
        return planType;
    }
    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }
    public String getDataLimit() {
        return dataLimit;
    }
    public void setDataLimit(String dataLimit) {
        this.dataLimit = dataLimit;
    }
    public String getTotalData() {
        return totalData;
    }
    public void setTotalData(String totalData) {
        this.totalData = totalData;
    }
    public String getVoiceLimit() {
        return voiceLimit;
    }
    public void setVoiceLimit(String voiceLimit) {
        this.voiceLimit = voiceLimit;
    }
    public String getSmsLimit() {
        return smsLimit;
    }
    public void setSmsLimit(String smsLimit) {
        this.smsLimit = smsLimit;
    }
    
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
   
    
}
