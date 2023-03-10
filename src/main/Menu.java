package main;

import java.util.Scanner;

public class Menu {
	private static Scanner input = new Scanner(System.in);
	private static AddressBook ADDRESS_BOOK;
	
	// constructor
	public Menu(AddressBook addressBook) {
		Menu.ADDRESS_BOOK = addressBook;
	};

	// main menu setup
	public void mainMenu() {
		System.out.println("\n|--------| Main Menu |---------|");
		System.out.println("|------------------------------|");
		System.out.println("    1) Add an entry");
		System.out.println("|------------------------------|");
		System.out.println("    2) Remove an entry");
		System.out.println("|------------------------------|");
		System.out.println("    3) Search for an entry");
		System.out.println("|------------------------------|");
		System.out.println("    4) Print Address Book");
		System.out.println("|------------------------------|");
		System.out.println("    5) Delete Book");
		System.out.println("|------------------------------|");
		System.out.println("    6) Quit");
		System.out.println("|------------------------------|");
		System.out.print("  Enter your Selection (1-6): ");
		int menuOption = checkIntInput();
		System.out.println("|------------------------------|");
		menuSelection(menuOption);
	}
	
	// mainMenu routing
	public static void menuSelection(int menuOption) {
		switch (menuOption) {
			case 1: // 1) Add an entry
				addEntry();
				break;
			case 2: // 2) Remove an entry
				removeEntry();
				break;
			case 3: // 3) Search for a specific entry
				searchBook(-1);
				break;
			case 4: // 4) Print Address Book
				printBook();
				break;
			case 5: // 5) Delete Book
				deleteBook();
				break;
			case 6: // 6) Quit
				quitProgram();
			default: // default
				System.out.println(" Invalid input. Please try again.");	
				menuSelection(checkIntInput());  // restart menu selection
		}
	}

	public static void addEntry() {
		System.out.print("  First Name: ");
		String firstName = checkStringInput();
		System.out.println("|------------------------------|");
		System.out.print("  Last Name: ");
		String lastName = checkStringInput();
		System.out.println("|------------------------------|");
		System.out.print("  Phone Number: ");
		String phoneNumber = checkPhoneNumber(); 
		System.out.println("|------------------------------|");
		System.out.print("  Email Address: ");
		String emailAddress = checkEmailInput();
		System.out.println("|------------------------------|");
		// add entry to ADDRESS_BOOK
		ADDRESS_BOOK.addEntry(firstName, lastName, phoneNumber, emailAddress);
		System.out.println("\n Add another entry?");
		System.out.println("   Enter 'y' for YES");
		System.out.println("   Enter 'n' for NO");
		
		boolean searchAgain = yesOrNoPrompt();
		if(searchAgain == true) {
			addEntry();
		} else {
			backToMainMenu();
		}
	}

	public static void removeEntry() {
		System.out.println("Enter an entry's email to remove: ");
		Entry foundEntry = ADDRESS_BOOK.removeEntriesByEmailAddress(checkEmailInput());
		
		System.out.println("\n-------- Entry Found: --------");
		System.out.println(foundEntry.toString());
		System.out.println("------------------------------");
		System.out.println("\n Are you sure you want to remove this entry:");
		System.out.println("   Enter 'y' for YES");
		System.out.println("   Enter 'n' for NO");
		boolean removalConformation = yesOrNoPrompt();
		
		if(removalConformation == true) {
			ADDRESS_BOOK.removeEntry(foundEntry);
		} else {
			backToMainMenu();
		}
		
		System.out.println(" Remove another entry?");
		System.out.println("   Enter 'y' for YES");
		System.out.println("   Enter 'n' for NO");
		
		boolean searchAgain = yesOrNoPrompt();
		if(searchAgain == true) {
			removeEntry();
		} else {
			backToMainMenu();
		}
	}

