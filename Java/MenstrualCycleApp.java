import java.util.InputMismatchException;
import java.util.List;
public class MenstrualCycleApp{

	public static String periodFlowDates(String month, String day, List<String> months){
		String flowDates = "";
		int convertToInteger = Integer.parseInt(day);

//Flow days in women lasts around 3 - 7 days.

		int averageOfFlowDays = (3 + 7)/2;

			if(month.matches("-?\\d+")){
				throw new InputMismatchException("Invalid input. Please enter a string.");
			}

			if(!months.contains(month)){
				throw new IllegalArgumentException("Month input is not valid. Try again");
			}

			if(!month.matches("-?\\d+") && months.contains(month)){
				flowDates = "Your period will likely last from " + month + " " + convertToInteger + "to" + month + " " + ((convertToInteger + averageOfFlowDays) - 1);

			}

		return flowDates;

	}












}