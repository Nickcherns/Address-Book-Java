package main;

import java.util.ArrayList;

public class AddressBook {
	private ArrayList<Entry> addressBook = new ArrayList<Entry>();
	private String name;
	
	public AddressBook() {
	};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	// add new entry to address book with duplicate check
	public void addEntry(String firstName, String lastName, 
				String phoneNumber, String emailAddress) {
		Entry newEntry = new Entry(firstName, lastName, phoneNumber, emailAddress);
		for(int i=0; i<addressBook.size(); i++) {
			Entry entryItem = addressBook.get(i);
			// checks entries for duplicates
			if(entryItem.getEmailAddress().toLowerCase().equals(emailAddress.toLowerCase())) {
				System.out.println("This email address is already associated with an entry, please try again");
				Menu.addEntry();
			}
		}
		addressBook.add(newEntry);	
		System.out.println("\n-------- Entry Added: --------");
		System.out.println(newEntry.toString());
		System.out.println("------------------------------");
	};
	
	// remove entries
	public void removeEntry(Entry entry) {
		addressBook.remove(entry);
		System.out.println("\n|------------------------------|");
		System.out.println("	*Entry Removed*");
		System.out.println("|------------------------------|");
	}; 
	
	// search addressBook by first name
	public void searchEntriesByFirstName(String firstName) {
		int matches = 0;
		// prints all possible matches
		for(int i=0; i<addressBook.size(); i++) {
			Entry entry = addressBook.get(i);
			if(entry.getFirstName().toLowerCase().contains(firstName.toLowerCase())) {
				System.out.println("----------- Entry: -----------");
				System.out.println(entry.toString());
				System.out.println("------------------------------");
				matches+=1;
			}
		}
		// if no matches then restart searchBook with invalid value
		if(matches == 0) {
			Menu.searchBook(-2);
		}
	};
	
	// search addressBook by last name
	public void searchEntriesByLastName(String lastName) {
		int matches = 0;
		// prints all possible matches
		for(int i=0; i<addressBook.size(); i++) {
			Entry entry = addressBook.get(i);
			if(entry.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
				System.out.println("----------- Entry: -----------");
				System.out.println(entry.toString());
				System.out.println("------------------------------");
				matches+=1;
			}
		}
		// if no matches then restart searchBook with invalid value
		if(matches == 0) {
			Menu.searchBook(-2);
		}
	};
	
	//search addressBook by phone number
	public void searchEntriesByPhoneNumber(String phoneNumber) {
		int matches = 0;
		// prints all possible matches
		for(int i=0; i<addressBook.size(); i++) {
			Entry entry = addressBook.get(i);
			if(entry.getPhoneNumber().contains(phoneNumber)) {
				System.out.println("----------- Entry: -----------");
				System.out.println(entry.toString());
				System.out.println("------------------------------");
				matches+=1;
			}
		}
		// if no matches then restart searchBook with invalid value
		if(matches == 0) {
			Menu.searchBook(-2);
		}
	};
	
	// search entries by email address (reverts back to searchBook)
	public void searchEntriesByEmailAddress(String emailAddress) {
		int matches = 0;
		// prints all possible matches
		for(int i=0; i<addressBook.size(); i++) {
			Entry entry = addressBook.get(i);
			if(entry.getEmailAddress().toLowerCase().contains(emailAddress.toLowerCase())) {
				System.out.println("----------- Entry: -----------");
				System.out.println(entry.toString());
				System.out.println("------------------------------");
				matches+=1;
			}
		}
		// if no matches then restart searchBook with invalid value
		if(matches == 0) {
			Menu.searchBook(-2);
		}
	};
	
	// search entries by email address (reverts back to removeEntry)
	public Entry removeEntriesByEmailAddress(String emailAddress) {
		Entry entry = new Entry();
		int matches = 0;
		// checks entries for possible matches
		for(int i=0; i<addressBook.size(); i++) {
			String entryEmail = addressBook.get(i).getEmailAddress();
			if(entryEmail.toLowerCase().equals(emailAddress.toLowerCase())) {
				entry = addressBook.get(i);
				matches+=1;
			}
		}
		if(matches == 0) {
			System.out.println("No Entry Found, Please try again....");
			Menu.removeEntry();
		}
		return entry;
	};
	
	// print each entry of the address book
	public void printAddressBook() {
		
		if(addressBook.isEmpty()) {
			System.out.println("\n|------------------------------|");
			System.out.println("  >> Address Book is empty <<");
			System.out.println("|------------------------------|\n");
			return;
		}
		
		System.out.println("\n|------------------------------|");
		System.out.println("    Printing Address Book:");
		System.out.println("|------------------------------|");
		

		for(int i=0; i<addressBook.size(); i++) {
			System.out.print(addressBook.get(i).toString());
	        if (i < addressBook.size() - 1) {
	            System.out.println("\n");
	        }
		}
		System.out.println("\n|------------------------------|\n");;
	}
	
	// deletes the address book
	public void deleteAddressBook() {
		addressBook.clear();
	}

	public int size() {
		return addressBook.size();
	}

	public Object get(int i) {
		return addressBook.get(i);
	}
	

}
