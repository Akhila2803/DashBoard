package com.example.dashboard.servcie;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.dashboard.entities.Text;
import com.example.dashboard.repository.TextRepository;

@Service
public class TextService{
    @Autowired
    private TextRepository textRepository;

    public List<Text> getTextsByPhoneNumber(String phoneNumber) {
        return textRepository.findByPhoneNumber(phoneNumber);
    }

    public Text createText(Text text) {
        return textRepository.save(text);
    }
    public List<Text> getAllTexts() {
        return textRepository.findAll();
    }
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    public Map<String, Integer> countTextsInOneHourIntervals() {
        Map<String, Integer> intervalCounts = new LinkedHashMap<>();

        try {
            Date intervalStart = timeFormat.parse("00:00");
            Date intervalEnd = timeFormat.parse("01:00");

            for (int hour = 0; hour < 24; hour++) {
                int textCount = countTextsInInterval(intervalStart, intervalEnd);
                String intervalKey = timeFormat.format(intervalStart) + " to " + timeFormat.format(intervalEnd);
                intervalCounts.put(intervalKey, textCount);

                intervalStart.setTime(intervalStart.getTime() + 3600000); // Add 1 hour in milliseconds
                intervalEnd.setTime(intervalEnd.getTime() + 3600000);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return intervalCounts;
    }

    public int countTextsInInterval(Date start, Date end) {
        List<Text> textList = textRepository.findAll();
        int textCount = 0;
        for (Text text : textList) {
            try {
                Date textTime = timeFormat.parse(text.getTime());
                if (textTime.after(start) && textTime.before(end)) {
                    textCount++;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return textCount;
    }
    

}
