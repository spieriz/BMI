package com.example.spieriz.lab1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void for_valid_data_bmi_isCorrect(){
        BMI bmiCounter = new BmiForKgM(60,170);
        double bmi = bmiCounter.calculate();
        assertEquals(20.761, bmi, 0.001);
    }

    @Test (expected = IllegalArgumentException.class)
    public void zero_bmi_throw_exception(){
        BMI bmiCounter = new BmiForKgM(0,0);
        double bmi = bmiCounter.calculate();
    }

}