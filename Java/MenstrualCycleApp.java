import java.util.InputMismatchException;
import java.util.List;
public class MenstrualCycleApp{


	public static void throwErrorsForInvalidData(String month, String day, List<String> months){
		if(month.matches("-?\\d+")){
			throw new InputMismatchException("Invalid input. Please enter a string.");
		}

		if(!months.contains(month)){
			throw new IllegalArgumentException("Month input is not valid. Try again");
		}


	}

	public static String periodFlowDates(String month, String day, List<String> months){
		throwErrorsForInvalidData(month,day,months);
		String flowDates = "";
		int convertToInteger = Integer.parseInt(day);

//Flow days in women lasts around 3 - 7 days.

		int averageOfFlowDays = (3 + 7)/2;


		flowDates = "Your period will likely last from " + month + " " + convertToInteger + " to " + month + " " + ((convertToInteger + averageOfFlowDays) - 1);

		return flowDates;

	}


	public static String nextPeriodStartDate(String month, String day, List<String> months){
		throwErrorsForInvalidData(month,day,months);
		List<String> monthsOfThirtyDays = List.of("September", "April", "June", "November");
		List<String> monthsOfThirtyOneDays = List.of("January", "March", "May", "July", "August", "October", "December");
		int cycleLength = 28;
//Average of period cycle length is 28 days.

		String nextPeriodStartSate = "";
		int convertToInteger = Integer.parseInt(day);

		if(monthsOfThirtyDays.contains(month)){
			if(convertToInteger + cycleLength > 30){
				nextPeriodStartSate = "Your next period begins on " + months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + cycleLength) - 30);
			}
			else{
				nextPeriodStartSate = "Your next period begins on " + month + " " + (convertToInteger + cycleLength);

			}

		}
		else if(monthsOfThirtyOneDays.contains(month)){
			if(convertToInteger + cycleLength > 31){
				nextPeriodStartSate = "Your next period begins on " + months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + cycleLength) - 31);
			}
			else{
				nextPeriodStartSate = "Your next period begins on " + month + " " + (convertToInteger + cycleLength);

			}
			

		}
		return nextPeriodStartSate;


	}





}
