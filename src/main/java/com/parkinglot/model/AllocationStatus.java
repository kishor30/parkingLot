package com.parkinglot.model;

public class AllocationStatus {

	    private int slot;
	    private String registrationNumber;

		
		public AllocationStatus(int slot, String registrationNumber) {
			super();
			this.slot = slot;
			this.registrationNumber = registrationNumber;

		}
		
		
	    
	    public AllocationStatus() {
			super();
			// TODO Auto-generated constructor stub
		}



		public int getSlot() {
			return slot;
		}
	
		public void setSlot(int slot) {
			this.slot = slot;
		}
		public String getRegistrationNumber() {
			return registrationNumber;
		}
		public void setRegistrationNumber(String registrationNumber) {
			this.registrationNumber = registrationNumber;
		}
	



		@Override
		public String toString() {
			return "AllocationStatus [slot=" + slot + ", registrationNumber=" + registrationNumber 
					+ "]";
		}
	    
		
	    
}
