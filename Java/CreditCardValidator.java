import java.math.BigInteger;
public class CreditCardValidator{


	public static String checkCreditCardType(String cardNumber){

		String cardType = "";
		
		BigInteger checkInvalidCharacter = new BigInteger(cardNumber);
//throws a NumberFormatException if there are invalid characters in the card number.

		char[] splitDigits = cardNumber.toCharArray();
		if(splitDigits.length >= 13 && splitDigits.length <= 16){
			if(splitDigits[0] == '4')cardType = "Visa";
			else if(splitDigits[0] == '5')cardType = "Mastercard";
			else if(splitDigits[0] == '3' && splitDigits[1] == '7')cardType = "American Express";
			else if(splitDigits[0] == '6')cardType = "Discover";
			else{
				throw new IllegalArgumentException("Issuer is unknown");
				}
			}
		else{
			throw new IllegalArgumentException("Invalid length");
		}

		return "Credit Card Type: " + cardType;
			
	}

	public static int sumDigitsInCardNumber(String cardNumber){

		char[] splitDigits = cardNumber.toCharArray();
		int sumEverySecond = 0;
		for(int counter = 1 ; counter <= 2 ; counter++){
			for(int index = splitDigits.length - counter; index >= 0 ; index -= 2){
				int toDigit = Character.getNumericValue(splitDigits[index]);
				toDigit *= 2;
				String digitToString = Integer.toString(toDigit);
				if(digitToString.length() == 2){
					toDigit = Character.getNumericValue(digitToString.charAt(0)) + Character.getNumericValue(digitToString.charAt(1));
				}
				sumEverySecond += toDigit;
			}
		}
		return sumEverySecond;
	}

	public static String checkCardValidity(int summedDigits){

		String cardValidity = "";
		if(summedDigits%10 == 0)return "Credit Card Validity Status: Valid";
		else{return "Credit Card Validity Status: Invalid";}
	}



	public static void main(String[] args){

		String cardNumber = "7799831619690403";
		System.out.println(checkCreditCardType(cardNumber));
		System.out.println("Credit Card Number: " + cardNumber);
		System.out.println("Credit Card Digit Length: " + cardNumber.length());
		System.out.println(checkCardValidity(sumDigitsInCardNumber(cardNumber)));
		
	}

}