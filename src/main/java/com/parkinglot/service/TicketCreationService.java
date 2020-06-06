package com.parkinglot.service;



import com.parkinglot.exception.ParkingLotException;


public class TicketCreationService {

	    private static TicketCreationService ticketService;
	    private ParkingService parkingService;



	    TicketCreationService(ParkingService parkingService) {
	        this.parkingService = parkingService;
	    
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


	

}
