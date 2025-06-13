
import java.util.InputMismatchException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckoutSystem{


	public static void setNameOfCustomer(String firstName, String lastName, String middleName, String[] customerInfo){
		String concatAllNames;

		if(firstName.isBlank() || lastName.isBlank() || middleName.isBlank())throw new IllegalArgumentException("This is a required field. Cannot be left blank.");

//Still throws NullPointerException if any of the names is null, as null String variables cannot invoke isBlank(). 
		

		if(!firstName.matches("^[a-zA-Z]+(-[a-zA-Z]+)*$") || !lastName.matches("^[a-zA-Z]+(-[a-zA-Z]+)*$") || !middleName.matches("^[a-zA-Z]+(-[a-zA-Z]+)*$")){
//Only letters and hyphens are allowed to be in names. Hyphens are not allowed to start a name or appear consecutively.
			throw new InputMismatchException("Name field may contain invalid characters or more than one input was enetered in field.");
		}

		customerInfo[0] = firstName.substring(0,1).toUpperCase() + firstName.substring(1) + " " + lastName.substring(0,1).toUpperCase() + lastName.substring(1) + " " + middleName.substring(0,1).toUpperCase() + middleName.substring(1);
		
	}

	public static String getCustomerName(String[] customerInfo){

		return "Customer name: " + customerInfo[0];
	}

	public static String returnProductIfDetected(String productInput, String[] allProducts){
//This function searches items on the system. Search input may be complete name or incomplete.
		Scanner input = new Scanner(System.in);

		List<String> allProductsToList = Arrays.asList(allProducts);
		List<String> productNameInfoIncomplete = new ArrayList<>();
		String productIfFound = "";
		int counter = 0;
		int productIndex = 0;
	
		for(int index = 0; index < allProductsToList.size(); index++){
			if(productInput.equalsIgnoreCase(allProductsToList.get(index)) || (allProductsToList.get(index).toLowerCase()).contains(productInput.toLowerCase())){
				productIndex = index;
				counter++;
				productNameInfoIncomplete.add(counter + ". " + allProductsToList.get(index));
			}
		}
		
		for(String eachProductInSystem : productNameInfoIncomplete){
			System.out.println(eachProductInSystem);
		}
		System.out.print("Select your option: ");
		while(true){
			int select = input.nextInt();
			if(select >= 1 && select <= productNameInfoIncomplete.size()){
				productIfFound = productNameInfoIncomplete.get(select - 1);
				productIfFound = productIfFound.substring(productIfFound.indexOf(" ") + 1);
				break;	
			}
			else{
				System.out.println("Enter valid option number");
			}
				
		}return productIfFound;

	}

	public static String getProductPrice(String product, String[] allProducts, String[] allPrices, String[] purchasedItemInfo){

		List<String> allProductsToList = Arrays.asList(allProducts);

		int productIndex = 0;
	
		for(int index = 0; index < allProductsToList.size(); index++){
			if(product.equalsIgnoreCase(allProductsToList.get(index))){
				purchasedItemInfo[0] = product;
				purchasedItemInfo[2] = allPrices[index];
				
			}
		}
			
		return  "NGN " + purchasedItemInfo[2];	

	}


	public static String addPurchasedProductToList(String[] purchasedItemInfo, String pieces, List<String[]> customerPurchase){
		try{
			int quantity = Integer.parseInt(pieces);
			if(quantity < 1)throw new IllegalArgumentException("Quantity entered is not valid.");
			purchasedItemInfo[1] = pieces;
			BigDecimal totalPrice = new BigDecimal(purchasedItemInfo[2]).multiply(new BigDecimal(purchasedItemInfo[1]));
			purchasedItemInfo[3] = totalPrice.toString();
			customerPurchase.add(purchasedItemInfo);
			return "Product added succesfully. Add more items?\nEnter Yes/No";
		}

		catch(NumberFormatException notAnInteger){
			throw new IllegalArgumentException("Quantity entered is not a valid integer.");
		}	

	}

	public static BigDecimal computeTotalCostOfItemsPurchased(List<String[]> customerPurchase){

		BigDecimal sum = new BigDecimal("0");
		

		for(String[] item : customerPurchase){
			if(Arrays.equals(customerPurchase.get(0), item))continue;
			sum = sum.add(new BigDecimal(item[3]));
			
		}
	
		return sum;
	}

	public static BigDecimal valueAddedTax(BigDecimal sum){

		BigDecimal addedTax = (new BigDecimal("7.5").divide(new BigDecimal("100"))).multiply(sum);

		return addedTax.setScale(2, RoundingMode.HALF_UP);
	}

	public static void inVoiceDisplay(String[] storeInfo, List<String[]> customerPurchase, BigDecimal sum){

		String[] receiptDesign = {"===============================================================", "--------------------------------------------------------------"};
		int change = 0;
		System.out.println();
		for(int index = 0; index < storeInfo.length; index++){System.out.println(storeInfo[index]);}

		for(String[] displayInvoice : customerPurchase){
			System.out.println(receiptDesign[change]);
			for(String itemInfo : displayInvoice){
				System.out.print("\t" + itemInfo);
				if(displayInvoice[0].equals(itemInfo))System.out.print("\t");
				
			}
			System.out.println();
			change = 1;	
		}
		System.out.println(receiptDesign[1]);
		System.out.println("\t\tSum Total: " + sum);
		System.out.println("\t\tVAT @ 7.5%: " + valueAddedTax(sum));
		System.out.println(receiptDesign[0]);
		System.out.println("\t\tBill Total: " + sum.add(valueAddedTax(sum)));
		System.out.println(receiptDesign[0]);
		System.out.println("THIS IS NOT A RECEIPT, KINDLY PAY " + sum.add(valueAddedTax(sum)));
		System.out.println(receiptDesign[0]);

	}

	public static void receiptDisplay(BigDecimal sum, String customerPay, String[] storeInfo, List<String[]> customerPurchase){
		try{
			BigDecimal convertCustomerPay = new BigDecimal(customerPay);
			
			String[] receiptDesign = {"===============================================================", "--------------------------------------------------------------"};
			int change = 0;
			System.out.println();
			for(int index = 0; index < storeInfo.length; index++){System.out.println(storeInfo[index]);}

			for(String[] displayInvoice : customerPurchase){
				System.out.println(receiptDesign[change]);
				for(String itemInfo : displayInvoice){
					System.out.print("\t" + itemInfo);
					if(displayInvoice[0].equals(itemInfo))System.out.print("\t");
				
				}
				System.out.println();
				change = 1;	
			}
		
			System.out.println(receiptDesign[1]);
			System.out.println("\t\tSum Total: " + sum);
			System.out.println("\t\tVAT @ 7.5%: " + valueAddedTax(sum));
			System.out.println(receiptDesign[0]);
			System.out.println("\t\tBill Total: " + sum.add(valueAddedTax(sum)));
			System.out.println("\t\tAmount paid: " + convertCustomerPay);
			int lessThanRequired = convertCustomerPay.compareTo(sum.add(valueAddedTax(sum)));
			int negativeAmountInput = convertCustomerPay.compareTo(new BigDecimal("0"));
			if(lessThanRequired < 0 || negativeAmountInput < 0)throw new IllegalArgumentException("Invalid input. Enter right amount.");
			System.out.println("\t\tBalance: " + convertCustomerPay.subtract(sum.add(valueAddedTax(sum))));
			System.out.println(receiptDesign[0]);
			System.out.println("\tTHANK YOU FOR YOUR PATRONAGE.");
			System.out.println(receiptDesign[0]);

		}catch(IllegalArgumentException invalidInput){
			System.out.println(invalidInput);
		}


	}
				

/*	public static void main(String[] args){

		Scanner input = new Scanner(System.in);
		DateTimeFormatter ofFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
		List<String[]> customerPurchase = new ArrayList<>();
		customerPurchase.add(new String[]{"ITEM", "QTY", "PRICE", "TOTAL(NGN)"});
		String[] customerInfo = new String[1];
		String[] storeInfo = {"SEMICOLON STORES", "MAIN BRANCH", "LOCATION: 312 HERBERT MACAULAY WAY, SABO YABA, LAGOS.", "TEL: 0823452776", null, null, null}; 
		String[] allProducts = { "ChiActive 10g", "Cornflakes 15g", "Cornflakes 35g", "Frozen chicken 15g", "Frozen Chicken 20g", "Milk 25g", "Milk 50g", "Milo 20g", "Ribena 8g", "Rice 50g", "Semolina 30g" };
		String[] allPrices = new String[] {"1700.00", "1200.00", "3000.00", "8000.00", "15700.00", "2200.00", "4200.00", "3500.00", "1100.00", "5450.00", "5800.00"};

// Prices are arranged according to sorted order of products.

		boolean checkOut = true;
		while(checkOut){
			System.out.println("What is the customer's full name?");
			String firstName = input.next();
			String lastName = input.next();
			String middleName = input.next();
			input.nextLine();
			setNameOfCustomer(firstName, lastName, middleName, customerInfo);
			storeInfo[6] =  getCustomerName(customerInfo);
			boolean checkItemBought = true;
			while(checkItemBought){
				String[] purchasedItemInfo = new String[4];
				System.out.println("What did the customer buy?");
				String productInput = input.nextLine();
				productInput = returnProductIfDetected(productInput, allProducts);
				System.out.println("How much per unit?");
				System.out.println(getProductPrice(productInput,allProducts,allPrices,purchasedItemInfo));
				System.out.println("How many pieces?");
				String quantity = input.next();
				String continueAddingItems = addPurchasedProductToList(purchasedItemInfo, quantity, customerPurchase);
				boolean continueAdding = true;
				while(continueAdding){
					System.out.println(continueAddingItems);
					String yesOrNo = input.next();
					input.nextLine();
					if("Yes".equalsIgnoreCase(yesOrNo))continueAdding = false;
					else if("No".equalsIgnoreCase(yesOrNo)){

						continueAdding = false;
						checkItemBought = false; 
						checkOut = false;
					}
					else{
						System.out.println("Enter valid response!");
					}
						
				}
				
			}
				BigDecimal sumTotalCostOfItems = computeTotalCostOfItemsPurchased(customerPurchase);
				System.out.println("Full name of cashier?");
				firstName = input.next();
				lastName = input.next();
				middleName = input.next();
				storeInfo[5] = "Cashier: " + firstName.substring(0,1).toUpperCase() + firstName.substring(1) + " " + lastName.substring(0,1).toUpperCase() + lastName.substring(1) + " " + middleName.substring(0,1).toUpperCase() + middleName.substring(1);
				LocalDateTime dateAndCurrentTime = LocalDateTime.now();
				storeInfo[4] = "Date: " + dateAndCurrentTime.format(ofFormat);
				inVoiceDisplay(storeInfo, customerPurchase, sumTotalCostOfItems);
				System.out.println("How much did customer pay?");
				String customerPay = input.next();
				receiptDisplay(sumTotalCostOfItems, customerPay, storeInfo, customerPurchase);
		}

	} */
}


				
