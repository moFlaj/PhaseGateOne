import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.util.InputMismatchException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestMenstrualCycleApp{
	String month;
	String day;
	List<String> months;
	String actual;
	String expected;

	@BeforeEach
	void setUp(){
		month = "June";
		day = "3";
		months = List.of("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

	}

	@Test
	void testPeriodFlowDatesExists(){
		MenstrualCycleApp.periodFlowDates(month,day,months);

	}
	
	@Test
	void testPeriodFlowOnlyAcceptsWordsAsItsFirstArgument(){
		month = "5";
		assertThrows(InputMismatchException.class, () -> {
			MenstrualCycleApp.periodFlowDates(month,day,months);
		});
	}

	@Test
	void testPeriodFlowDateThrowsErrorIfMonthEnteredIsNotValid(){
		month = "Blue";
		assertThrows(IllegalArgumentException.class, () -> {
			MenstrualCycleApp.periodFlowDates(month,day,months);
		});
	}

	@Test
	void testRuntimeErrorOccursIfAnythingAsidesAnIntegerIsPassedAsSecondArgument(){
		try{
			day = "REYYD";
			MenstrualCycleApp.periodFlowDates(month,day,months);
		}catch(NumberFormatException error){
			actual = error.getMessage();
		}
		expected = "For input string: \"REYYD\"";
		assertEquals(expected, actual);


	}
	@Test
	void testPeriodFlowDateShowsHowLongFlowDaysMayLatIfRequiredInformationIsProvided(){
		actual = MenstrualCycleApp.periodFlowDates(month,day,months);
		expected =  "Your period will likely last from June 3 to June 7";
		assertEquals(expected, actual);

	}
	@Test
	void testNextPeriodStartDateReturnsCorrectDateIfNextPeriodWillBeginInTheUpcomingMonth(){
		actual = MenstrualCycleApp.nextPeriodStartDate(month,day,months);
		expected = "Your next period begins on July 1";
		assertEquals(expected, actual);

	}

	@Test
	void testNextPeriodStartDateReturnsCorrectDateIfNextPeriodWillBeginInTheSameMonthAsPrevious(){
		day = "1";
		actual = MenstrualCycleApp.nextPeriodStartDate(month,day,months);
		expected = "Your next period begins on June 29";
		assertEquals(expected, actual);

	}
	



}
