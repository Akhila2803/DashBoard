package com.example.dashboard;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dashboard.entities.Text;
import com.example.dashboard.repository.TextRepository;
import com.example.dashboard.servcie.TextService;

public class TextServiceTest {

    @InjectMocks
    private TextService textService;

    @Mock
    private TextRepository textRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTextsByPhoneNumber() {
        String phoneNumber = "1234567890";
        List<Text> textList = new ArrayList<>();
        when(textRepository.findByPhoneNumber(phoneNumber)).thenReturn(textList);
        List<Text> result = textService.getTextsByPhoneNumber(phoneNumber);
        assertEquals(textList, result);
    }

    @Test
    public void testCreateText() {
        Text text = new Text();
        when(textRepository.save(text)).thenReturn(text);
        Text createdText = textService.createText(text);
        assertEquals(text, createdText);
    }

    @Test
    public void testGetAllTexts() {
        List<Text> textList = new ArrayList<>();
        when(textRepository.findAll()).thenReturn(textList);
        List<Text> result = textService.getAllTexts();
        assertEquals(textList, result);
    }

      @Test
    public void testCountTextsInInterval() throws ParseException {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date start = timeFormat.parse("10:00");
        Date end = timeFormat.parse("12:00");

        List<Text> textList = new ArrayList<>();
        textList.add(createTextWithTime("10:30"));
        textList.add(createTextWithTime("11:45"));
        when(textRepository.findAll()).thenReturn(textList);

        int result = textService.countTextsInInterval(start, end);
        assertEquals(2, result);
    }

    @Test
    public void testCountTextsInOneHourIntervals() {
        // Define test data, such as a list of Text objects with different times
        List<Text> textList = new ArrayList<>();
        textList.add(createTextWithTime("10:30"));
        textList.add(createTextWithTime("11:45"));
        when(textRepository.findAll()).thenReturn(textList);

        // Call the countTextsInOneHourIntervals method
        // It should calculate counts in one-hour intervals

        // Assert the result
    }

    // Helper method to create Text objects with a specific time
    private Text createTextWithTime(String time) {
        Text text = new Text();
        text.setTime(time);
        return text;
    }



   
}
