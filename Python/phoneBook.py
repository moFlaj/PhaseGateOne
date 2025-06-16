from decimal import Decimal

def add_contact(first_name, last_name, phone_number, contact_to_be_added, phone_book):
	check_phone_number = Decimal(phone_number)

	if len(phone_number) < 11:
		raise ValueError("Enter complete phone number.")

	if first_name.strip() == '' or last_name.strip() == '':
		raise ValueError("These are all required fields. Cannot be left blank.")

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


def find_contact_by_phone_number(number, phone_book):

	check_phone_number = Decimal(number)
	found_contact = []

	for each_contact in phone_book:
		for phone_number in each_contact:
			if number in phone_number:
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
				each_contact[0] = input("Edit name: ")
			elif select == 2:
 				each_contact[1] = input("Edit phone number: ")

	return phone_book



first_name = ""
last_name = ""
phone_number = ""
full_name = ""
phone_book = []
phone_book_runner = True
phone_book_menu = """
1. Add contact
2. Remove contact
3. Find contact by phone number
4. Find contact by first name
5. Find contact by last name
6. Edit contact
7. Exit
"""

while phone_book_runner:
	print(phone_book_menu)
	select = input()

	match select:
		case "1":
			add = True
			while add:
				try:
					contact_to_be_added = ["", ""]
					first_name = input('First name: ')
					last_name = input('Last name: ')
					phone_number = input('Phone number: ')
					phone_book_size_after = len(phone_book) + 1
					phone_book = add_contact(first_name, last_name, phone_number, contact_to_be_added, phone_book)

				except ValueError as invalid_input:
					print(invalid_input.args[0]);
				if len(phone_book) ==  phone_book_size_after:	
					back_to = True
					while back_to:
						print("Enter 0 to return")
						back_to_last_page = input()
						match back_to_last_page:
							case "0":
								add = False
								back_to = False
							case _:
								print("Invalid input.")
				else:
					continue

		case "2":
			try:
				full_name = input('Full name of contact: ')
				phone_book = remove_contact(full_name, phone_book)

			except ValueError as emptyPhoneBook:
				print(emptyPhoneBook.args[0])
			back_to = True
			while back_to:
				print("Enter 0 to return")
				back_to_last_page = input()
				match back_to_last_page:
					case "0":
						back_to = False
					case _:
						print("Invalid input.")

		

		case "3":
			find_contact_by_number = True
			while find_contact_by_number:
				try:
					phone_number = input('Enter phone number: ')
					print(find_contact_by_phone_number(phone_number, phone_book))

				except ValueError as no_contacts_found:
					print(no_contacts_found.args[0])

				back_to = True
				while back_to:
					print("Enter 0 to return or 1 to search again")
					back_to_last_page = input()
					match back_to_last_page:
						case "0":
							find_contact_by_number = False
							back_to = False
						case "1":
							back_to = False
						case _:
 							print("Invalid input.")

		case "4":
			
			find_contact_by_first = True
			while find_contact_by_first:
				try:
					first_name = input('Enter first name: ')
					print(find_contact_by_first_name(first_name, phone_book))
				except ValueError as no_contacts_found:
					print(no_contacts_found.args[0])
				back_to = True
				while back_to:
					print("Enter 0 to return or 1 to search again")
					back_to_last_page = input()
					match back_to_last_page:
						case "0":
							find_contact_by_first = False
							back_to = False
						case "1":
							back_to = False
						case _:
 							print("Invalid input.")

		case "5":

			find_contact_by_last = True

			while find_contact_by_last:

				try:
					last_name = input('Enter last name: ')

					print(find_contact_by_last_name(last_name, phone_book))

				except ValueError as no_contacts_found:

					print(no_contacts_found.args[0])

				back_to = True
				while back_to:

					print("Enter 0 to return or 1 to search again")

					back_to_last_page = input()

					match back_to_last_page:

						case "0":
							find_contact_by_last = False
							back_to = False
						case "1":
							back_to = False
						case _:
 							print("Invalid input.")

		case "6":
			edit_con = True
			while edit_con:
				full_name = input('Enter full name of contact: ')
				phone_book = edit_contact(full_name, phone_book)
				back_to = True
				while back_to:
					print("Enter 0 to return")
					back_to_last_page = input()
					match back_to_last_page:
						case "0":
							edit_con = False
							back_to = False
						case _:
 							print("Invalid input.")

		case "7":
			phone_book_runner = False

		case _:
			print("Invalid input.")
