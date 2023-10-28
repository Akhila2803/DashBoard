package com.example.dashboard.servcie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dashboard.entities.DataUsage;
import com.example.dashboard.repository.DataUsageRepository;
@Service
public class DataUsageService {
    private final DataUsageRepository dataUsageRepository;

    @Autowired
    public DataUsageService(DataUsageRepository dataUsageRepository) {
        this.dataUsageRepository = dataUsageRepository;
    }
    public DataUsage createDataUsage(DataUsage dataUsage) {
        return dataUsageRepository.save(dataUsage);
    }
    public List<DataUsage> getDataUsageByPhoneNumber(String phoneNumber) {
        return dataUsageRepository.findByPhoneNumber(phoneNumber);
    }
    public List<DataUsage> getAllDataUsages() {
        return dataUsageRepository.findAll();
    }
    public double getTotalConsumedData() {
        List<DataUsage> allDataUsages = dataUsageRepository.findAll();
        double totalConsumedData = 0.0;

        for (DataUsage dataUsage : allDataUsages) {
            try {
                double consumedData = Double.parseDouble(dataUsage.getConsumedData());
                totalConsumedData += consumedData;
            } catch (NumberFormatException e) {
                // Handle invalid data if needed
            }
        }

        return totalConsumedData;
    }
}

