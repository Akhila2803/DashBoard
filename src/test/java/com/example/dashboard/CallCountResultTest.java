package com.example.dashboard;
import org.junit.jupiter.api.Test;

import com.example.dashboard.dto.CallCountResult;

import static org.assertj.core.api.Assertions.assertThat;

public class CallCountResultTest {

    @Test
    public void testGetSetStartHour() {
        CallCountResult result = new CallCountResult();
        result.setStartHour(10);
        assertThat(result.getStartHour()).isEqualTo(10);
    }

    @Test
    public void testGetSetCount() {
        CallCountResult result = new CallCountResult();
        result.setCount(100);
        assertThat(result.getCount()).isEqualTo(100);
    }
}
