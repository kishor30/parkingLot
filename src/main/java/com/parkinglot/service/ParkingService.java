package com.parkinglot.service;

import java.util.HashMap;
import java.util.Map;

import com.parkinglot.exception.ParkingLotException;

public class ParkingService {




	    private static ParkingService parkingService;
	    private static Map<Integer,Slot> parkingslotsMap;
	    static  int count =0;

	    static int commandCallCounter(){
	        count++;
	        return count;
	    }

	  //create parking with n capacity
	    protected ParkingService(int slotNumbers){
	    parkingslotsMap = new HashMap<Integer, Slot>();
	    for (int i = 1; i <= slotNumbers; i++) {
	        parkingslotsMap.put(i, new Slot(i));
	    }
	}




	static ParkingService getInstance(int slotNumbers){
	    if(parkingService ==  null){
	    	parkingService=new ParkingService(slotNumbers);
	    }
	    return parkingService;
	}

//allocating parking is slot is empty otherwise parking full
	    int fillAvailableSlot() throws ParkingLotException {
	        int nextAvailableSlotNumber = -1;
	        for (int i = 1; i <= parkingslotsMap.size(); i++) {
	            Slot s = parkingslotsMap.get(i);
	            if (s.isSlotEmpty) {
	                nextAvailableSlotNumber = s.slotNumbers;
	                s.isSlotEmpty = false;
	                break;
	            }
	        }
	        if (nextAvailableSlotNumber != -1) {
	            return nextAvailableSlotNumber;
	        } else {
	            throw new ParkingLotException("Sorry, parking lot is full");
	        }
	    }

//vacating slot once car is exited
	    void emptySlot(int slotNumber) {
	        if (parkingslotsMap.containsKey(slotNumber)) {
	            if (parkingslotsMap.get(slotNumber).isSlotEmpty) {
	                throw new IllegalStateException("The slot is already empty");
	            } else {
	                parkingslotsMap.get(slotNumber).isSlotEmpty = true;
	            }
	        } else {
	            throw new IllegalStateException("The slot number is invalid");
	        }
	    }


	    private class Slot {
	    private int slotNumbers;
	    private boolean isSlotEmpty;

	        public Slot(int slotNumbers) {
	            this.slotNumbers = slotNumbers;
	            this.isSlotEmpty = true;
	        }
	    }




	



}
