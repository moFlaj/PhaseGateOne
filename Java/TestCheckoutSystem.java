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

public class TestCheckoutSystem{

	String firstName;
	String lastName;
	String middleName;
	String[] customerInfo;
	String actaual;
	String expected;
	String product;
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
		allProducts = new String[] {"Milk	50g", "Milk	25g", "Cornflakes	35g", "Cornflakes	15g", "Milo	20g", "Rice	50g", "Semolina	30g", "Frozen Chicken	20g", "Frozen chicken 	15g", "ChiActive	10g", "Ribena	8g"};

		allPrices = new String[] {"1700.00", "1200.00", "3000.00", "8000.00", "15700.00", "2200.00", "4200.00", "3500.00", "1100.00", "5450.00", "5800.00"};

		purchasedItemInfo = new String[4];
		customerPurchase = new ArrayList<String[]>();
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
		assertEquals("Adebola Folorunsho Ayomide", CheckoutSystem.getCustomerName(customerInfo));
	}

	@Test
	void testGetProductPriceReturnsPriceOfProductEnteredIfSizeIsIncluded(){
		product = "milk	25g";
//one tab spacing between product and size
		assertEquals("NGN 2200.00", CheckoutSystem.getProductPrice(product, allProducts, allPrices, purchasedItemInfo));
	}

	@Test
	void testGetProductPriceReturnsProductSizeAndPriceIfSearchInputIsNotCompletelyStated(){
		product = "mil";
		assertEquals("Milk	25g	2200.00\nMilk	50g	4200.00\nMilo	20g	3500.00\n", CheckoutSystem.getProductPrice(product, allProducts, allPrices, purchasedItemInfo));

	}


}