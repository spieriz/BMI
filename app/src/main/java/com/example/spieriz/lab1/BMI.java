package com.example.spieriz.lab1;

/**
 * Created by spieriz on 2018-03-08.
 */

public abstract class BMI {
    protected double mass;
    protected double height;
    public BMI(double mass, double height){
        this.mass = mass;
        this.height = height;
    }

    protected double getMass(){
        return mass;
    }

    protected double getHeight(){
        return height;
    }

    abstract public double calculate();
    protected boolean dataAreValid() { return getMass() > 0 && getHeight() > 0; }
}
