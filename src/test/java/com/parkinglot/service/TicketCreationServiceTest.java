package com.parkinglot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.parkinglot.model.AllocationStatus;
import com.parkinglot.model.Car;
import com.parkinglot.model.Ticket;



public class TicketCreationServiceTest {

	 @Test
	    public void integrationTest() {
	        TicketCreationService ticketService = TicketCreationService.createInstance(6);

	        int slot1 = ticketService.issueParkingTicket(new Car("KA-01-HH-1234"));
	        Assert.assertEquals(1, slot1);

	        int slot2 = ticketService.issueParkingTicket(new Car("KA-01-HH-9999"));
	        Assert.assertEquals(2, slot2);

	        int slot3 = ticketService.issueParkingTicket(new Car("KA-01-BB-0001"));
	        Assert.assertEquals(3, slot3);

	        int slot4 = ticketService.issueParkingTicket(new Car("KA-01-HH-7777"));
	        Assert.assertEquals(4, slot4);

	        int slot5 = ticketService.issueParkingTicket(new Car("KA-01-HH-2701"));
	        Assert.assertEquals(5, slot5);

	        int slot6 = ticketService.issueParkingTicket(new Car("KA-01-HH-3141"));
	        Assert.assertEquals(6, slot6);

	        ticketService.exitVehicle("KA-01-HH-3141",4);

	        int slot7 = ticketService.issueParkingTicket(new Car("KA-01-P-333"));
	        Assert.assertEquals(6, slot7);




	        try {
	            ticketService.issueParkingTicket(new Car("DL-12-AA-9999"));
	            Assert.assertTrue("parking lot is full", false);
	        } catch (Exception e) {
	            Assert.assertEquals("Sorry, parking lot is full", e.getMessage());
	        }

	        Map<Integer, Ticket> ticketDataMap = new HashMap<>();
	        ticketDataMap.put(6, new Ticket(6,10,new Car("KA-01-HH-3141")));

	        int slotNumber = ticketService.getSlotFromRegistrationNumber("KA-01-HH-3141",ticketDataMap);
	        Assert.assertEquals(6, slotNumber);

	        try {
	            int slotNumber2 = ticketService.getSlotFromRegistrationNumber("MH-04-AY-1111",ticketDataMap);
	            Assert.assertTrue("should throw not found exception", true);
	        } catch (Exception e) {
	            Assert.assertEquals("Registration number MH-04-AY-1111 not found.", e.getMessage());
	        }
	    }

	    @Test
	    public void issueParkingTicketWithValidVehicle() {
	       TicketCreationService ticketService = new TicketCreationService(new MockParkingLot(3));

	        int slot1 = ticketService.issueParkingTicket(new Car("KA-01-HH-1234"));
	        Assert.assertEquals(3, slot1);
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void issueParkingTicketWithNullVehicle() {
	       TicketCreationService ticketService = new TicketCreationService(new MockParkingLot(3));
	        int slot1 = ticketService.issueParkingTicket(null);
	    }

	    @Test
	    public void exitVehicleWithValidRegistrationNumber() {
	        MockParkingLot DemoParkingLot = new MockParkingLot(3);
	        TicketCreationService ticketService = new TicketCreationService(DemoParkingLot);
	        ticketService.issueParkingTicket(new Car("KA-01-HH-3141"));

	        ticketService.exitVehicle("KA-01-HH-3141",3);
	        Assert.assertEquals(3, DemoParkingLot.emptySlotNumber);
	        
	    }

	    @Test
	    public void exitVehicleWithInvalidNumber() {
	        MockParkingLot DemoParkingLot = new MockParkingLot(3);
	      TicketCreationService ticketService = new TicketCreationService(DemoParkingLot);

	        try {
	            ticketService.exitVehicle("KA-01-HH-3141",3);
	            Assert.assertTrue("should throw vehicle not found exception", false);
	        } catch (Exception e) {
	            Assert.assertEquals("Registration number KA-01-HH-3141 not found.", e.getMessage());
	        }
	    }

	    

	    @Test
	    public void getSlotFromRegistrationNumberWithValidRegistrationNumber() {
	        TicketCreationService ticketService = new TicketCreationService(new ParkingService(5));
	        Map<Integer, Ticket> ticketDataMap = new HashMap<>();
	        ticketDataMap.put(1, new Ticket(1,10,new Car("KA-01-HH-3141")));
	        ticketService.issueParkingTicket(new Car("KA-01-HH-3141"));
	       

	        int slot1 = ticketService.getSlotFromRegistrationNumber("KA-01-HH-3141",ticketDataMap);
	        Assert.assertEquals(1, slot1);
	    }

	    @Test
	    public void getParkingCost() {
	 
	    	TicketCreationService ticketService = new TicketCreationService(new ParkingService(5));

	        float ActualcostforParkingSlot = ticketService.getParkingCost(4);
	        Assert.assertEquals(30, ActualcostforParkingSlot, 0.0f);
	    }
	    @Test
	    public void getSlotNumberFromRegistrationNumberWithInvalidRegistrationNumber() {
	        TicketCreationService ticketService = new TicketCreationService(new ParkingService(5));
	        Map<Integer, Ticket> ticketDataMap = new HashMap<>();
	        ticketDataMap.put(1, new Ticket(1,10,new Car("KA-01-HH-3141")));
	        try {
	            ticketService.getSlotFromRegistrationNumber(null,ticketDataMap);
	            Assert.assertTrue("should throw Invalid registration number exception", false);
	        } catch (Exception e) {
	            Assert.assertEquals("Invalid registration number.", e.getMessage());
	        }
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void getSlotNumberFromRegistrationNumberWithNullRegistrationNumber() {
	        TicketCreationService ticketService = new TicketCreationService(new ParkingService(5));
	        Map<Integer, Ticket> ticketDataMap = new HashMap<>();
	        ticketDataMap.put(1, new Ticket(1,10,new Car("KA-01-HH-3141")));
	        ticketService.getSlotFromRegistrationNumber(null,ticketDataMap);
	    }

	    

	    @Test
	    public void getStatus() {
	        TicketCreationService ticketService = new TicketCreationService(new ParkingService(1));
	        ticketService.issueParkingTicket(new Car("KA-01-HH-2701"));

	        List<AllocationStatus> statusResponseList = ticketService.getStatus();
	        Assert.assertEquals(1, statusResponseList.size());
	        Assert.assertEquals("KA-01-HH-2701", statusResponseList.get(0).getRegistrationNumber());
	       
	        Assert.assertEquals(1, statusResponseList.get(0).getSlot());

	    }

	   
	    private class MockParkingLot extends ParkingService {

	        private int nextAvailableSlotNumber;
	        private int emptySlotNumber;

	        MockParkingLot (int slotNumber) {
	            super(1);
	            this.nextAvailableSlotNumber = slotNumber;
	            this.emptySlotNumber = slotNumber;
	        }

	        @Override
	        int fillAvailableSlot() {
	            return nextAvailableSlotNumber;
	        }

	        @Override
	        void emptySlot(int slotNumber) {
	            this.emptySlotNumber = slotNumber;
	        }
	    }
}
