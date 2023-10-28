package com.example.dashboard.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.dashboard.entities.Text;

public interface TextRepository extends MongoRepository<Text, String> {
    List<Text> findByPhoneNumber(String phoneNumber);

}
