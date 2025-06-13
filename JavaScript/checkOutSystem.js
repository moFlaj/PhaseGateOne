
const prompt = require('prompt-sync')()
const Decimal = require('decimal.js')
const dayjs = require('dayjs')


function setCustomerName(firstName, lastName, middleName, customerInfo){
	
	const regex = /^[a-zA-Z]+(-[a-zA-Z]+)*$/

	if(!firstName || !lastName || !middleName){
		throw new error("This is a required field. Cannot be left blank.")
	}

	if (!regex.test(firstName) || !regex.test(lastName) || !regex.test(middleName)){
		throw new error("Name field may contain invalid characters or more than one input was enetered in field.")
	}

	customerInfo[0] = firstName.substring(0,1).toUpperCase() + firstName.substring(1) + " " + lastName.substring(0,1).toUpperCase() + lastName.substring(1) + " " + middleName.substring(0,1).toUpperCase() + middleName.substring(1)

}

function getCustomerName(customerInfo){

	return customerInfo[0]

}

function returnProductIfDetected(productInput, allProducts){

	let itemsMatched = []
	let selectedItem = ""
	let counter = 0

	for(let index = 0; index < allProducts.length; index++){
		if(productInput.toLowerCase() === allProducts[index].toLowerCase() || allProducts[index].toLowerCase().includes(productInput.toLowerCase())){
			counter++
			itemsMatched.push(counter + '. ' + allProducts[index])
		}
	}
	
	for(let eachItemsMatched of itemsMatched)console.log(eachItemsMatched)
	while(true){
		let select = Number(prompt("Select option: "))
		if(select >= 1 && select <= itemsMatched.length){
			selectedItem = itemsMatched[select-1]
			selectedItem = selectedItem.substring(selectedItem.indexOf(" ") + 1)
			break
		}
		else{
			console.log("Enter valid option number")
		}
	}
	return selectedItem
}


function getProductPrice(product, allProducts, allPrices, purchasedItemInfo){

	for(let index = 0; index < allProducts.length; index++){
		if(product.toLowerCase() === allProducts[index].toLowerCase()){
			purchasedItemInfo[0] = product
			purchasedItemInfo[2] = allPrices[index]		
		}
	}		
	return  "NGN " + purchasedItemInfo[2]
}


function addPurchasedProductToList(purchasedItemInfo, pieces, customerPurchase) {

	let quantity = parseInt(pieces, 10)
	if (isNaN(quantity)) {
		throw new Error("Quantity entered is not a valid integer.")
	}
	if (quantity < 1) {
		throw new Error("Quantity entered is not valid.")
	}

	purchasedItemInfo[1] = pieces;
	const totalPrice = new Decimal(purchasedItemInfo[2]).times(new Decimal(purchasedItemInfo[1]))
	purchasedItemInfo[3] = totalPrice.toString()
	customerPurchase.push(purchasedItemInfo)

	return "Product added successfully. Add more items?\nEnter Yes/No"
}


function computeTotalCostOfItemsPurchased(customerPurchase) {
	let sum = new Decimal(0);

	for(let i = 1; i < customerPurchase.length; i++) {
	sum = sum.plus(new Decimal(customerPurchase[i][3]))
	}
	return sum;
}

function valueAddedTax(sum) {
	const addedTax = new Decimal("7.5").dividedBy(new Decimal("100")).times(sum)
	return addedTax.toDecimalPlaces(2, Decimal.ROUND_HALF_UP);
}


function inVoiceDisplay(storeInfo, customerPurchase, sum) {
	const receiptDesign = [
        "===============================================================",
        "--------------------------------------------------------------"]
	let change = 0;

	console.log();

	for(let index = 0; index < storeInfo.length; index++) {
		console.log(storeInfo[index]);
	}

	for(const displayInvoice of customerPurchase) {
		console.log(receiptDesign[change]);

		for(let itemInfo of displayInvoice) {
			process.stdout.write("\t" + itemInfo)
			if(displayInvoice[0] === itemInfo) {
				process.stdout.write("\t")
			}
		}

		console.log()
		change = 1;
	}

	console.log(receiptDesign[1]);
	console.log("\t\tSum Total: " + sum)
	console.log("\t\tVAT @ 7.5%: " + valueAddedTax(sum))
	console.log(receiptDesign[0])
	console.log("\t\tBill Total: " + (sum.add(valueAddedTax(sum))))
	console.log(receiptDesign[0])
	console.log("THIS IS NOT A RECEIPT, KINDLY PAY " + (sum.add(valueAddedTax(sum))))
	console.log(receiptDesign[0])
}


