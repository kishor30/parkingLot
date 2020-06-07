package com.parkinglot.service;

import org.junit.Assert;
import org.junit.Test;


public class ParkingLotServiceTest {

	    @Test
	    public void parkInEmptySlot() {
	    	ParkingService parkingLot = new ParkingService(2);

	        int slot1 = parkingLot.fillAvailableSlot();
	        Assert.assertEquals(1, slot1);

	        int slot2 = parkingLot.fillAvailableSlot();
	        Assert.assertEquals(2, slot2);
	    }

	    @Test
	    public void checkParkInFullParkingLot() {
	        ParkingService parkingLot = new ParkingService(1);

	        int slot = parkingLot.fillAvailableSlot();
	        Assert.assertEquals(1, slot);

	        try {
	            parkingLot.fillAvailableSlot();
	            Assert.assertTrue("should throw parking lot is full", false);
	        } catch (Exception e) {
	            String message = e.getMessage();
	            Assert.assertEquals("Sorry, parking lot is full", message);
	        }
	    }

	    @Test
	    public  void parkinglotAlreadyCreated(){
	        ParkingService parkingLot = new ParkingService(3);
	    try{
	        ParkingService parking = new ParkingService(3);

	        }
	    catch(Exception e){
	        Assert.assertEquals("Parking Lot can only be created once",e.getMessage());
	    }
	    }
	    @Test
	    public void emptySlotWithValidSlotNumber() {
	        ParkingService parkingLot = new ParkingService(3);

	        int slot = parkingLot.fillAvailableSlot();
	        Assert.assertEquals(1, slot);

	        int slot2 = parkingLot.fillAvailableSlot();
	        Assert.assertEquals(2, slot2);

	        int slot3 = parkingLot.fillAvailableSlot();
	        Assert.assertEquals(3, slot3);

	        parkingLot.emptySlot(2);
	        int slot4 = parkingLot.fillAvailableSlot();
	        Assert.assertEquals(2, slot4);

	        parkingLot.emptySlot(1);
	        int slot5 = parkingLot.fillAvailableSlot();
	        Assert.assertEquals(1, slot5);
	    }

	    @Test
	    public void emptySlotWithInvalidSlotNumber() {
	        ParkingService parkingLot = new ParkingService(2);

	        int slot = parkingLot.fillAvailableSlot();
	        Assert.assertEquals(1, slot);

	        int slot2 = parkingLot.fillAvailableSlot();
	        Assert.assertEquals(2, slot2);

	        try {
	            parkingLot.emptySlot(3);
	            Assert.assertTrue("should throw slot number is invalid exception", false);
	        } catch (Exception e) {
	            String message = e.getMessage();
	            Assert.assertEquals("The slot number is invalid", message);
	        }
	    }

	    @Test
	    public void emptySlotWhichIsAlreadyEmpty() {
	        ParkingService parkingLot = new ParkingService(2);

	        int slot = parkingLot.fillAvailableSlot();
	        Assert.assertEquals(1, slot);

	        try {
	            parkingLot.emptySlot(2);
	            Assert.assertTrue("should throw slot already empty exception", false);
	        } catch (Exception e) {
	            String message = e.getMessage();
	            Assert.assertEquals("The slot is already empty", message);
	        }
	    }
	

}
