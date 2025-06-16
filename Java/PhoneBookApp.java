import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.math.BigInteger;

public class PhoneBookApp{


	public static List<String[]> addContact(String firstName, String lastName, String phoneNumber, String[] contactToBeAdded, List<String[]> phoneBook){

		BigInteger checkPhoneNumber = new BigInteger(phoneNumber);

		if(phoneNumber.length() < 11)throw new IllegalArgumentException("Enter complete phone number.");

		if(firstName.isBlank() || lastName.isBlank())throw new IllegalArgumentException("This is a required field. Cannot be left blank.");	

		if(!firstName.matches("^[a-zA-Z]+$") || !lastName.matches("^[a-zA-Z]+$"))throw new InputMismatchException("Name field may contain invalid characters.");
		
		contactToBeAdded[0] = firstName + " " + lastName;
		contactToBeAdded[1] = phoneNumber;
		phoneBook.add(contactToBeAdded);
		return phoneBook;
	}

	public static List<String[]> removeContact(String name, List<String[]> phoneBook){
		
		if(phoneBook.size() > 0)phoneBook.removeIf(contact -> Arrays.asList(contact).contains(name));
			
		else{throw new IllegalArgumentException("Phonebook is empty!");}

		return phoneBook;
	}

	public static String findContactByPhoneNumber(String phoneNumber, List<String[]> phoneBook){

		BigInteger checkPhoneNumber = new BigInteger(phoneNumber);
		List<String[]> foundContact = new ArrayList<>();
		String ifFound = "";
		
		for(String[] eachContact : phoneBook){

			if(Arrays.asList(eachContact).contains(phoneNumber))foundContact.add(eachContact);

		}
		if(foundContact.size() == 0)throw new IllegalArgumentException("No contacts found.");

		for(String[] found : foundContact)ifFound = Arrays.toString(found);
		return ifFound;

	}


	public static String findContactByFirstName(String firstName, List<String[]> phoneBook){

		List<String[]> foundContacts = new ArrayList<>();
		String ifFound = "";

		for(String[] eachContact : phoneBook){

			List<String> eachContactToList = Arrays.asList(eachContact);

			if(eachContactToList.get(0).toLowerCase().contains(firstName.toLowerCase()))foundContacts.add(eachContact);
		}

		if(foundContacts.size() == 0)throw new IllegalArgumentException("No contacts found.");

		for(String[] found : foundContacts)ifFound = Arrays.toString(found);
		return ifFound;


	}

	public static String findContactByLastName(String lastName, List<String[]> phoneBook){

		List<String[]> foundContacts = new ArrayList<>();
		String ifFound = "";

		for(String[] eachContact : phoneBook){

			List<String> eachContactToList = Arrays.asList(eachContact);

			if(eachContactToList.get(0).toLowerCase().contains(lastName.toLowerCase()))foundContacts.add(eachContact);
		}

		if(foundContacts.size() == 0)throw new IllegalArgumentException("No contacts found.");

		for(String[] found : foundContacts)ifFound = Arrays.toString(found);
		return ifFound;

	}

	public static List<String[]> editContact(String fullName, List<String[]> phoneBook){

		Scanner input = new Scanner(System.in);

		for(String[] eachContact : phoneBook){

			List<String> eachContactToList = Arrays.asList(eachContact);
	
			if(eachContactToList.get(0).equalsIgnoreCase(fullName)){

				System.out.print("Which field do you want to edit.\nEnter 1 for name or 2 for phone number");
				int select = input.nextInt();
				if(select == 1) eachContact[0] = input.next();
				else if(select == 2) eachContact[1] = input.next();

			}
		}
		return phoneBook;
	}



	public static void main(String[] args){


		Scanner input = new Scanner(System.in);
		String firstName = "";
		String lastName = "";
		String phoneNumber = "";
		String fullName = "";
		List<String[]> phoneBook = new ArrayList<>();
		boolean phoneBookRunner = true;
		String phoneBookMenu = """
1. Add contact
2. Remove contact
3. Find contact by phone number
4. Find contact by first name
5. Find contact by last name
6. Edit contact
7. Exit
""";
		while(phoneBookRunner){

			System.out.println(phoneBookMenu);
			String select = input.next();
			switch(select){

				case "1":
						boolean addContact = true;
						while(addContact){
							String[] contactToBeAdded = new String[2];
							firstName = "Adebola";
							lastName = "Foley";
							phoneNumber = "08103195663";
							phoneBook = addContact(firstName, lastName, phoneNumber, contactToBeAdded, phoneBook);
							boolean backTo = true;
							while(backTo){
								System.out.println("Enter 0 to return");
								String backToLastPage = input.next();
								switch(backToLastPage){	
									case "0":
										addContact = false;
										backTo = false;
										break;
									default:
										System.out.print("Invalid input.");

								}
							}

						}
							
						break;
				case "2":
					
						boolean removeCon = true;
						while(removeCon){
							fullName = "Adebola Foley";
							phoneBook = removeContact(fullName, phoneBook);
							boolean backTo = true;
							while(backTo){
								System.out.println("Enter 0 to return");
								String backToLastPage = input.next();
								switch(backToLastPage){	
									case "0":
										removeCon = false;
										backTo = false;
										break;
									default:
										System.out.print("Invalid input.");

								}
							}
							
						}
						break;

				case "3":

						boolean findContactByNumber = true;
						while(findContactByNumber){
							phoneNumber = "08103195663";
							System.out.println(findContactByPhoneNumber(phoneNumber, phoneBook));
							boolean backTo = true;
							while(backTo){
								System.out.println("Enter 0 to return");
								String backToLastPage = input.next();
								switch(backToLastPage){	
									case "0":
										findContactByNumber = false;
										backTo = false;
										break;
									default:
										System.out.print("Invalid input.");

								}
							}

						}
				
						break;


				case "4":

						boolean findContactByFirst = true;
						while(findContactByFirst){
							firstName = "Adebola";
							System.out.println(findContactByFirstName(firstName, phoneBook));
							boolean backTo = true;
							while(backTo){
								System.out.println("Enter 0 to return");
								String backToLastPage = input.next();
								switch(backToLastPage){	
									case "0":
										findContactByFirst = false;
										backTo = false;
										break;
									default:
										System.out.print("Invalid input.");

								}
							}
						}

						break;

				case "5":

						boolean findContactByLast = true;
						while(findContactByLast){
							lastName = "Foley";
							System.out.println(findContactByLastName(lastName, phoneBook));
							boolean backTo = true;
							while(backTo){
								System.out.println("Enter 0 to return");
								String backToLastPage = input.next();
								switch(backToLastPage){	
									case "0":
										findContactByLast = false;
										backTo = false;
										break;
									default:
										System.out.print("Invalid input.");

								}
							}
						}

						break;

				case "6":
					
						boolean editCon = true;
						while(editCon){
							fullName = "Adebola Foley";
							phoneBook = editContact(fullName, phoneBook);
							boolean backTo = true;
							while(backTo){
								System.out.println("Enter 0 to return");
								String backToLastPage = input.next();
								switch(backToLastPage){	
									case "0":
										editCon = false;
										backTo = false;
										break;
									default:
										System.out.print("Invalid input.");

								}
							}
						}
						break;

				case "7":
						phoneBookRunner = false;
						break;

				default:
					System.out.println("Invalid input.");
			}

		}




	}

}
