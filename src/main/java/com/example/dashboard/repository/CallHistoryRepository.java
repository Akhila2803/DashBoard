package com.example.dashboard.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.example.dashboard.entities.CallHistory;

public interface CallHistoryRepository extends MongoRepository<CallHistory, String> {
    List<CallHistory> findByPhoneNumber(String phoneNumber);
    List<CallHistory> findByPhoneNumberOrderByDate(String phoneNumber);

    
}