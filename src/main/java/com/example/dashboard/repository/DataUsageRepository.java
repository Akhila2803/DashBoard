package com.example.dashboard.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.dashboard.entities.DataUsage;

public interface DataUsageRepository extends MongoRepository<DataUsage, String> {
    List<DataUsage> findByPhoneNumber(String phoneNumber);
}