	public static void searchBook(int selection) {
		
		while(selection == -2) {
			System.out.println("\n Couldn't find an entry associated with your search...");
			System.out.print(" Press Enter to start a new Search...");
			input.nextLine();
			break;
		}
		
		System.out.println("\n|------------------------------|");
		System.out.println("    Search by:");
		System.out.println("|------------------------------|");
		System.out.println("   1) First Name");
		System.out.println("|------------------------------|");
		System.out.println("   2) Last Name");
		System.out.println("|------------------------------|");
		System.out.println("   3) Phone Number");
		System.out.println("|------------------------------|");
		System.out.println("   4) Email Address");
		System.out.println("|------------------------------|");
		System.out.println("   5) Back to Main Menu");
		System.out.println("|------------------------------|\n");
		System.out.print("  Enter your Selection (1-5): ");
		
		selection = checkIntInput();
		switch(selection) {
			case 1:
				System.out.print("\n  Enter First Name: ");
				String firstName = checkStringInput();
				System.out.println();
				ADDRESS_BOOK.searchEntriesByFirstName(firstName);
				break;
			case 2:
				System.out.print("\n  Enter Last Name: ");
				String lastName = checkStringInput();
				System.out.println();
				ADDRESS_BOOK.searchEntriesByLastName(lastName);
				break;
			case 3:
				// int-String check 
				System.out.print("\n  Enter Phone Number: ");
				String phoneNumber = input.nextLine().trim();
			    while (!phoneNumber.toLowerCase().matches(".*\\d.*") || phoneNumber.isEmpty()) { // check if inputString contains any digits
			        System.out.println("  Invalid input. Please enter a valid input.");
			        phoneNumber = input.nextLine().trim();
			    }
				System.out.println();
				ADDRESS_BOOK.searchEntriesByPhoneNumber(phoneNumber);
				break;
			case 4:
				System.out.print("\n  Enter Email Address: ");
				String emailAddress = checkStringInput();
				System.out.println();
				ADDRESS_BOOK.searchEntriesByEmailAddress(emailAddress);
				break;
			case 5:
				backToMainMenu();
			default:
				searchBook(-2);
		}
		System.out.println(" Start a new search?");
		System.out.println("    Enter 'y' for YES");
		System.out.println("    Enter 'n' for NO");
		
		boolean searchAgain = yesOrNoPrompt();
		if(searchAgain == true) {
			searchBook(-1);
		} else {
			backToMainMenu();
		}
	}

	public static void printBook() {
		ADDRESS_BOOK.printAddressBook();
		
		System.out.println(" Print the address book again?");
		System.out.println("    Enter 'y' for YES");
		System.out.println("    Enter 'n' for NO");
		boolean searchAgain = yesOrNoPrompt();
		if(searchAgain == true) {
			printBook();
		} else {
			backToMainMenu();
		}
	}

	// clear currentBook and return
	public static void deleteBook() {
		ADDRESS_BOOK.deleteAddressBook();
		System.out.println("  Address Book successfully deleted...");
		System.out.println("|------------------------------|");
		System.out.print("  Press Enter to go back to the Main Menu");
		input.nextLine();
		backToMainMenu();
	}
	
	public static void backToMainMenu() {
		Menu menu = new Menu(ADDRESS_BOOK);
		menu.mainMenu();
	}

	public static void quitProgram() {
		Program.writeFile(ADDRESS_BOOK.getName(), ADDRESS_BOOK);
		System.exit(0);
	}
	
	// restart searchBook function or back to main menu
	public static boolean yesOrNoPrompt() {
		char input = checkCharacterInput();
		
		while(input != 'Y' && input != 'N') {
			System.out.println("  Invalid input. Please enter a valid character.");
	        input = checkCharacterInput();
		}
		if(input == 'Y') {
			return true;
		} 
		return false;
	}
	

	//	~VALIDATIONS~ 
	//		 (INT / STRING / CHAR / EMAIL / PHONE NUMBER)
	public static int checkIntInput() {
		while (!input.hasNextInt()) {
	        input.next();
	        System.out.println("  Invalid input. Please try again.");
	    }
		int number = input.nextInt();
		input.nextLine();
	    return number;
	}
	
	// STRING_VALIDATION
	public static String checkStringInput() {
	    String string = input.nextLine().trim();
	    while (string.toLowerCase().matches(".*\\d.*") || string.isEmpty()) { // check if inputString contains any digits
	        System.out.println("  Invalid input. Please enter a valid string.");
	        string = input.nextLine().trim();
	    }
	    return string;
	}
	
	// CHARACTER_VALIDATION
	public static char checkCharacterInput() {
		char inputLetter;
		String inputString = input.nextLine().trim().toUpperCase();
		// prevents empty input
		while(inputString.isEmpty()) {
			System.out.println("  Invalid input. Please enter a valid character.");
			inputString = input.nextLine().trim().toUpperCase();
		}
		// get first character of inputString
		inputLetter = inputString.charAt(0);
		// check if input starts with a valid character
		while(!Character.toString(inputLetter).matches("^[A-Z0-9]*$")) {
			System.out.println("  Invalid input. Please enter a valid character.");
	        inputLetter = input.nextLine().trim().toUpperCase().charAt(0);
		}
		return inputLetter;
	}
	
	// EMAIL_VALIDATION
	public static String checkEmailInput() {
		String emailAddress = input.nextLine().trim();
		String matchParameter = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
	    
	    while(!emailAddress.toLowerCase().matches(matchParameter)
	    		|| emailAddress.isEmpty()) {
	    	System.out.println("  Invalid Email Address, Please try again:");
	        emailAddress = input.nextLine().trim();
	    }
	    return emailAddress;
	}
	
	// PHONE_NUMBER_VALIDATION
	public static String checkPhoneNumber() {
		String phoneNumber = input.nextLine().trim();
		
		while(!phoneNumber.matches(".*\\d.*")
				|| phoneNumber.length() != 10
				|| phoneNumber.isEmpty()) {
			System.out.println("  Invalid input. Please enter another Phone Number:");
			phoneNumber = input.nextLine().trim();
		}
		return phoneNumber;
	}
}
