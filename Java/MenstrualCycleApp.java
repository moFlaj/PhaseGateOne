import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MenstrualCycleAppMod{

	public static String returnDayOfWeek(String date){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate customDate = LocalDate.parse(date, formatter);

		String dayOfWeek = customDate.getDayOfWeek().toString();
// Found out getDayOfWeek() returns an enum type.

		String capitalizeFirstLetterOfDay = dayOfWeek.substring(0,1).toUpperCase() + dayOfWeek.substring(1).toLowerCase();

		return capitalizeFirstLetterOfDay;
	}

	public static void throwErrorsIfDateInputIsInvalid(String date){
//Created this function to avoid repeating the try and catch in all functions of this task. Called this function in the first line of the following functions so errors would be thrown if date input is invalid.

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try{
			LocalDate customDate = LocalDate.parse(date, formatter);
		}catch(DateTimeParseException invalidDate){
			throw new IllegalArgumentException("Invalid date format or values. Expected format: YYYY-MM-DD.");

		}

	}

	public static LocalDate formatDate(String date){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate customDate = LocalDate.parse(date, formatter);
// To make sure input is of the format specified is why the "formatter" object is part of the arguments passed.

		return customDate;
	}

	public static String periodFlowDays(String dateOfFirstFlow, int averageOfFlowDays){

		throwErrorsIfDateInputIsInvalid(dateOfFirstFlow);

		return "Your current period flow will last from " + returnDayOfWeek(dateOfFirstFlow) + ", " + formatDate(dateOfFirstFlow) + " to " + returnDayOfWeek(formatDate(dateOfFirstFlow).plusDays(averageOfFlowDays).toString()) + ", " + formatDate(dateOfFirstFlow).plusDays(averageOfFlowDays)  + ".";

	}


	public static String nextPeriodStartDate(String dateOfFirstFlow, int cycleLength){

		throwErrorsIfDateInputIsInvalid(dateOfFirstFlow);

		return "Your next period begins on " + returnDayOfWeek(formatDate(dateOfFirstFlow).plusDays(cycleLength).toString()) + ", " + formatDate(dateOfFirstFlow).plusDays(cycleLength) + ".";
	}

	public static String findOvulationDay(LocalDate dateOfNextPeriod){

		throwErrorsIfDateInputIsInvalid(dateOfNextPeriod.toString());
		int ovulationDay = 14;
//Ovulation day occurs 14 days before next period start date.

		return "Ovulation day of current period is " + returnDayOfWeek(dateOfNextPeriod.minusDays(ovulationDay).toString()) + ", " + dateOfNextPeriod.minusDays(ovulationDay) + ".";
	}
}
