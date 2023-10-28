package com.example.dashboard.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "subscribers")
public class Subscriber {
    public static final String PREPAID = null;
    public static final Location Location = null;
    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    
    // Define a PlanType enum
    private PlanType planType;

    private Location location;

    // Reference to PlanDetails
    @DBRef
    private Plan plan;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PlanType getPlanType() {
        return planType;
    }

    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public enum PlanType {
        PREPAID,
        POSTPAID
    }
    public enum Location {
        HYDERABAD,
        BENGALURU,
        CHENNAI,
        MUMBAI

    }
    
   


}

