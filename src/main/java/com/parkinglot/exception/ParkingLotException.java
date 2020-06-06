package com.parkinglot.exception;

public class ParkingLotException extends RuntimeException {

	  private String errorMessage;


	    public ParkingLotException(String message) {
	        this.errorMessage = message;
	    }

	    @Override
	    public String getMessage() {
	        return errorMessage;
	    }

	    public void setMessage(String message) {
	        this.errorMessage = message;
	    }
}
