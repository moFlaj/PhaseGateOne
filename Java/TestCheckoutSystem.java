import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class TestCheckoutSystem{

	String firstName;
	String lastName;
	String middleName;
	String[] customerInfo;
	String actual;
	String expected;
	String product;
	String searchInput;
	String[] allProducts;
	String[] allPrices;
	String[] purchasedItemInfo;
	List<String[]> customerPurchase;

	@BeforeEach
	void setUp(){
		firstName = "adebola";
		lastName = "folorunsho";
		middleName = "ayomide";
		customerInfo = new String[1];
		allProducts = new String[] { "ChiActive 10g", "Cornflakes 15g", "Cornflakes 35g", "Frozen chicken 15g", "Frozen Chicken 20g", "Milk 25g", "Milk 50g", "Milo 20g", "Ribena 8g", "Rice 50g", "Semolina 30g" };

		allPrices = new String[] {"1700.00", "1200.00", "3000.00", "8000.00", "15700.00", "2200.00", "4200.00", "3500.00", "1100.00", "5450.00", "5800.00"};

		purchasedItemInfo = new String[4];
		customerPurchase = new ArrayList<>();
		customerPurchase.add(new String[]{"ITEM", "QTY", "PRICE", "TOTAL(NGN)"});
	}

	@Test
	void testSetCustomerNameThrowsExceptionIfNameIsNull(){
		firstName = null;
		assertThrows(NullPointerException.class, () -> {
			CheckoutSystem.setNameOfCustomer(firstName, lastName, middleName, customerInfo);
		});
	}

	@Test
	void testSetCustomerNameThrowsExceptionIfFieldIsLeftBlankOrEmpty(){
		lastName = " ";
		assertThrows(IllegalArgumentException.class, () -> {
			CheckoutSystem.setNameOfCustomer(firstName, lastName, middleName, customerInfo);
		});
	}

	@Test
	void testSetCustomerNameThrowsExceptionIfInvalidCharactersOrDigitsAreEntered(){
		middleName = "Ad$bola";
		assertThrows(InputMismatchException.class, () -> {
			CheckoutSystem.setNameOfCustomer(firstName, lastName, middleName, customerInfo);
		});
	}

	@Test
	void testSetCustomerNameThrowsErrorIfHyphensAreUsedToStartAName(){
		firstName = "-debola";
		assertThrows(InputMismatchException.class, () -> {
			CheckoutSystem.setNameOfCustomer(firstName, lastName, middleName, customerInfo);
		});
	}
	@Test
	void testSetCustomerNameDoesNotThrowErrorIfNameIsACompoundName(){
		firstName = "Ade-bola";
		assertDoesNotThrow(() -> {
			CheckoutSystem.setNameOfCustomer(firstName, lastName, middleName, customerInfo);
		});
	}

	@Test
	void testGetCustomerNameReturnsFirstLettersOfNamesCapitalizedIfNamesAreValid(){
		CheckoutSystem.setNameOfCustomer(firstName, lastName, middleName, customerInfo);
		assertEquals("Customer name: Adebola Folorunsho Ayomide", CheckoutSystem.getCustomerName(customerInfo));
	}

/*	@Test
	void testReturnProductIfDetectedReturnsListOfItemsMatchingSearchInputForCashierToPick(){
		searchInput = "mil";
// If cashier wants to pick "Milk 25g" for example, and they enter "mil. Prompt for input in this function."
		actual = CheckoutSystem.returnProductIfDetected(searchInput, allProducts);
		expected = "Milk 25g";
		assertEquals(expected, actual);
	}
*/ commented out because input is required

	@Test
	void testGetProductPriceReturnsPriceOfProductEnteredIfSizeIsIncluded(){
		product = "milk 25g";
		assertEquals("NGN 2200.00", CheckoutSystem.getProductPrice(product, allProducts, allPrices, purchasedItemInfo));
	}

	@Test
	void testAddPurchasedProductToListReturnsSuccessfulIfQuantityEnteredIsAValidInteger(){
		product = "milk 25g";
		CheckoutSystem.getProductPrice(product, allProducts, allPrices, purchasedItemInfo);
		String quantity = "2";
		actual = CheckoutSystem.addPurchasedProductToList(purchasedItemInfo, quantity, customerPurchase);
		expected = "Product added succesfully. Add more items?\nEnter Yes/No";
		assertEquals(expected, actual);

	}

	@Test
	void testAddPurchasedProductToListThrowsErrorIfQuantityEnteredIsNotAValidInteger(){
		product = "milk 25g";
		CheckoutSystem.getProductPrice(product, allProducts, allPrices, purchasedItemInfo);
		String quantity = "2.5";
		assertThrows(IllegalArgumentException.class, () -> {
			CheckoutSystem.addPurchasedProductToList(purchasedItemInfo, quantity, customerPurchase);
		});
	}

	@Test
	void testAddPurchasedProductToListThrowsErrorIfQuantityEnteredIsLessThanOne(){
		product = "milk 25g";
		CheckoutSystem.getProductPrice(product, allProducts, allPrices, purchasedItemInfo);
		String quantity = "0";
		assertThrows(IllegalArgumentException.class, () -> {
			CheckoutSystem.addPurchasedProductToList(purchasedItemInfo, quantity, customerPurchase);
		});
	}

	@Test
	void testComputeTotalCostOfItemsPurchased(){
		
		product = "milk 25g";
		CheckoutSystem.getProductPrice(product, allProducts, allPrices, purchasedItemInfo);
		String quantity = "2";
		CheckoutSystem.addPurchasedProductToList(purchasedItemInfo, quantity, customerPurchase);
		purchasedItemInfo = new String[4];
		product = "cornflakes 35g";
		CheckoutSystem.getProductPrice(product, allProducts, allPrices, purchasedItemInfo);
		quantity = "5";
		CheckoutSystem.addPurchasedProductToList(purchasedItemInfo, quantity, customerPurchase);
		actual = CheckoutSystem.computeTotalCostOfItemsPurchased(customerPurchase).toString();
		expected = "19400.00";
		assertEquals(expected, actual);
	}


}
