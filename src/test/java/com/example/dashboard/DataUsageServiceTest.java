package com.example.dashboard;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dashboard.entities.DataUsage;
import com.example.dashboard.repository.DataUsageRepository;
import com.example.dashboard.servcie.DataUsageService;

public class DataUsageServiceTest {

    @InjectMocks
    private DataUsageService dataUsageService;

    @Mock
    private DataUsageRepository dataUsageRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDataUsage() {
        DataUsage dataUsage = new DataUsage(); // create a DataUsage object
        when(dataUsageRepository.save(dataUsage)).thenReturn(dataUsage); // mock the repository
        DataUsage createdDataUsage = dataUsageService.createDataUsage(dataUsage);
        assertEquals(dataUsage, createdDataUsage);
    }

    @Test
    public void testGetDataUsageByPhoneNumber() {
        String phoneNumber = "1234567890";
        List<DataUsage> dataUsageList = Arrays.asList(new DataUsage(), new DataUsage());
        when(dataUsageRepository.findByPhoneNumber(phoneNumber)).thenReturn(dataUsageList);
        List<DataUsage> result = dataUsageService.getDataUsageByPhoneNumber(phoneNumber);
        assertEquals(dataUsageList, result);
    }

    @Test
    public void testGetAllDataUsages() {
        List<DataUsage> dataUsageList = Arrays.asList(new DataUsage(), new DataUsage());
        when(dataUsageRepository.findAll()).thenReturn(dataUsageList);
        List<DataUsage> result = dataUsageService.getAllDataUsages();
        assertEquals(dataUsageList, result);
    }

    @Test
    public void testGetTotalConsumedData() {
        DataUsage dataUsage1 = new DataUsage();
        dataUsage1.setConsumedData("10.5");
        DataUsage dataUsage2 = new DataUsage();
        dataUsage2.setConsumedData("5.2");
        List<DataUsage> dataUsageList = Arrays.asList(dataUsage1, dataUsage2);
        when(dataUsageRepository.findAll()).thenReturn(dataUsageList);
        double result = dataUsageService.getTotalConsumedData();
        assertEquals(15.7, result, 0.01); // specify a delta for double comparison
    }
    @Test
public void testGetTotalConsumedDataWithInvalidData() {
    DataUsage dataUsage1 = new DataUsage();
    dataUsage1.setConsumedData("10.5");
    DataUsage dataUsage2 = new DataUsage();
    dataUsage2.setConsumedData("invalid"); // This will cause a NumberFormatException
    List<DataUsage> dataUsageList = Arrays.asList(dataUsage1, dataUsage2);
    when(dataUsageRepository.findAll()).thenReturn(dataUsageList);
    double result = dataUsageService.getTotalConsumedData();
    assertEquals(10.5, result, 0.01); // Make sure that the valid data is included in the total
    // You can also add assertions or custom logic to handle the invalid data scenario
}
}
