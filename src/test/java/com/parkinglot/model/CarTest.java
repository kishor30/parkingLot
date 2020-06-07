package com.parkinglot.model;

import org.junit.Assert;
import org.junit.Test;



public class CarTest {
    @Test
    public void validTest() {
        Car car = new Car(
                "KA-01-HH-3141");
       
        Assert.assertEquals("KA-01-HH-3141", car.getRegistrationNumber());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testWithNullRegistrationNumber() {
        new Car(null);
    }


	
}

