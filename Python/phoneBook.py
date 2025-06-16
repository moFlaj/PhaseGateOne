from decimal import Decimal

def add_contact(first_name, last_name, phone_number, contact_to_be_added, phone_book):
	check_phone_number = Decimal(phone_number)

	if len(phone_number) < 11:
		raise ValueError("Enter complete phone number.")

	if first_name.strip() == '' or last_name.strip() == '':
		raise ValueError("This is a required field. Cannot be left blank.")

	if not first_name.isalpha() or not last_name.isalpha():
		raise ValueError("Name field may contain invalid characters.")

	contact_to_be_added[0] = first_name + " " + last_name
	contact_to_be_added[1] = phone_number
	phone_book.append(contact_to_be_added)
	return phone_book


def remove_contact(name, phone_book):
	if len(phone_book) > 0:
		phone_book = [contact for contact in phone_book if name not in contact]
	else:
		raise ValueError("Phonebook is empty!")
	return phone_book


def find_contact_by_phone_number(phone_number, phone_book):
	check_phone_number = Decimal(phone_number)
	found_contact = []

	for each_contact in phone_book:
		if phone_number in each_contact:
			found_contact.append(each_contact)

	if len(found_contact) == 0:
		raise ValueError("No contacts found.")

	return found_contact


def find_contact_by_first_name(first_name, phone_book):
	found_contacts = []

	for each_contact in phone_book:
		if first_name.lower() in each_contact[0].lower():
			found_contacts.append(each_contact)

	if len(found_contacts) == 0:
		raise ValueError("No contacts found.")

	return found_contacts


def find_contact_by_last_name(last_name, phone_book):
	found_contacts = []
	if_found = ""

	for each_contact in phone_book:
		if last_name.lower() in each_contact[0].lower():
			found_contacts.append(each_contact)

	if len(found_contacts) == 0:
		raise ValueError("No contacts found.")

   
	return found_contacts


def edit_contact(full_name, phone_book):
	for each_contact in phone_book:
		if each_contact[0].lower() == full_name.lower():
			print("Which field do you want to edit.\nEnter 1 for name or 2 for phone number")
			select = int(input())
			if select == 1:
				each_contact[0] = input("Enter new name: ")
			elif select == 2:
 				each_contact[1] = input("Enter new phone number: ")

	return phone_book


firstName = ""
lastName = ""
phoneNumber = ""
fullName = ""
phoneBook = []
phoneBookRunner = True
phoneBookMenu = """
1. Add contact
2. Remove contact
3. Find contact by phone number
4. Find contact by first name
5. Find contact by last name
6. Edit contact
7. Exit
"""

while phoneBookRunner:
	print(phoneBookMenu)
	select = input()

	match select:
		case "1":
			addContact = True
			while addContact:
				contactToBeAdded = ["", ""]
				firstName = "Adebola"
				lastName = "Foley"
				phoneNumber = "08103195663"
				phoneBook = addContact(firstName, lastName, phoneNumber, contactToBeAdded, phoneBook)
				backTo = True
				while backTo:
					print("Enter 0 to return")
					backToLastPage = input()
					match backToLastPage:
						case "0":
							addContact = False
							backTo = False
						case _:
							print("Invalid input.")

		case "2":
			removeCon = True
			while removeCon:
				fullName = "Adebola Foley"
				phoneBook = removeContact(fullName, phoneBook)
				backTo = True
				while backTo:
					print("Enter 0 to return")
					backToLastPage = input()
					match backToLastPage:
						case "0":
							removeCon = False
							backTo = False
						case _:
							print("Invalid input.")

		case "3":
			findContactByNumber = True
			while findContactByNumber:
				phoneNumber = "08103195663"
				print(findContactByPhoneNumber(phoneNumber, phoneBook))
				backTo = True
				while backTo:
					print("Enter 0 to return")
					backToLastPage = input()
					match backToLastPage:
						case "0":
							findContactByNumber = False
							backTo = False
						case _:
							print("Invalid input.")

		case "4":
			findContactByFirst = True
			while findContactByFirst:
				firstName = "Adebola"
				print(findContactByFirstName(firstName, phoneBook))
				backTo = True
				while backTo:
					print("Enter 0 to return")
					backToLastPage = input()
					match backToLastPage:
						case "0":
							findContactByFirst = False
							backTo = False
						case _:
 							print("Invalid input.")

		case "5":
			findContactByLast = True
			while findContactByLast:
				lastName = "Foley"
				print(findContactByLastName(lastName, phoneBook))
				backTo = True
				while backTo:
					print("Enter 0 to return")
					backToLastPage = input()
					match backToLastPage:
						case "0":
							findContactByLast = False
							backTo = False
						case _:
							print("Invalid input.")

		case "6":
			editCon = True
			while editCon:
				fullName = "Adebola Foley"
				phoneBook = editContact(fullName, phoneBook)
				backTo = True
				while backTo:
					print("Enter 0 to return")
					backToLastPage = input()
					match backToLastPage:
						case "0":
							editCon = False
							backTo = False
						case _:
 							print("Invalid input.")

		case "7":
			phoneBookRunner = False

		case _:
			print("Invalid input.")