function receiptDisplay(sum, customerPay, storeInfo, customerPurchase) {
	try {
		const convertCustomerPay = new Decimal(customerPay)
		const vat = valueAddedTax(sum);
		const billTotal = sum.add(vat);
		const lessThanRequired = convertCustomerPay.cmp(billTotal)
		const negativeAmountInput = convertCustomerPay.cmp(new Decimal(0))

		if(lessThanRequired < 0 || negativeAmountInput < 0){
			throw new Error("Invalid input. Enter right amount.")
		}

		const receiptDesign = [
			"===============================================================",
			"--------------------------------------------------------------"
        	]
        	let change = 0

        	console.log()

		for(let index = 0; index < storeInfo.length; index++) {
			console.log(storeInfo[index])
		}

		for(let displayInvoice of customerPurchase) {
			console.log(receiptDesign[change])
			for(let itemInfo of displayInvoice) {
				process.stdout.write("\t" + itemInfo)
				if(displayInvoice[0] === itemInfo) {
					process.stdout.write("\t")
				}
			}
			console.log()
			change = 1
		}


        	console.log(receiptDesign[1])
       		console.log("\t\tSum Total: " + sum)
        	console.log("\t\tVAT @ 7.5%: " + vat)
        	console.log(receiptDesign[0])
        	console.log("\t\tBill Total: " + billTotal)
		console.log("\t\tAmount paid: " + convertCustomerPay)

		const balance = convertCustomerPay.sub(billTotal)
		console.log("\t\tBalance: " + balance.toFixed(2))
		console.log(receiptDesign[0])
		console.log("\tTHANK YOU FOR YOUR PATRONAGE.")
		console.log(receiptDesign[0])

	}catch (invalidInput) {
		console.log(invalidInput.message)
	}
}



const ofFormat = 'DD-MMM-YYYY HH:mm:ss';

let customerPurchase = [["ITEM", "QTY", "PRICE", "TOTAL(NGN)"]];
let customerInfo = [""]
let storeInfo = [
  "SEMICOLON STORES",
  "MAIN BRANCH",
  "LOCATION: 312 HERBERT MACAULAY WAY, SABO YABA, LAGOS.",
  "TEL: 0823452776",
  null,
  null,
  null
]

let allProducts = [
  "ChiActive 10g", "Cornflakes 15g", "Cornflakes 35g",
  "Frozen chicken 15g", "Frozen Chicken 20g", "Milk 25g",
  "Milk 50g", "Milo 20g", "Ribena 8g", "Rice 50g", "Semolina 30g"
]

let allPrices = [
  "1700.00", "1200.00", "3000.00", "8000.00",
  "15700.00", "2200.00", "4200.00", "3500.00",
  "1100.00", "5450.00", "5800.00"
]
//prices are arranged according to sorted order of products.

let checkOut = true
while(checkOut){
	console.log("What is the customer's full name?")
	let firstName = prompt()
	let lastName = prompt()
	let middleName = prompt()
	setCustomerName(firstName, lastName, middleName, customerInfo)
	storeInfo[6] = "Customer name: " + getCustomerName(customerInfo)

	let checkItemBought = true

	while(checkItemBought){
		let purchasedItemInfo = []
		console.log("What did the customer buy?")
		let productInput = prompt()
		productInput = returnProductIfDetected(productInput, allProducts)

		console.log("How much per unit?")
		console.log(getProductPrice(productInput, allProducts, allPrices, purchasedItemInfo));

		console.log("How many pieces?")
		let quantity = prompt()

		let continueAddingItems = addPurchasedProductToList(purchasedItemInfo, quantity, customerPurchase)

		let continueAdding = true
		while(continueAdding){
			console.log(continueAddingItems)
			let yesOrNo = prompt()
			if("Yes".toLowerCase() === yesOrNo.toLowerCase()){
				continueAdding = false
      			} 
			else if("No".toLowerCase() === yesOrNo.toLowerCase()){
				continueAdding = false
				checkItemBought = false
				checkOut = false;
			} else {
				console.log("Enter valid response!");
			}
		}
	}

	let sumTotalCostOfItems = computeTotalCostOfItemsPurchased(customerPurchase);
	console.log("Full name of cashier?");
	firstName = prompt();
	lastName = prompt();
	middleName = prompt();
	storeInfo[5] = "Cashier: " + firstName.substring(0,1).toUpperCase() + firstName.substring(1) + " " + lastName.substring(0,1).toUpperCase() + lastName.substring(1) + " " + middleName.substring(0,1).toUpperCase() + middleName.substring(1)

	let dateAndCurrentTime = dayjs();
	storeInfo[4] = "Date: " + dateAndCurrentTime.format(ofFormat);

	inVoiceDisplay(storeInfo, customerPurchase, sumTotalCostOfItems);

	console.log("How much did customer pay?");
	let customerPay = prompt();
	receiptDisplay(sumTotalCostOfItems, customerPay, storeInfo, customerPurchase);
}
