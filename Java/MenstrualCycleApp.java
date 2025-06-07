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
		List<String> monthsOfThirtyDays = List.of("September", "April", "June", "November");
		List<String> monthsOfThirtyOneDays = List.of("January", "March", "May", "July", "August", "October", "December");
		List<String> monthsOfTwentyEightDays = List.of("February");

		String flowDates = "";
		int convertToInteger = Integer.parseInt(day);
		
//Flow days in women lasts around 3 - 7 days.
		int averageOfFlowDays = (3 + 7)/2;

		throwErrorsIfDayExceedsNumberOfDaysInAParticularMonth(month,day);
		if(monthsOfThirtyDays.contains(month)){
			if(convertToInteger + averageOfFlowDays > 30){

				flowDates = "Your period will likely last from " + month + " " + convertToInteger + " to " +  months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + averageOfFlowDays) - 31);
			}
			else{
				flowDates = "Your period will likely last from " + month + " " + convertToInteger + " to " +  month + " " + ((convertToInteger + averageOfFlowDays) - 1);
			}
		}

		else if(monthsOfThirtyOneDays.contains(month)){
			if(convertToInteger + averageOfFlowDays > 31){

				flowDates = "Your period will likely last from " + month + " " + convertToInteger + " to " +  months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + averageOfFlowDays) - 32);
			}
			else{
				flowDates = "Your period will likely last from " + month + " " + convertToInteger + " to " +  month + " " + ((convertToInteger + averageOfFlowDays) - 1);
			}
		}

		else if(monthsOfTwentyEightDays.contains(month)){
			if(convertToInteger + averageOfFlowDays > 28){

				flowDates = "Your period will likely last from " + month + " " + convertToInteger + " to " +  months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + averageOfFlowDays) - 29);
			}
			else{
				flowDates = "Your period will likely last from " + month + " " + convertToInteger + " to " +  month + " " + ((convertToInteger + averageOfFlowDays) - 1);
			}
		}

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
				ovulationDate = "Your ovulation day is " + months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + calcOvDate) - 31);
			}
			else{
				ovulationDate = "Your ovulation day is " + month + " " + (convertToInteger + calcOvDate);

			}
			

		}
		else if(monthsOfTwentyEightDays.contains(month)){
			if(convertToInteger + calcOvDate > 28){
				ovulationDate = "Your ovulation day is " + months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + calcOvDate) - 28);
			}
			else{
				ovulationDate = "Your ovulation day is " + month + " " + (convertToInteger + calcOvDate);

			}
		}

		return ovulationDate;


	}

	public static String identifyFertileWindow(String month, String day, List<String> months){
		throwErrorsForInvalidData(month,day,months);
//Fertile window is five days before ovulation day and one day after
//If ovulation day is fourteen days after first flow, then fertile window before ovulation day is 14 - 5, and end of fertile window is 14 + 1

		int fertileWindowBefore = 9;
		int fertileWindowAfter = 15;
		List<String> monthsOfThirtyDays = List.of("September", "April", "June", "November");
		List<String> monthsOfThirtyOneDays = List.of("January", "March", "May", "July", "August", "October", "December");
		List<String> monthsOfTwentyEightDays = List.of("February");

		String fertileWindow = "";
		int convertToInteger = Integer.parseInt(day);

		throwErrorsIfDayExceedsNumberOfDaysInAParticularMonth(month,day);

		if(monthsOfThirtyDays.contains(month)){
			if(convertToInteger + fertileWindowBefore > 30 && convertToInteger + fertileWindowAfter > 30){
				fertileWindow = "Fertile window will likely start on " + months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + fertileWindowBefore) - 30) + ", to end on " +  months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + fertileWindowAfter) - 30);
			

			}
			else if(convertToInteger + fertileWindowBefore <= 30 && convertToInteger + fertileWindowAfter > 30){

				fertileWindow = "Fertile window will likely start on " + month + " " + (convertToInteger + fertileWindowBefore) + ", to end on " +  months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + fertileWindowAfter) - 30);

			}

			else if(convertToInteger + fertileWindowBefore <= 30 && convertToInteger + fertileWindowAfter <= 30){

				fertileWindow = "Fertile window will likely start on " + month + " " + (convertToInteger + fertileWindowBefore) + ", to end on " +  month + " " + (convertToInteger + fertileWindowAfter);

			}

		}

		else if(monthsOfThirtyOneDays.contains(month)){

			if(convertToInteger + fertileWindowBefore > 31 && convertToInteger + fertileWindowAfter > 31){

				fertileWindow = "Fertile window will likely start on " + months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + fertileWindowBefore) - 31) + ", to end on " +  months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + fertileWindowAfter) - 31);
			

			}
			else if(convertToInteger + fertileWindowBefore <= 31 && convertToInteger + fertileWindowAfter > 31){

				fertileWindow = "Fertile window will likely start on " + month + " " + (convertToInteger + fertileWindowBefore) + ", to end on " +  months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + fertileWindowAfter) - 30);

			}

			else if(convertToInteger + fertileWindowBefore <= 31 && convertToInteger + fertileWindowAfter <= 31){

				fertileWindow = "Fertile window will likely start on " + month + " " + (convertToInteger + fertileWindowBefore) + ", to end on " +  month + " " + (convertToInteger + fertileWindowAfter);

			}


		}

		else if(monthsOfTwentyEightDays.contains(month)){

			if(convertToInteger + fertileWindowBefore > 28 && convertToInteger + fertileWindowAfter > 28){

				fertileWindow = "Fertile window will likely start on " + months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + fertileWindowBefore) - 28) + ", to end on " +  months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + fertileWindowAfter) - 28);
			

			}
			else if(convertToInteger + fertileWindowBefore <= 28 && convertToInteger + fertileWindowAfter > 28){

				fertileWindow = "Fertile window will likely start on " + month + " " + (convertToInteger + fertileWindowBefore) + ", to end on " +  months.get(months.indexOf(month) + 1) + " " + ((convertToInteger + fertileWindowAfter) - 28);

			}

			else if(convertToInteger + fertileWindowBefore <= 28 && convertToInteger + fertileWindowAfter <= 28){

				fertileWindow = "Fertile window will likely start on " + month + " " + (convertToInteger + fertileWindowBefore) + ", to end on " +  month + " " + (convertToInteger + fertileWindowAfter);

			}


		}
		return fertileWindow;

	}





}
