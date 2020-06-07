package com.parkinglot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.parkinglot.exception.ParkingLotException;
import com.parkinglot.model.AllocationStatus;
import com.parkinglot.model.Car;
import com.parkinglot.model.Vehicle;


public class TicketCreationService {

	    private static TicketCreationService ticketService;
	    private ParkingService parkingService;
	    private static Map<Integer, Ticket> ticketDataMap;
	    private float parkedHours;


	    TicketCreationService(ParkingService parkingService) {
	        this.parkingService = parkingService;
	        ticketDataMap = new HashMap<Integer, Ticket>();
	    
	    }


	    static TicketCreationService createInstance(int numberOfSlots) {
	        if(numberOfSlots < 1) {
	            throw new ParkingLotException("Number of slots cannot be less than 1");
	        }
	        if (ticketService == null) {
	            ParkingService parkingLot = ParkingService.getInstance(numberOfSlots);
	            ticketService = new TicketCreationService(parkingLot);
	        }
	        return ticketService;
	    }


	    static TicketCreationService getInstance() {
	        if(ticketService== null) {
	            throw new IllegalStateException("Parking Lot is not initialized");
	        }
	        return ticketService;
	    }

	    int issueParkingTicket(Vehicle vehicle) {
	        if (vehicle == null) {
	            throw new IllegalArgumentException("Vehicle cannot be null");
	        }



	            boolean check= true;

	                for (Ticket ticket : ticketDataMap.values()) {
	                    if(vehicle.getRegistrationNumber().equals(ticket.vehicle.getRegistrationNumber())){
	                        check= false;
	                    }
	                 }
	                if(check) {
	                    int assignedSlotNumber = parkingService.fillAvailableSlot();
	                    Ticket ticket = new Ticket(assignedSlotNumber,parkedHours, vehicle);

	                    ticketDataMap.put(assignedSlotNumber, ticket);
	                    return assignedSlotNumber;
	                }
	                else{
	                    throw new ParkingLotException("Same Registration Number Vehicles are not allowed");
	                }


	    }
	    
	    Ticket exitVehicle(String registrationNumber,float hoursParked) {
	    	int slotNumber = this.getSlotFromRegistrationNumber(registrationNumber, ticketDataMap);
	        if (ticketDataMap.containsKey(slotNumber)) {
	            parkingService.emptySlot(slotNumber);
	            ticketDataMap.remove(slotNumber);
	            return new Ticket(slotNumber,this.getParkingCost(hoursParked),new Car(registrationNumber)); 
	            
	        } else {
	            throw new ParkingLotException("Registration number "+ registrationNumber +" not found.");
	        }
	    }
	    float getParkingCost(float hoursParked) {
	    	if(hoursParked ==1 || hoursParked == 2) {
	    		return 10;
	    	}
	    	else{
	    		return ((hoursParked - 2)*10)+10;
	    	}
	    }
	    int getSlotFromRegistrationNumber(String registrationNumber,Map<Integer, Ticket> ticketMap) {
	    	Integer slot = -1;
	    	Set<Entry<Integer,Ticket>> ticketValues = ticketMap.entrySet();
	    	for(Entry<Integer, Ticket> ticket:ticketValues) {
	    			if(registrationNumber.equalsIgnoreCase(ticket.getValue().vehicle.getRegistrationNumber()))
	    			{ 
	    		
	    				slot= ticket.getKey();
	    			
	    			}
	    		
	    	}
	    	return slot;
	    }
	    List<AllocationStatus> getStatus() {
	        List<AllocationStatus>allocationStatusList= new ArrayList<AllocationStatus>();
	        for (Ticket ticket : ticketDataMap.values()) {
	            allocationStatusList.add(new AllocationStatus(ticket.slotNumber, ticket.vehicle.getRegistrationNumber()));
	        }
	        return allocationStatusList;
	    }
	    
	    public class Ticket {
	        int slotNumber;
	        float parkingCost;
	        Vehicle vehicle;

	        Ticket(int slotNumber,float parkingCost, Vehicle vehicle) {
	            this.slotNumber = slotNumber;
	            this.vehicle = vehicle;
	            this.parkingCost = parkingCost;
	        }
	    }

	

}
