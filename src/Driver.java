import java.util.ArrayList;

import java.util.Scanner;

import java.lang.Integer;

import static java.lang.Integer.parseInt;

public class Driver {

    // instance variables (add more as needed)
    private static ArrayList<Ship> shipList = new ArrayList();
    private static ArrayList<Cruise> cruiseList = new ArrayList();
    private static ArrayList<Passenger> passengerList = new ArrayList();


    public static void main(String[] args) {

        initializeShipList();       // initial ships
        initializeCruiseList();     // initial cruises
        initializePassengerList();  // initial passengers
        
        Scanner scnr = new Scanner(System.in);

        // add loop and code here that accepts and validates user input
        // and takes the appropriate action. include appropriate
        // user feedback and redisplay the menu as needed
        
        displayMenu();
        
        String userSelection = scnr.next().toUpperCase();
        scnr.nextLine();

        //use a while loop since the number of iterations is unknown.
        //user can exit the loop by pressing "X."
        while (!userSelection.equalsIgnoreCase("X")) {
            try {
                switch (userSelection) {
                    case "1":
                        addShip(scnr);
                        break;
                    case "2":
                        editShip();
                        break;
                    case "3":
                        if (shipAvailable()) {
                            addCruise(scnr);
                        }
                        else {
                            System.out.println("There are no ships in service");
                        }
                        break;
                    case "4":
                        editCruise();
                        break;
                    case "5":
                        addPassenger();
                        break;
                    case "6":
                        editPassenger();
                        break;
                    case "A":
                        printShipList("name");
                        break;
                    case "B":
                        printShipList("active");
                        break;
                    case "C":
                        printShipList("full");
                        break;
                    case "D":
                        printCruiseList("list");
                        break;
                    case "E":
                        printCruiseList("details");
                        break;
                    case "F":
                        printPassengerList();
                        break;
                    case "X":
                        break;
                    // any other case results in an invalid entry.
                    default:
                        throw new Exception("Invalid Entry. Please try again");
                }
            }
            catch (Exception excpt){
                System.out.println(excpt.getMessage());
            }

            displayMenu();
            userSelection = scnr.next().toUpperCase();
            scnr.nextLine();
        }

        System.out.println("Goodbye");

        return;

    }

    private static boolean shipAvailable() {
		return false;
	}

	// hardcoded ship data for testing
    // Initialize ship list
    public static void initializeShipList() {
        add("Candy Cane", 20, 40, 10, 60, true);
        add("Peppermint Stick", 10, 20, 5, 40, true);
        add("Bon Bon", 12, 18, 2, 24, false);
        add("Candy Corn", 12, 18, 2, 24, false);
    }

    // hardcoded cruise data for testing
    // Initialize cruise list
    public static void initializeCruiseList() {
        Cruise newCruise = new Cruise("Southern Swirl", "Candy Cane", "Miami", "Cuba", "Miami");
        cruiseList.add(newCruise);
    }

    // hardcoded cruise data for testing
    // Initialize passenger list
    public static void initializePassengerList() {
        Passenger newPassenger1 = new Passenger("Neo Anderson", "Southern Swirl", "STE");
        passengerList.add(newPassenger1);

        Passenger newPassenger2 = new Passenger("Trinity", "Southern Swirl", "STE");
        passengerList.add(newPassenger2);

        Passenger newPassenger3 = new Passenger("Morpheus", "Southern Swirl", "BAL");
        passengerList.add(newPassenger3);
    }

    // custom method to add ships to the shipList ArrayList
    public static void add(String tName, int tBalcony, int tOceanView,
                           int tSuite, int tInterior, boolean tInService) {
        Ship newShip = new Ship(tName, tBalcony, tOceanView, tSuite, tInterior, tInService);
        shipList.add(newShip);
    }


    public static void printShipList(String listType) {
        // printShipList() method prints list of ships from the
        // shipList ArrayList. There are three different outputs
        // based on the listType String parameter:
        // name - prints a list of ship names only
        // active - prints a list of ship names that are "in service"
        // full - prints tabbed data on all ships

        if (shipList.size() < 1) {
            System.out.println("\nThere are no ships to print.");
            return;
        }
        if (listType == "name") {
            System.out.println("\n\nSHIP LIST - Name");
            for (int i = 0; i < shipList.size(); i++) {
                System.out.println(shipList.get(i));
            }
        } else if (listType == "active") {
            System.out.println("\n\nSHIP LIST - Active");

            int numberofShips = 0;
            for (int i = 0; i < shipList.size(); i++) {
                
                if (shipList.get(i).getInService()) {
                    System.out.println(shipList.get(i).toString()); 
                    numberofShips++;
                }
            }

            // if there are no ships in the inventory, print the following message.
            if (numberofShips < 1) {
                System.out.println("There are no cruise ships in service at this time.");
            }

        } else if (listType == "full") {
            System.out.println("\n\nSHIP LIST - Full");
            System.out.println("-----------------------------------------------");
            System.out.println("                    Number of Rooms     In");
            System.out.print("SHIP NAME           Bal OV  Ste Int     Service");
            System.out.println("\n-----------------------------------------------");
            for (Ship eachShip: shipList)
                eachShip.printShipData();

        } else
            System.out.println("\n\nError: List type not defined.");
    }

