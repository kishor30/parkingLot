package com.parkinglot.model;

public class AllocationStatus {

	    private int slot;
	    private String registrationNumber;
	    private String color;
		
		public AllocationStatus(int slot, String registrationNumber, String color) {
			super();
			this.slot = slot;
			this.registrationNumber = registrationNumber;
			this.color = color;
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
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}



		@Override
		public String toString() {
			return "AllocationStatus [slot=" + slot + ", registrationNumber=" + registrationNumber + ", color=" + color
					+ "]";
		}
	    
		
	    
}
