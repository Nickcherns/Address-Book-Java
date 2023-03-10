package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
	
	private static File addressBookFile;
	private static Scanner input = new Scanner(System.in);
	
	
	// create new addressBook file
	public static void createFile(String bookName, AddressBook addressBook) {
		addressBookFile = new File(bookName);
		try {
			if(addressBookFile.createNewFile()) {
				System.out.println(" New Address Book Created: " + addressBookFile.getName());
				
			} else {
				//if the file already exists, then read file
				System.out.println(" File already exists");
				readFile(bookName, addressBook);
			}
		} catch (IOException e) {
			System.out.println(" Experienced Error creating Address Book");
			e.printStackTrace();
		}
	}
	
	// write addressBook to file
	public static void writeFile(String bookName, AddressBook addressBook) {
		try {
			FileWriter addressBookWriter = new FileWriter(bookName);
			for(int i=0; i<addressBook.size(); i++) {
				addressBookWriter.write(addressBook.get(i).toString() + "\n\n");
		    }
			addressBookWriter.close();
			System.out.println("  Address Book saved...");
		} catch (IOException e1) {
			System.out.println(" Experienced Error writing to Address Book");
			e1.printStackTrace();
		}
	}
	
	// read file and add to an Address Book
	public static AddressBook readFile(String bookName, AddressBook addressBook) {
		addressBookFile = new File(bookName);
		Scanner fileReader;
		ArrayList<String> fileArray = new ArrayList<>();
		ArrayList<String> fileData = new ArrayList<>();
		try {
			fileReader = new Scanner(addressBookFile);
			System.out.println("\n|------------------------------");
			System.out.println("  File " + "'"+ addressBookFile.getName() +"'" + " successfully loaded...");
			System.out.println("  Accessed file at: " + 
								addressBookFile.getAbsolutePath());
			System.out.println("|------------------------------\n");
			// read lines and trim
			while(fileReader.hasNextLine()) {
				fileArray.add(fileReader.nextLine().trim());
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Experienced Error reading file...");
			e.printStackTrace();
		}
		// split fileArray by ':' and add to new list
		for(int i=0; i<fileArray.size(); i++) {
			String[] fileLine = new String[2];
			fileLine = fileArray.get(i).split(":");
			// adds separated lines to fileData
			for(int j=0; j<fileLine.length;j++) {
				fileData.add(fileLine[j].trim());
				// deletes empty values
				if(fileLine[j].isEmpty()) {
					fileData.remove(fileLine[j]);
				}
			}
		}
		// remove labels from data
		while(fileData.remove("Name"));
		while(fileData.remove("Phone Number"));
		while(fileData.remove("Email Address"));
		
		// separate each name into first and last name 
		for(int i=0; i<fileData.size(); i+=3) {
			String[] nameData = new String[2];
			String firstNameData = null;
			String lastNameData = null;
			nameData = fileData.get(i).split(" ");
			for(int j=0; j<nameData.length; j+=2) {
				firstNameData = nameData[j];
				lastNameData = nameData[j+1];
			}
			// add each entry to the address book
			addressBook.addEntry(firstNameData, lastNameData,
										fileData.get(i+1), 
										fileData.get(i+2));
		}
		return addressBook;
	}
	
	// int check
	public static int intCheck() {
		while (!input.hasNextInt()) {
	        input.next();
	        System.out.println("  Invalid input. Please try again.");
	    }
		int number = input.nextInt();
		input.nextLine();
	    return number;
	}

	public static void main(String[] args) {
		AddressBook newAddressBook = new AddressBook();
		String addressBookName = newAddressBook.getName();
		
		System.out.println("\n\n|-------> Address Book <-------|");
		System.out.println("|------------------------------|");
		System.out.println("  1) Load existing Address Book");
		System.out.println("|------------------------------|");
		System.out.println("  2) Create new Address Book");
		System.out.println("|------------------------------|");
		// menu selection
		System.out.print("\n Enter your Selection (1 or 2): ");
		int userValue = intCheck();
		// menu routing
		switch (userValue) {
			case 1: // load existing address book
				System.out.println("\n|------------------------------");
				System.out.print(" Enter existing Address Book name: ");
				newAddressBook.setName(Menu.checkStringInput());
				addressBookName = newAddressBook.getName();
				readFile(addressBookName, newAddressBook);
				break;
			case 2: // create new address book
				System.out.println("  Enter an address book name: ");
				newAddressBook.setName(Menu.checkStringInput());
				addressBookName = newAddressBook.getName();
				createFile(addressBookName, newAddressBook);
				writeFile(addressBookName, newAddressBook);
				break;
			default: // invalid values
				System.out.println(" Invalid selection, please try again...");
				userValue = intCheck();
		}
		
		// initialize menu
		Menu menu = new Menu(newAddressBook);
		// program start
		menu.mainMenu();
	}

}

 /* ------TO DO-------
 *  
 *  
 * ~HIGH PRIORITY:
 *  
 * ~MEDIUM PRIORITY:
 *  
 * ~LOW PRIORITY:
 *  X> email validation needs work (requires @ but not '.')
 *  
 * ~FINAL CHECK:
 * 
 */