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

		if(Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31){
			throw new IllegalArgumentException("Enter a valid number");
		}


	}

	public static void throwErrorsIfDayExceedsNumberOfDaysInAParticularMonth(String month, String day){
		List<String> monthsOfThirtyDays = List.of("September", "April", "June", "November");
		List<String> monthsOfThirtyOneDays = List.of("January", "March", "May", "July", "August", "October", "December");
		List<String> monthsOfTwentyEightDays = List.of("February");

		if(monthsOfThirtyDays.contains(month) && Integer.parseInt(day) > 30){
			throw new IllegalArgumentException(month + " has only thirty days.");

		}
		else if(monthsOfTwentyEightDays.contains(month) && Integer.parseInt(day) > 28){
			throw new IllegalArgumentException(month + " has only twenty eight days.");

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
		List<String> monthsOfTwentyEightDays = List.of("February");
		int cycleLength = 28;
//Average of period cycle length is 28 days.

		String nextPeriodStartSate = "";
		int convertToInteger = Integer.parseInt(day);
		throwErrorsIfDayExceedsNumberOfDaysInAParticularMonth(month,day);
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
		else if(monthsOfTwentyEightDays.contains(month)){
			if(convertToInteger + cycleLength > 28){
				nextPeriodStartSate = "Your next period begins on " + months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + cycleLength) - 28);
			}
			else{
				nextPeriodStartSate = "Your next period begins on " + month + " " + (convertToInteger + cycleLength);

			}
		}
		return nextPeriodStartSate;


	}

	public static String findOvulationDay(String month, String day, List<String> months){
		throwErrorsForInvalidData(month,day,months);
//Ovulation day occurs 14 days before next next period date
		int calcOvDate = 14;
		List<String> monthsOfThirtyDays = List.of("September", "April", "June", "November");
		List<String> monthsOfThirtyOneDays = List.of("January", "March", "May", "July", "August", "October", "December");
		List<String> monthsOfTwentyEightDays = List.of("February");
		String ovulationDate = "";
		int convertToInteger = Integer.parseInt(day);
		throwErrorsIfDayExceedsNumberOfDaysInAParticularMonth(month,day);
		if(monthsOfThirtyDays.contains(month)){
			if(convertToInteger + calcOvDate > 30){
				ovulationDate = "Your ovulation day is " + months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + calcOvDate) - 30);
			}
			else{
				ovulationDate = "Your ovulation day is " + month + " " + (convertToInteger + calcOvDate);

			}

		}
		else if(monthsOfThirtyOneDays.contains(month)){
			if(convertToInteger + calcOvDate > 31){
				ovulationDate = "Your next period begins on " + months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + calcOvDate) - 31);
			}
			else{
				ovulationDate = "Your next period begins on " + month + " " + (convertToInteger + calcOvDate);

			}
			

		}
		else if(monthsOfTwentyEightDays.contains(month)){
			if(convertToInteger + calcOvDate > 28){
				ovulationDate = "Your next period begins on " + months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + calcOvDate) - 28);
			}
			else{
				ovulationDate = "Your next period begins on " + month + " " + (convertToInteger + calcOvDate);

			}
		}

		return ovulationDate;




	}





}
