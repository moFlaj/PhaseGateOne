import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestMenstrualCycleAppMod{
	String inputDate;
	String actual;
	String expected;
	int numberOfFlowDays;
	int cycleLength;

	@BeforeEach
	void setUp(){
		inputDate = "2025-06-29";
		numberOfFlowDays = 5;
		cycleLength = 28;
			
	}

	@Test
	void testPeriodFlowDaysReturnsCorrectDateIfFlowSpansFromCurrentMonthToTheNextMonthOfSameYear(){
		actual = MenstrualCycleAppMod.periodFlowDays(inputDate, numberOfFlowDays);
		expected = "Your period will likely last from Sunday, 2025-06-29 to Friday, 2025-07-04.";
		assertEquals(expected, actual);
	}

	@Test
	void testPeriodFlowDaysReturnsCorrectDateIfFlowSpansFromCurrentMonthToTheNextMonthOfDifferentYear(){
		inputDate = "2025-12-29";
		actual = MenstrualCycleAppMod.periodFlowDays(inputDate, numberOfFlowDays);
		expected = "Your period will likely last from Monday, 2025-12-29 to Saturday, 2026-01-03.";
		assertEquals(expected, actual);
	}

	@Test
	void testPeriodFlowDaysThrowsErrorIfInvalidDateInputIsEntered(){
// Month 13 when there are only 12 months in a year.
		inputDate = "2025-13-29";
		assertThrows(IllegalArgumentException.class, () -> {
			MenstrualCycleAppMod.periodFlowDays(inputDate, numberOfFlowDays);
		});
	}

	@Test
	void testPeriodFlowDaysThrowsErrorIfDateIsInputIsNotOfFormatSpecifiedInLogic(){
// Format specified is YYYY-MM-DD.
		inputDate = "2025-29-12";
		assertThrows(IllegalArgumentException.class, () -> {
			MenstrualCycleAppMod.periodFlowDays(inputDate, numberOfFlowDays);
		});
	}

	@Test
	void testNextPeriodStartDateReturnsNextPeriodStartDateWhichIs28DaysAfterStartDateOfCurrentOrPreviousPeriod(){
		actual = MenstrualCycleAppMod.nextPeriodStartDate(inputDate, cycleLength);
		expected = "Your next period begins on Sunday, 2025-07-27.";
		assertEquals(expected, actual);

	}


	@Test
	void testNextPeriodStartDateThrowsErrorIfIncorrectDateInputIsEntered(){
		// Format specified is YYYY-MM-DD.
		inputDate = "2025-29-12";
		assertThrows(IllegalArgumentException.class, () -> {
			MenstrualCycleAppMod.nextPeriodStartDate(inputDate, cycleLength);
		});
	}
}
