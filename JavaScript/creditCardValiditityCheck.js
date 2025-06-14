function checkCreditCardType(cardNumber){
	let cardType = "";

	try {
		BigInt(cardNumber); // Throws if cardNumber contains invalid characters
	}catch(invalidCh){
		throw new Error("Invalid characters in card number");
	}

	let splitDigits = cardNumber.split('');
	if(splitDigits.length >= 13 && splitDigits.length <= 16){
		if(splitDigits[0] === '4'){
			cardType = "Visa";
		}	
		else if (splitDigits[0] === '5') {
			cardType = "Mastercard";
		}	
		else if (splitDigits[0] === '3' && splitDigits[1] === '7') {
			cardType = "American Express";
		} 
		else if (splitDigits[0] === '6') {
			cardType = "Discover";
		} 
		else{
			throw new Error("Issuer is unknown");
		}
		}else{
			throw new Error("Invalid length");
		}

	return "Credit Card Type: " + cardType;
}

function sumDigitsInCardNumber(cardNumber){
	let splitDigits = cardNumber.split('');
	let sumEverySecond = 0;

	for(let counter = 1; counter <= 2; counter++){
		for(let index = splitDigits.length - counter; index >= 0; index -= 2){
			let toDigit = parseInt(splitDigits[index]) * 2;
			let digitToString = toDigit.toString();
			if(digitToString.length === 2){
				toDigit = parseInt(digitToString[0]) + parseInt(digitToString[1]);
			}
			sumEverySecond += toDigit;
		}
	}

	return sumEverySecond;
}

function checkCardValidity(summedDigits){
	if(summedDigits % 10 === 0){
		return "Credit Card Validity Status: Valid";
	} 
	else{
		return "Credit Card Validity Status: Invalid";
	}
}

let cardNumber = "379831619690403";

console.log(checkCreditCardType(cardNumber));
console.log("Credit Card Number: " + cardNumber);
console.log("Credit Card Digit Length: " + cardNumber.length);
console.log(checkCardValidity(sumDigitsInCardNumber(cardNumber)));