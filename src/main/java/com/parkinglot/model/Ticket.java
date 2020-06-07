package com.parkinglot.model;

public class Ticket {
  
        private int slotNumber;
        private float parkingCost;
        private Vehicle vehicle;

        
        public Ticket() {
			super();
			// TODO Auto-generated constructor stub
		}

		public int getSlotNumber() {
			return slotNumber;
		}

		public void setSlotNumber(int slotNumber) {
			this.slotNumber = slotNumber;
		}

		public float getParkingCost() {
			return parkingCost;
		}

		public void setParkingCost(float parkingCost) {
			this.parkingCost = parkingCost;
		}

		public Vehicle getVehicle() {
			return vehicle;
		}

		public void setVehicle(Vehicle vehicle) {
			this.vehicle = vehicle;
		}

		public Ticket(int slotNumber,float parkingCost, Vehicle vehicle) {
            this.slotNumber = slotNumber;
            this.vehicle = vehicle;
            this.parkingCost = parkingCost;
        }

		@Override
		public String toString() {
			return "Ticket [slotNumber=" + slotNumber + ", parkingCost=" + parkingCost + ", vehicle=" + vehicle + "]";
		}
    
}
