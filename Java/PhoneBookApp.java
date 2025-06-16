public class PhoneBookApp{


	public static List<String[]> addContact(String firstName, String lastName, String phoneNumber, String[] contactToBeAdded, List<String[]> phoneBook){

		BigInteger checkPhoneNumber = new BigInteger(phoneNumber);

		if(phoneNumber.length() < 11)throw new IllegalArgumentException("Enter complete phone number.");

		if(firstName.isBlank() || lastName.isBlank() || middleName.isBlank())throw new IllegalArgumentException("This is a required field. Cannot be left blank.");	

		if(!firstName.matches("^[a-zA-Z]+$") || !lastName.matches("^[a-zA-Z]+$"))throw new InputMismatchException("Name field may contain invalid characters.");
		
		contactToBeAdded[0] = firstName + " " + lastName;
		contactToBeAdded[1] = phoneNumber;
		phoneBook.add(contactToBeAdded);
		return phoneBook;
	}

	public static List<String[]> removeContact(String name, List<String[]> phoneBook){
		
		if(phoneBook.size() > 0){

			for(String[] contacts : phoneBook){

				if(Arrays.asList(contacts).contains(name))phoneBook.remove(contacts)
			}
		}
		else{throw new IllegalArgumentException("Phonebook is empty!");

		return phoneBook;
	}

	public static List<String> findContactByPhoneNumber(String phoneNumber, List<String[]> phoneBook){

		BigInteger checkPhoneNumber = new BigInteger(phoneNumber);
		List<String> foundContact = new ArrayList<>();
		
		for(String[] eachContact : phoneBook){

			if(Arrays.asList(eachContact).contains(phoneNumber))foundContact.append(eachContact);

		}
		if(foundContact.size() == 0)throw new IllegalArgumentException("No contacts found.");

		return foundContact;

	}


	public static List<String> findContactByFirstName(String firstName, List<String[]> phoneBook){

		List<String> foundContacts = new ArrayList<>();

		for(String[] eachContact : phoneBook){

			List<String> eachContactToList = Arrays.asList(eachContact);

			if(eachContactToList.get(0).toLowerCase().contains(firstName.toLowerCase()))foundContacts.append(eachContact);
		}

		if(foundContacts.size() == 0)throw new IllegalArgumentException("No contacts found.");

		return foundContacts;


	}

	public static List<String> findContactByLastName(String lastName, List<String[]> phoneBook){

		List<String> foundContacts = new ArrayList<>();

		for(String[] eachContact : phoneBook){

			List<String> eachContactToList = Arrays.asList(eachContact);

			if(eachContactToList.get(0).toLowerCase().contains(lastName.toLowerCase()))foundContacts.append(eachContact);
		}

		if(foundContacts.size() == 0)throw new IllegalArgumentException("No contacts found.");

		return foundContacts;


	}

	public static List<String[]> editContact(String fullName, List<String[]> phoneBook){

		for(String[] eachContact : phoneBook){

			List<String> eachContactToList = Arrays.asList(eachContact);
	
			if(eachContactToList.get(0).equalsIgnoreCase(fullName)){

				eachContact


			}
		}

		return phoneBook;

	}



	public static void main(String[] args){

		String firstName = "";
		String lastName = "";
		String phoneNumber = "";
		boolean phoneBook = true;
		String phoneBookMenu = """
1. Add contact
2. Remove contact
3. Find contact by phone number
4. Find contact by first name
5. Find contact by last name
6. Edit contact
"""
		while(phoneBook){

			System.out.println(phoneBookMenu);
			String select = input.next();
			switch(select){

				case "1":
						boolean addContact = true;
						while(addContact){
							
						break;

			}




	}

}