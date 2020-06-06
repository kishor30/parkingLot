package com.parkinglot;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;


import com.parkinglot.service.CommandExecutionService;


public class ParkingLotApplication {

	
	public static void main(String[] args) throws Exception {
		  
		CommandExecutionService commandService = CommandExecutionService.getInstance();
		  String filePath = args[0];
          File inputFile = new File(filePath);
          System.out.println("Reading the file:"+inputFile);
          try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
        	    while (true) {
                    String commandStatement = bufferedReader.readLine();
                    System.out.println("commands are:"+commandStatement);
                    if (commandStatement == null || "exit".equalsIgnoreCase(commandStatement)) {
                        break;
                    } else {
                         boolean executionSuccess = commandService.runCommand(commandStatement);
                      
                        if (!executionSuccess) {
                            break;
                        }
                    }
                }
        	}
          
      
	}

}
