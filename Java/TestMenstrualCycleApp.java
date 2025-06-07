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
		month = "February";
		day = "27";
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
	void testPeriodFlowDatesThrowsErrorIfANegativeDayInputIsEntered(){
		day = "-30";
		assertThrows(IllegalArgumentException.class, () -> {
			MenstrualCycleApp.periodFlowDates(month,day,months);
		});
	}

	@Test
	void testPerioFlowDatesThrowsErrorIfDayEnteredExceedsNumberOfDaysInChosenMonth(){
		day = "30";
		assertThrows(IllegalArgumentException.class, () -> {
			MenstrualCycleApp.periodFlowDates(month,day,months);
		});
	}

	@Test
	void testPeriodFlowDateShowsHowLongFlowDaysMayLastIfRequiredInformationIsProvided(){
		actual = MenstrualCycleApp.periodFlowDates(month,day,months);
		expected =  "Your period will likely last from February 27 to March 3";
		assertEquals(expected, actual);

	}

	@Test
	void testNextPeriodStartDateReturnsCorrectDateIfNextPeriodWillBeginInTheUpcomingMonth(){
		actual = MenstrualCycleApp.nextPeriodStartDate(month,day,months);
		expected = "Your next period begins on March 27";
		assertEquals(expected, actual);

	}

	@Test
	void testNextPeriodStartDateReturnsCorrectDateIfNextPeriodWillBeginInTheSameMonthAsPreviousAndMonthIsNotFebruary(){
		month = "June";
		day = "1";
		actual = MenstrualCycleApp.nextPeriodStartDate(month,day,months);
		expected = "Your next period begins on June 29";
		assertEquals(expected, actual);

	}

	@Test
	void testFindOvulationDayOccurs14daysBeforeNextPeriodStartDate(){
		month = "June";
		day = "1";
		actual = MenstrualCycleApp.nextPeriodStartDate(month,day,months) + ". "  + MenstrualCycleApp.findOvulationDay(month, day, months) + ".";
		expected =  "Your next period begins on June 29. Your ovulation day is June 15.";
		assertEquals(expected, actual);

	}

	@Test
	void testDurationOfFertileWindowToConfirmIfItLastsFromFiveDaysBeforeOvulationDayAndOneDayAfterOvulationDay(){
		month = "June";
		day = "1";
		actual = MenstrualCycleApp.findOvulationDay(month, day, months) + ". " + MenstrualCycleApp.identifyFertileWindow(month,day,months) + ".";
		expected = "Your ovulation day is June 15. Fertile window will likely start on June 10, to end on June 16.";
		assertEquals(expected, actual);

	}

	@Test
	void testDurationOfFertileWindowIfOvulationDayDoesNotOccurInTheSameMonthAsFirstPeriodFlowDate(){
		month = "June";
		day = "30";
		actual = MenstrualCycleApp.findOvulationDay(month, day, months) + ". " + MenstrualCycleApp.identifyFertileWindow(month,day,months) + ".";
		expected = "Your ovulation day is July 14. Fertile window will likely start on July 9, to end on July 15.";
		assertEquals(expected, actual);

	}

	@Test
	void testMarkSafePeriodReturnsCorrectSafePeriodDuration(){
		actual = MenstrualCycleApp.findOvulationDay(month, day, months) + ". " + MenstrualCycleApp.identifyFertileWindow(month,day,months) + ". " + MenstrualCycleApp.markSafePeriods(month,day,months) + ".";
		expected = "Your ovulation day is March 13. Fertile window will likely start on March 8, to end on March 14. Your safe period is from February 27 to March 7 and March 15 to March 26.";
		assertEquals(expected, actual);

	}


}
