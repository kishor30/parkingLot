package com.parkinglot.service;

import java.util.HashMap;
import java.util.Map;

import com.parkinglot.exception.ParkingLotException;

public class ParkingService {




	    private static ParkingService parkingService;
	    private static Map<Integer,Slots> parkingslotsMap;
	    static  int count =0;

	    static int commandCallCounter(){
	        count++;
	        return count;
	    }

	  
	    private ParkingService(int slotNumbers){
	    parkingslotsMap = new HashMap<Integer, Slots>();
	    for (int i = 1; i <= slotNumbers; i++) {
	        parkingslotsMap.put(i, new Slots(i));
	    }
	}




	static ParkingService getInstance(int slotNumbers){
	    if(parkingService ==  null){
	    	parkingService=new ParkingService(slotNumbers);
	    }
	    return parkingService;
	}


	    int fillAvailableSlot() throws ParkingLotException {
	        int nextAvailableSlotNumber = -1;
	        for (int i = 1; i <= parkingslotsMap.size(); i++) {
	            Slots s = parkingslotsMap.get(i);
	            if (s.status) {
	                nextAvailableSlotNumber = s.slotNumbers;
	                s.status = false;
	                break;
	            }
	        }
	        if (nextAvailableSlotNumber != -1) {
	            return nextAvailableSlotNumber;
	        } else {
	            throw new ParkingLotException("Sorry, parking lot is full");
	        }
	    }


	    void emptySlot(int slotNumber) {
	        if (parkingslotsMap.containsKey(slotNumber)) {
	            if (parkingslotsMap.get(slotNumber).status) {
	                throw new IllegalStateException("The slot is already empty");
	            } else {
	                parkingslotsMap.get(slotNumber).status = true;
	            }
	        } else {
	            throw new IllegalStateException("The slot number is invalid");
	        }
	    }


	    private class Slots {
	    private int slotNumbers;
	    private boolean status;

	        public Slots(int slotNumbers) {
	            this.slotNumbers = slotNumbers;
	            this.status = true;
	        }
	    }




	



}
