package com.parkinglot.service;


import com.parkinglot.exception.ParkingLotException;

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
             //  command = new Park(commandStringArray);
               break;
           case leave:
              // command = new Leave(commandStringArray);
               break;
           case status:
               //command = new CheckStatus(commandStringArray);
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

}
