package com.parkinglot.service;


import java.util.List;
import com.parkinglot.exception.ParkingLotException;
import com.parkinglot.model.AllocationStatus;
import com.parkinglot.model.Car;
import com.parkinglot.service.TicketCreationService.Ticket;

public class CommandExecutionService {
	
	private static CommandExecutionService commandExecutionService;
	
	private CommandExecutionService() {
		
	}
//singleton class to return only instance of commandService
	public static CommandExecutionService getInstance() {
		
		if(commandExecutionService==null) {
			commandExecutionService = new CommandExecutionService();
		}
		return commandExecutionService;
	}
	
	
    private enum CommandLine {
        create_parking_lot, park, leave, status 
    }
    

   private interface Command {
        public void commandValidation();

        public String executeCommand();
    }
   
   private CommandLine getCommand(String command){
       CommandLine commandLine = null;

       if(command== null){
           System.out.println("Invalid Input from file");
       }
       else{
           String[] commandArray =command.split(" ");
           if("".equals(commandArray[0])){
               System.out.println("no command found line is empty");
           }
           else {
               try {
                   commandLine = CommandLine.valueOf(commandArray[0]);
               } catch (Exception e) {
                   System.out.println("Command not Available");
               }
           }
       }
       return commandLine;
   }
   
   public boolean runCommand(String commandFromFile) {

       CommandLine commandLine = getCommand(commandFromFile);
       System.out.println("commandLine is:"+commandLine);
       if (commandLine == null) {
           return false;
       }
       String[] commandwithArgument = commandFromFile.split(" ");
       Command command=null;

       switch (commandLine) {
           case create_parking_lot:
               command = new CreateParkingLot(commandwithArgument);
               break;
           case park:
               command = new Park(commandwithArgument);
               break;
           case leave:
               command = new Leave(commandwithArgument);
               break;
           case status:
               command = new CheckStatus(commandwithArgument);
               break;
    
           default:
               System.out.println("could not process the command");
               return false;
       }
       try {
           command.commandValidation();
       } catch (IllegalArgumentException e) {
           System.out.println("Please provide a valid argument");
           return false;
       }

       String output = "";
       try {
           output = command.executeCommand();
       } catch (ParkingLotException e) {
           System.out.print(e.getMessage());
       } catch(Exception e) {
           System.out.println("unable to get the issue");
           e.printStackTrace();
           return false;
       }
       System.out.println(output);
       return true;

 
     
   }
   
   private class CreateParkingLot implements Command {
       private String[] commandwithArgument;
       
     
       CreateParkingLot(String[] commandSentence) {
    	   System.out.println("creating parking lot as per command:"+commandSentence);
           commandwithArgument = commandSentence;
       }
       public void commandValidation(){

           if( ParkingService.commandCallCounter()>1){
               throw new ParkingLotException("Parking Lot once created cannot be Override");
           }
           if (commandwithArgument.length != 2) {
               throw new IllegalArgumentException("create_parking_lot should have legal arguments number");
           }
       }

       public String executeCommand() {
    	   
           int numberOfSlots = Integer.parseInt(commandwithArgument[1]);
           TicketCreationService.createInstance(numberOfSlots);
           ParkingService.commandCallCounter();
           return "Created a parking lot with " + commandwithArgument[1] + " slots";
       }
   }
   
   private class Park implements Command {
       private String[] commandwithArgument;

       Park(String[] commandSentence) {
           commandwithArgument = commandSentence;
       }

       public void commandValidation() {

           if (commandwithArgument.length != 2) {
               throw new IllegalArgumentException("park command should have exactly 2 arguments");
           }
       }

       public String executeCommand() {
    	   TicketCreationService ticketService = TicketCreationService.getInstance();
           int allocatedSlotNumber = ticketService.issueParkingTicket(new Car(commandwithArgument[1]));
           return "Allocated slot number: " + allocatedSlotNumber;
       }
   }

  

   private class Leave implements Command {
       private String[] commandwithArgument;

       Leave(String[] commandSentence) {
           commandwithArgument = commandSentence;
       }

       public void commandValidation() {
           if (commandwithArgument.length != 3) {
               throw new IllegalArgumentException("leave command should have proper arguments");
           }
       }

       public String executeCommand() {
           TicketCreationService ticketService = TicketCreationService.getInstance();
           Ticket exitTicket =  ticketService.exitVehicle(commandwithArgument[1],Integer.parseInt(commandwithArgument[2]));
           return "registration number"+exitTicket.vehicle.getRegistrationNumber()+ " with Slot number " + exitTicket.slotNumber 
        		   + " is free with charge"+exitTicket.parkingCost;
       }
   }
   private class CheckStatus implements Command {
       private String[] commandwithArgument;

       CheckStatus(String[] commandSentence) {
           commandwithArgument = commandSentence;
       }

       public void commandValidation() {
           if (commandwithArgument.length != 1) {
               throw new IllegalArgumentException("status command should have no arguments");
           }
       }

       public String executeCommand() {
           TicketCreationService ticketService = TicketCreationService.getInstance();
           List<AllocationStatus> statusResponseList = ticketService.getStatus();

           StringBuilder outputStringBuilder = new StringBuilder("Slot No.    Registration No    Colour");
           for (AllocationStatus allocationStatus: statusResponseList) {
               outputStringBuilder.append("\n").append(allocationStatus);
           }
           return outputStringBuilder.toString();
       }
   }


}
