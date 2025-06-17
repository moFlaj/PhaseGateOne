const prompt = require('prompt-sync')();

function addContact(firstName, lastName, phoneNumber, contactToBeAdded, phoneBook) {

	BigInt(phoneNumber); 

	if (phoneNumber.length < 11) throw new Error("Enter complete phone number.");

	if (firstName.trim() === "" || lastName.trim() === "") throw new Error("This is a required field. Cannot be left blank.");

	if (!/^[a-zA-Z]+$/.test(firstName) || !/^[a-zA-Z]+$/.test(lastName))
        throw new Error("Name field may contain invalid characters.");

	contactToBeAdded[0] = firstName + " " + lastName;
	contactToBeAdded[1] = phoneNumber;
	phoneBook.push(contactToBeAdded);
	return phoneBook;
}

function removeContact(name, phoneBook) {
	if (phoneBook.length > 0) {
		phoneBook = phoneBook.filter(contact => !contact.includes(name));
	} else {
		throw new Error("Phonebook is empty!");
	}
	return phoneBook;
	}

function findContactByPhoneNumber(phoneNumber, phoneBook) {

	BigInt(phoneNumber); // checkPhoneNumber
	let foundContact = [];

	for (let contact of phoneBook) {
		if (contact.includes(phoneNumber)) {
			foundContact.push(contact);
		}
	}

	if (foundContact.length === 0) throw new Error("No contacts found.");

  
	return foundContact;
}

function findContactByFirstName(firstName, phoneBook) {
	let foundContacts = [];

	for (let contact of phoneBook) {
		if (contact[0].toLowerCase().includes(firstName.toLowerCase())) {
			foundContacts.push(contact);
		}
	}

	if (foundContacts.length === 0) throw new Error("No contacts found.");

	return foundContacts;
}

function findContactByLastName(lastName, phoneBook) {
	let foundContacts = [];

	for (let contact of phoneBook) {
		if (contact[0].toLowerCase().includes(lastName.toLowerCase())) {
			foundContacts.push(contact);
		}
	}

	if (foundContacts.length === 0) throw new Error("No contacts found.");

	return foundContacts;
}

function editContact(fullName, phoneBook) {
	for (let contact of phoneBook) {
		if (contact[0].toLowerCase() === fullName.toLowerCase()) {
			console.log("Which field do you want to edit.\nEnter 1 for name or 2 for phone number:");
			const select = prompt();

			if (select === "1") {
  				contact[0] = prompt("Enter new name: ");
			} else if (select === "2") {
				contact[1] = prompt("Enter new phone number: ");
			}
		}
	}
	return phoneBook;
}



let firstName = "";
let lastName = "";
let phoneNumber = "";
let fullName = "";
let phoneBook = [];
let phoneBookRunner = true;
let phoneBookMenu = `
1. Add contact
2. Remove contact
3. Find contact by phone number
4. Find contact by first name
5. Find contact by last name
6. Edit contact
7. Exit
`;

while(phoneBookRunner){
	console.log(phoneBookMenu);
	let select = prompt();

	switch(select){
		case "1":
		let add = true;
		while(add){
			try{
				let contactToBeAdded = ["", ""];
 				firstName = prompt('Enter first name: ');
  				lastName = prompt('Enter last name: ');
				phoneNumber = prompt('Enter eleven digit phone number: ')
				phoneNoLengthAfter = phoneBook.length + 1
				phoneBook = addContact(firstName, lastName, phoneNumber, contactToBeAdded, phoneBook);

			}catch(invalidInput){
				console.log(invalidInput.message)
			}
			if(phoneBook.length == phoneNoLengthAfter){
				let backTo = true;
				while(backTo){
					console.log("Enter 0 to return or 1 to add another contact.");
					let backToLastPage = prompt();
					switch(backToLastPage){
						case "0":
							add = false
  							backTo = false
							break;
						case "1":
							backTo = false;
							break;
 						default:
							console.log("Invalid input.");
					}
				}
			}else{continue;}
		}
			break;

		case "2":
			try{
				fullName = prompt();
				phoneBook = removeContact(fullName, phoneBook);
			}
			catch(err){
				console.log(err.message);
			}

                	let backTo = true;
                	while(backTo){
                    		console.log("Enter 0 to return");
                    		let backToLastPage = prompt();
				switch(backToLastPage){
					case "0":
						backTo = false;
						break;
					default:
						console.log("Invalid input.");
				}
			}

			break;

		case "3":
			let findContactByNumber = true;
			while (findContactByNumber){
				try{
					phoneNumber = "08103195663";
					console.log(findContactByPhoneNumber(phoneNumber, phoneBook));
				}
				catch(err){
					console.log(err.message);
				}
				let backTo = true;
				while(backTo){
					console.log("Enter 0 to return or 1 to search again");
					let backToLastPage = prompt();
                    			switch(backToLastPage){
						case "0":
							findContactByNumber = false;
							backTo = false;
							break;
						case "1":
							backTo = false;
							break;
						default:
							console.log("Invalid input.");
					}
                		}
			}
			break;

		case "4":
			let findContactByFirst = true;
			while(findContactByFirst){
				try{
					firstName = prompt();
					console.log(findContactByFirstName(firstName, phoneBook));
				}catch(err){
					console.log(err.message);
				}
				let backTo = true;
				while(backTo){
					console.log("Enter 0 to return or 1 to search again");
					let backToLastPage = prompt();
					switch(backToLastPage){
						case "0":
							findContactByFirst = false;
							backTo = false;
							break;

						case "1":
							backTo = false;
							break;
						
						default:
							console.log("Invalid input.");
					}
				}
			}
			break;

		case "5":
			let findContactByLast = true;
			while(findContactByLast){
				try{
					lastName = prompt();
					console.log(findContactByLastName(lastName, phoneBook));
				}catch(err){
					console.log(err.message);
				}
				let backTo = true;
				while(backTo){
					console.log("Enter 0 to return or 1 to search again");
					let backToLastPage = prompt();
					switch(backToLastPage){
						case "0":
							findContactByLast = false;
							backTo = false;
							break;

						case "1":
							backTo = false;
							break;
						
						default:
							console.log("Invalid input.");
					}
				}
			}

			break;

		case "6":
			let editCon = true;
			while(editCon){
				fullName = "Adebola Foley";
				phoneBook = editContact(fullName, phoneBook);
				let backTo = true;
				while (backTo){
					console.log("Enter 0 to return");
					let backToLastPage = prompt();
					switch(backToLastPage){
						case "0":
							editCon = false;
							backTo = false;
							break;
						default:
							console.log("Invalid input.");
					}
				}
			}
			break;

		case "7":
			phoneBookRunner = false;
			break;

		default:
			console.log("Invalid input.");
	}
}
