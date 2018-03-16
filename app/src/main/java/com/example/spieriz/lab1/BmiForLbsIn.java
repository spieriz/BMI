package com.example.spieriz.lab1;

import com.example.spieriz.lab1.BMI;

/**
 * Created by spieriz on 2018-03-08.
 */

public class BmiForLbsIn extends BMI {

    public BmiForLbsIn(double mass, double height){
        super(mass, height);
    }

    @Override
    public double calculate() throws IllegalArgumentException {
        if (dataAreValid()) {
            return mass / (height / 100 * height / 100);
        } else {
            throw new IllegalArgumentException("Invalid data");
        }
    }

    @Override
    protected boolean dataAreValid(){
        return getMass() > 0 && getHeight() > 0;
    }

}
