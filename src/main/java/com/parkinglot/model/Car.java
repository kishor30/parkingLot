package com.parkinglot.model;

public class Car implements Vehicle {
	
	  private String registrationNumber;
	   

	    public Car(String registrationNumber) {



	        if(registrationNumber == null) {
	            throw new IllegalArgumentException("Registration Number  and Color information is missing");
	        }
	        else {
	            this.registrationNumber = registrationNumber;
	           
	        }
	    }
	  

	    public String getRegistrationNumber() {
	        return this.registrationNumber;
	    }



}