    public static void printCruiseList(String listType) {
        if (cruiseList.size() < 1) {
            System.out.println("\nThere are no cruises to print.");
            return;
        }
        if (listType == "list") {
            System.out.println("\n\nCRUISE LIST");
            for (int i=0; i < cruiseList.size(); i++) {
                System.out.println(cruiseList.get(i));
            }
        } else if (listType == "details") {
            System.out.println("\n\nCRUISE LIST - Details");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("                                      |----------------------PORTS-----------------------|");
            System.out.print("CRUISE NAME         SHIP NAME           DEPARTURE           DESTINATION         RETURN");
            System.out.println("\n-----------------------------------------------------------------------------------------");
            for (Cruise eachCruise: cruiseList)
                eachCruise.printCruiseDetails();
        } else
            System.out.println("\n\nError: List type not defined.");
    }

    public static void printPassengerList() {
        if (passengerList.size() < 1) {
            System.out.println("\nThere are no passengers to print.");
            return;
        }
        System.out.println("\n\nPASSENGER LIST");
        System.out.println("-----------------------------------------------------");
        System.out.print("PASSENGER NAME      CRUISE              ROOM TYPE");
        System.out.println("\n-----------------------------------------------------");
        for (Passenger eachPassenger: passengerList)
            eachPassenger.printPassenger();
    }

    // display text-based menu
    public static void displayMenu() {

        System.out.println("\n\n");
        System.out.println("\t\t\tLuxury Ocean Cruise Outings");
        System.out.println("\t\t\t\t\tSystem Menu\n");
        System.out.println("[1] Add Ship            [A] Print Ship Names");
        System.out.println("[2] Edit Ship           [B] Print Ship In Service List");
        System.out.println("[3] Add Cruise          [C] Print Ship Full List");
        System.out.println("[4] Edit Cruise         [D] Print Cruise List");
        System.out.println("[5] Add Passenger       [E] Print Cruise Details");
        System.out.println("[6] Edit Passenger      [F] Print Passenger List");
        System.out.println("[x] Exit System");
        System.out.println("\nEnter a menu selection: ");
    }

    // Add a New Ship
    public static void addShip(Scanner scnr) {

    	//initialize shipName
    	String shipName = "";
    	//declare instance variables
        int roomBalcony;
        int roomOceanView;
        int roomSuite;
        int roomInterior;
        
        String inServiceUserInput;  
        
        boolean inService = false;  
        boolean validEntry;

        /* Ensure the ship does not already exist in the system.
         * User inputs the ship's name.
         * Implement a do-while loop with exception handling.
         */

        do {
            validEntry = true;
            try {
                System.out.println("Enter ship name: ");          
                shipName = scnr.nextLine();

                for (int i = 0; i < shipList.size(); ++i) {
                    if (shipName.equalsIgnoreCase(shipList.get(i).getShipName())) {
                        throw new Exception("Invalid entry. Please enter a different ship name");
                    }
                }
                if (shipName.isEmpty() || shipName.isBlank()) {
                    throw new Exception("Invalid entry. Ship name must be entered.");
                }
            }
            catch (Exception excpt) {
                System.out.println("Bad value exception. " + excpt.getMessage());
                validEntry = false;
            }
        } while (!validEntry);

        // All class variables need to be populated.
        roomBalcony = validateRoomInput(scnr, "balcony");
        roomOceanView = validateRoomInput(scnr, "ocean view");
        roomSuite = validateRoomInput(scnr, "suite");
        roomInterior = validateRoomInput(scnr, "interior");

        // prompt the user whether ship is in service. 
        do {
            validEntry = true;
            try {
                System.out.println("Is this ship currently available for cruises? (Y/N) ");
                inServiceUserInput = scnr.nextLine();
                // throw an exception if user does not enter 'Y' or 'N'
                if (!inServiceUserInput.equalsIgnoreCase("Y") && !inServiceUserInput.equalsIgnoreCase("N")) {
                    throw new Exception("Please enter Y or N.");
                }
                if (inServiceUserInput.equalsIgnoreCase("Y")) {
                    inService = true;
                }
                else {
                    inService = false;
                }
            }
            catch (Exception excpt) {
                System.out.println("Invalid Entry. " + excpt.getMessage());
                validEntry = false;
            }
        } while (!validEntry);

        /* adds a new ship object and includes all class variables. 
         * ArrayList updates.
         */
        
        add(shipName, roomBalcony, roomOceanView, roomSuite, roomInterior, inService);

        return;
    }

    private static int validateRoomInput(Scanner scnr, String string) {
		return 0;
	}

	// Edit an existing ship
    public static void editShip() {

        // This method does not need to be completed
        System.out.println("The \"Edit Ship\" feature is not yet implemented.");

    }

