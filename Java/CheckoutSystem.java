
import java.util.InputMismatchException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.util.Scanner;

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

		return customerInfo[0];
	}

	public static String getProductPrice(String product, String[] allProducts, String[] allPrices, String[] purchasedItemInfo){

		Scanner input = new Scanner(System.in);

		Arrays.sort(allProducts);
// Prices are arranged according to sorted order of products.

		List<String> allProductsToList = Arrays.asList(allProducts);
		List<String> productNameInfoIncomplete = new ArrayList<>();

		int counter = 0;
		int productIndex = 0;
	
		for(int index = 0; index < allProductsToList.size(); index++){
			if(product.equalsIgnoreCase(allProductsToList.get(index)) || (allProductsToList.get(index).toLowerCase()).contains(product.toLowerCase())){
				productIndex = index;
				counter++;
				if(counter >= 1){
					productNameInfoIncomplete.add(counter + ". " + allProductsToList.get(index));
				}
			}
		}
		if(counter == 1){
			purchasedItemInfo[0] = product;
			purchasedItemInfo[2] = allPrices[productIndex];
			
		}
		else{
			for(String eachProduct : productNameInfoIncomplete){
				System.out.println(eachProduct);
			}
			System.out.print("Select your option: ");
			while(true){
				int select = input.nextInt();
				if(select >= 1 && select <= productNameInfoIncomplete.size()){
					product = productNameInfoIncomplete.get(select - 1);
					product = product.substring(product.indexOf(" ") + 1);
					getProductPrice(product, allProducts, allPrices, purchasedItemInfo);
					break;	
				}
				else{
					System.out.println("Enter valid option number");
				}
				
			}
		}return purchasedItemInfo[2];
		

	}

	public static String addPurchasedProductToList(String[] purchasedItemInfo, String pieces, List<String[]> customerPurchase){
		try{
			Integer.parseInt(pieces);
			purchasedItemInfo[1] = pieces;
			BigDecimal totalPrice = new BigDecimal(purchasedItemInfo[2]).multiply(new BigDecimal(purchasedItemInfo[1]));
			// System.out.println(totalPrice);
			purchasedItemInfo[3] = totalPrice.toString();
			customerPurchase.add(purchasedItemInfo);
			return "Product added succesfully. Add more items?";
		}
		catch(NumberFormatException error){
			throw new IllegalArgumentException("Quantity entered is not valid.");
		}	

	}

	public static BigDecimal computeTotalCostOfItemsPurchased(List<String[]> customerPurchase){

		BigDecimal sum = new BigDecimal("0");
		

		for(String[] item : customerPurchase){
			sum = sum.add(new BigDecimal(item[3]));
			
		}
	
		return sum;
	}

	public static BigDecimal valueAddedTax(BigDecimal sum){

		BigDecimal addedTax = (new BigDecimal("7.5").divide(new BigDecimal("100"))).multiply(sum);

		return addedTax;

	}


	public static void main(String[] args){

		Scanner input = new Scanner(System.in);
		List<String[]> customerPurchase = new ArrayList<>();
		String[] customerInfo = new String[1];
		String[] storeInfo = {"SEMICOLON STORES", "MAIN BRANCH", "LOCATION: 312 HERBERT MACAULAY WAY, SABO YABA, LAGOS.", "TEL: 0823452776", null, "Cashier: Sojinu Sodiq", null}; 
		String[] allProducts = {"Milk	50g", "Milk	25g", "Cornflakes	35g", "Cornflakes	15g", "Milo	20g", "Rice	50g", "Semolina	30g", "Frozen Chicken	20g", "Frozen chicken 	15g", "ChiActive	10g", "Ribena	8g"};

//One tab spacing between product and size or between brand and name of product.

		String[] allPrices = new String[] {"1700.00", "1200.00", "3000.00", "8000.00", "15700.00", "2200.00", "4200.00", "3500.00", "1100.00", "5450.00", "5800.00"};

		boolean checkOut = true;
		while(checkOut){
			System.out.println("What is the customer's name?");
			String firstName = input.next();
			String lastName = input.next();
			String middleName = input.next();
			input.nextLine();
			setNameOfCustomer(firstName, lastName, middleName, customerInfo);
			storeInfo[4] = getCustomerName(customerInfo);
			boolean checkItemBought = true;
			while(checkItemBought){
				String[] purchasedItemInfo = new String[4];
				System.out.println("What did the customer buy?");
				String product = input.nextLine();
				System.out.println("How much per unit?");
				System.out.println(getProductPrice(product,allProducts,allPrices,purchasedItemInfo));
				System.out.println("How many pieces?");
				String quantity = input.next();
				System.out.println(addPurchasedProductToList(purchasedItemInfo, quantity, customerPurchase));
				String yesOrNo = input.next();
				input.nextLine();
				switch(yesOrNo){
					case "yes", "Yes":
						break;
					case "no":
						checkItemBought = false; 
						break;
					default: 
						System.out.println("Enter valid response!");
				}


			}
		}
	}

}


				
