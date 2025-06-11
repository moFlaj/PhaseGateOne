
import java.util.InputMismatchException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

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

		Arrays.sort(allProducts);
// Prices are arranged according to sorted order of products.

		List<String> allProductsToList = Arrays.asList(allProducts);

		int counter = 0;
		int productIndex = 0;

		String productNameInfoIncomplete = "";
	
		for(int index = 0; index < allProductsToList.size(); index++){
			if(product.equalsIgnoreCase(allProductsToList.get(index)) || (allProductsToList.get(index).toLowerCase()).contains(product.toLowerCase())){
				productIndex = index;
				counter++;
				if(counter >= 1){
					productNameInfoIncomplete = productNameInfoIncomplete + counter + ". " + allProductsToList.get(index) + "	" + allPrices[productIndex] + "\n";

				}
			}
		}
		if(counter == 1){
			purchasedItemInfo[0] = product;
			purchasedItemInfo[2] = allPrices[productIndex];
			
			return "NGN " + purchasedItemInfo[2];
		}
		else{
			return productNameInfoIncomplete;
		}
		

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

}


				