    // Add a New Cruise
    public static void addCruise(Scanner scnr) {

    	//declare instance variables
        String cruiseName = "";
        String cruiseShipName = "";
        String departurePort = "";
        String destination = "";
        String returnPort = "";
        boolean Entry;         
        int shipCount;         
                                    
        boolean shipInService;      
                                    
        boolean shipHasCruise;      
                                   

        /* Ensure the cruise does not already exist in our system.
         * Prompts user for cruise name.
         * Use a do-while loop with extension handling.
         */
        do {
            Entry = true;
            try {
                System.out.println("Enter cruise name: ");
                cruiseName = scnr.nextLine();

                // loop through the existing cruiseList array
                for (int i = 0; i < cruiseList.size(); ++i) {
                    
                    // throw exception if cruise name already exists.
                    if (cruiseName.equalsIgnoreCase(cruiseList.get(i).getCruiseName())) {
                        throw new Exception("Invalid entry. Cruise name already exists. Cruise name must be unique.");
                    }
                }
                // throw an exception if no name is input by user.
                if (cruiseName.isEmpty() || cruiseName.isBlank()) {
                    throw new Exception("Invalid entry. Please enter a cruise name.");
                }
            }
            catch (Exception excpt) {
                System.out.println("Bad value exception. " + excpt.getMessage());
                Entry = false;
            }
        } while (!Entry);

        // validate that all class variables are populated
        departurePort = validateStringInput(scnr, "departure port");
        destination = validateStringInput(scnr, "cruise destination");
        returnPort = validateStringInput(scnr, "return port");
        
        //validate that ship is in service
        do {
            Entry = true;
            try {
                shipCount = 0;
                shipInService = false;
                shipHasCruise = false;
                cruiseShipName = validateStringInput(scnr, "cruise ship name");
                for (int i = 0; i < shipList.size(); ++i) {
                    if (cruiseShipName.equalsIgnoreCase(shipList.get(i).getShipName())) {
                        shipCount ++;
                        if (shipList.get(i).getInService()) {
                            shipInService = true;
                        }
                        // throw an exception if ship is already assigned to a cruise.
                        shipHasCruise = hasCruise(cruiseShipName);
                    }
                }
                if (shipCount == 0) {
                    throw new Exception ("Please try again.  Please enter a valid ship name.");
                }
                if (!shipInService) {
                    throw new Exception("Please try again. Ship is not in service. Please enter a valid ship.");
                }
                if (shipHasCruise) {
                    throw new Exception("Please try again. Ship is already assigned to a cruise. Please enter a valid ship.");
                }
            }
            catch (Exception excpt) {
                System.out.println("Bad value exception. " + excpt.getMessage());
                Entry = false;
            }
        } while (!Entry);

        // add the new cruise to the cruiseList ArrayList
        // adds a new Cruise object, includes all class variables, updates appropriate ArrayList
        Cruise newCruise = new Cruise(cruiseName, cruiseShipName, departurePort, destination, returnPort);
        cruiseList.add(newCruise);

        return;

    }

    private static boolean hasCruise(String cruiseShipName) {
		return false;
	}

	private static String validateStringInput(Scanner scnr, String string) {
		return null;
	}

	// Edit an existing cruise
    public static void editCruise() {

        // This method does not need to be completed
        System.out.println("The \"Edit Cruise\" feature is not yet implemented.");

    }

    // Add a New Passenger
    public static void addPassenger() {

        Scanner newPassengerInput = new Scanner(System.in);
        System.out.println("Enter the new passenger's name: ");
        String newPassengerName = newPassengerInput.nextLine();

        // ensure new passenger name does not already exist
        for (Passenger eachPassenger: passengerList) {
            if (eachPassenger.getPassengerName().equalsIgnoreCase(newPassengerName)) {
                System.out.println("That passenger is already in the system. Exiting to menu...");
                return; // quits addPassenger() method processing
            }
        }

        // get cruise name for passenger
        System.out.println("Enter cruise name: ");
        String newCruiseName = newPassengerInput.nextLine();

        // ensure cruise exists
        for (Cruise eachCruise: cruiseList) {
            if (eachCruise.getCruiseName().equalsIgnoreCase(newCruiseName)) {
                // cruise does exist
            } else {
                System.out.println("That cruise does not exist in the system. Exiting to menu...");
                return; // quits addPassenger() method processing
            }
        }

        // get room type
        System.out.println("Enter Room Type (BAL, OV, STE, or INT: ");
        String room = newPassengerInput.nextLine();
        // validate room type
        if ((room.equalsIgnoreCase("BAL")) || (room.equalsIgnoreCase("OV")) ||
                (room.equalsIgnoreCase("STE")) || (room.equalsIgnoreCase("INT"))) {
            // validation passed - add passenger
            Passenger newPassenger = new Passenger(newPassengerName, newCruiseName, room.toUpperCase());
            passengerList.add(newPassenger);
        } else {
            System.out.println("Invalid input. Exiting to menu...");
            return; // quits addPassenger() method processing
        }
    }

    // Edit an existing passenger
    public static void editPassenger() {

        // This method does not need to be completed
        System.out.println("The \"Edit Passenger\" feature is not yet implemented.");

    }

    // Method to check if input is a number
    public static boolean isANumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i)) == false)
                return false;
        }
        return true;
    }

}
