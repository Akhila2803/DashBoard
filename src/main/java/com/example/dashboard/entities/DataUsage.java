package com.example.dashboard.entities;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
@Document(collection = "data")

public class DataUsage {
    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String date;
    private String dataPerDay;
    private String consumedData;
    private String leftData;

   
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public String getDataPerDay() {
        return dataPerDay;
    }
    public void setDataPerDay(String dataPerDay) {
        this.dataPerDay = dataPerDay;
    }
    public String getConsumedData() {
        return consumedData;
    }
    public void setConsumedData(String consumedData) {
        this.consumedData = consumedData;
    }
    public String getLeftData() {
        return leftData;
    }
    public void setLeftData(String leftData) {
        this.leftData = leftData;
    }  
}
