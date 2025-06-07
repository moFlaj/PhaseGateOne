import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MenstrualCycleAppMod{

	public static String periodFlowDays(String dateOfFirstFlow){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try{
			LocalDate date = LocalDate.parse(dateOfFirstFlow, formatter);
// To make sure input is of the format specified is why the "formatter" object is part of the arguments passed.

			String dayOfWeekOfStartOfFlow = date.getDayOfWeek().toString();
// Found out getDayOfWeek() returns an enum type.

			String capitalizeFirstLetterOfStartDay = dayOfWeekOfStartOfFlow.substring(0,1).toUpperCase() + dayOfWeekOfStartOfFlow.substring(1).toLowerCase();

			int averageOfFlowDays = (3 + 7)/2;
// Flow days in women lasts around 3 - 7 days.

			LocalDate newDate = date.plusDays(averageOfFlowDays);
			String dayOfWeekOfEndOfFlow = newDate.getDayOfWeek().toString();
			String capitalizeFirstLetterOfEndDay = dayOfWeekOfEndOfFlow.substring(0,1).toUpperCase() + dayOfWeekOfEndOfFlow.substring(1).toLowerCase();

			return "Your period will likely last from " + capitalizeFirstLetterOfStartDay + ", " + date + " to " + capitalizeFirstLetterOfEndDay + ", " + newDate.format(formatter) + ".";

		}catch(DateTimeParseException invalidDate){

//Throws error if input format is invalid. This can also be made more strict using the Pattern and Matcher classes.

			throw new IllegalArgumentException("Invalid date format or values. Expected format: YYYY-MM-DD.");

		}
	}

}
