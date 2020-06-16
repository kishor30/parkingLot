# Parking Lot  problem Design  Document

This is the solution to problem statement to create an automatic ticketing system for car parking.

## Problem Statement:


- A parking lot can hold up to 'n' cars at any given point in time
- Each slot is given a number starting at 1 increasing with increasing distance from the entry point in steps of one
- Create an automated ticketing system that allows the use of parking lot
- When a car enters the parking lot, a ticket issued is issued to the driver
- The ticket issuing process includes documenting the registration number and the color of the car and allocating an available parking slot to the car before actually handing over a ticket to the driver
- The customer should be allocated a parking slot which is nearest to the entry
- At the exit the customer returns the ticket which then marks the slot they were using as being available
- Total parking charge should be calculated as per the parking time
	- Charge applicable is $10 for first 2 hours and $10 for every additional hour
	- Slot number in which a car with a given registration number is parked
	
- This project supports only mode of input commands read from file 
	- It accepts a filename as a parameter at the command prompt and reads the commands from that file

- Sample Test Cases & outputs are provided at the end of this file.

## Getting Started with the Setup

- Instructions to setup the project in local machine and run the test file

### System Requirements:
- Java 1.8
- Apache Maven 
- Git (For Version Control)

### Running the Test Cases:
- Run the command bin/setup to install the build the project (Java, Maven assumed to be pre-installed on the system)
   ````
   ./bin/setup
   or directly executing shell script file in bin folder setup.sh will do building the project,installation running, unit tests 
   ````
### Running with Bash
````
./bin/parking_lot java -jar ../target/parking_lot-0.0.1-SNAPSHOT.jar <path of input file>
directly executing shell script file in bin folder parking_lot.sh will run the program taking filename as command line arguments 

````
### Versioning
   
 The folder also contains a .git file. Please check the version history using "git log" & "git diff" commands

   
### Built With

- [Maven](https://maven.apache.org/) - Build/Dependency Management


### Sample Test Cases & Outputs for Reference
- Sample test Cases given with problem are incorporated into /src/main/resources/commandFile.txt 
- To change input file edit the main file configuration => edit configuration => Arguments=> the program arguments(put path of new file as value).
- For illustration output according to command is shown

create_parking_lot 6
Created a parking lot with 6 slots

park KA-01-HH-1234
Allocated slot number: 1

park KA-01-HH-9999
Allocated slot number: 2

park KA-01-BB-0001
Allocated slot number: 3

park KA-01-HH-7777
Allocated slot number: 4

park KA-01-HH-2701
Allocated slot number: 5

park KA-01-HH-3141
Allocated slot number: 6

leave KA-01-HH-3141 4
registration number KA-01-HH-3141 with Slot number 6 is free with charge 30.0

status
Slot No.    Registration No
   1           KA-01-HH-1234 
   2           KA-01-HH-9999 
   3           KA-01-BB-0001 
   4           KA-01-HH-7777 
   5           KA-01-HH-2701 
   

park KA-01-P-333   
Allocated slot number: 6

park DL-12-AA-9999
Sorry, parking lot is full

leave KA-01-HH-1234 4
registration number KA-01-HH-1234 with Slot number 1 is free with charge 30.0

leave KA-01-BB-0001 6
registration number KA-01-BB-0001 with Slot number 3 is free with charge 50.0

leave DL-12-AA-9999 2
Registration number DL-12-AA-9999 not found.

park KA-09-HH-0987
Allocated slot number: 1

park CA-09-IO-1111
Allocated slot number: 3

park KA-09-HH-0123
Sorry, parking lot is full

status
Slot No.    Registration No
   1           KA-09-HH-0987 
   2           KA-01-HH-9999 
   3           CA-09-IO-1111 
   4           KA-01-HH-7777 
   5           KA-01-HH-2701 
   6           KA-01-P-333 
	

			
### Test Cases Usage:
 The Exceptions are handled keeping actual real life scenario in mind:
 
 - Parking Lot Once Created Cannot be override (Singleton class).

 - Empty Slot Cannot be Emptied again.
 - Car with same Registration number is not allowed.
 - Car number not available in any slot then should throw error.
 - Slot will not be given if registration number of the vehicle is not available.
 - Same slot is not provided for two different cars.
 - For Integration testing commands are checked for any invalid command or argument.
 - No Slots to be allocated if parking lot is full.
