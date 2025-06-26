import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class StudentGrade{

	public static double[][] inputStudentScores(double[][] allStudents, int noOfStudents, int noOfSubjects){
		Scanner input = new Scanner(System.in);
 		int studentCounter = 1;
		allStudents = new double[noOfStudents][noOfSubjects + 3];

		for(double[] eachStudent : allStudents){
			double total = 0;
			String score = "";
			for(int subjectScore = 0; subjectScore < noOfSubjects; subjectScore++){
				while(true){
					try{
						System.out.println("Entering score for student " + studentCounter);
						System.out.println("Enter score for subject " + (subjectScore + 1));
						score = input.next();
						double convertScore = Double.parseDouble(score);
						if(100 >= convertScore && convertScore >= 0){
							total+=convertScore;
							eachStudent[subjectScore] = convertScore;
							break;
						}
						else{
							System.out.println("Invalid score.\nScore must be between 0 and 100!");
						}
					}catch(NumberFormatException invalidInput){
						System.out.println("You may have entered an invalid character. Retry.");
					}
				}
			}	
			eachStudent[noOfSubjects] = total;
			eachStudent[noOfSubjects + 1] = total/noOfSubjects;
			System.out.println("Saving >>>>>>>>>>>>>>>>\nSaved successfully");
			studentCounter++;
		}
		return allStudents;
	}

	public static double[][] postionOfStudents(double[][] allStudents){

		List<Double> averageScoresOfEachStudent = new ArrayList<>();

		for(double[] eachStudent : allStudents){

			averageScoresOfEachStudent.add(eachStudent[eachStudent.length - 2]); //adds each student average score to a list.
		}

		Collections.sort(averageScoresOfEachStudent, Collections.reverseOrder()); //To sort the all students average score in descending order.

		List<double[]> studentHasPosition = new ArrayList<>();

		for(double[] eachStudent : allStudents){

			for(int highestToLowest = 0; highestToLowest < averageScoresOfEachStudent.size(); highestToLowest++){
		
				if(eachStudent[eachStudent.length - 2] == averageScoresOfEachStudent.get(highestToLowest) && !studentHasPosition.contains(eachStudent)){

					eachStudent[eachStudent.length - 1] = highestToLowest + 1;	
					studentHasPosition.add(eachStudent);

				}
			}
		}
		return allStudents;
	}

	public static void displayRecordSheet(double[][] allStudents, int noOfSubjects){

		System.out.println("===============================================");

		String[] header = new String[allStudents[0].length + 1];
		header[0] = "STUDENT";
		for(int index = 1; index <= noOfSubjects; index++){	
			header[index] = "SUB" + index;
		}
		header[header.length - 1] = "POS";
		header[header.length - 2] = "AVE";
		header[header.length - 3] = "TOTAL";
		
		for(String eachHeader : header){
			System.out.print(eachHeader + "\t");
			if(header[0] == eachHeader)System.out.print("\t");
		}
		System.out.print("\n==============================================================================================\n");
		int studentCounter = 1;

		for(double[] eachStudentAndScores : allStudents){
			System.out.print("Student " + studentCounter + "\t");
			for(double printEach : eachStudentAndScores){
				if(eachStudentAndScores[eachStudentAndScores.length - 1] == printEach)System.out.print((int)printEach);
				else if(eachStudentAndScores[eachStudentAndScores.length - 2] == printEach)System.out.printf("%.2f\t", printEach);
				else{System.out.print(printEach + "\t");}

			}
			studentCounter++;
			System.out.println();
		}

		System.out.println("==========================================================================================");
		System.out.println("==========================================================================================");


	}

	public static void subjectSummary(double[][] allStudents, int noOfSubjects, int noOfStudents){		

		for(int eachSubjectIndex = 0; eachSubjectIndex < noOfSubjects; eachSubjectIndex++){

			double highest = Integer.MIN_VALUE;
			double lowest = Integer.MAX_VALUE;
			int indexOfHighest = 0;
			int highestScoringStudent = 0;
			int indexOfLowest = 0;
			int lowestScoringStudent = 0;
			double sumSubjectTotal = 0;
			int countNoOfPasses = 0;
			int countNoOfFailures = 0;

			for(double[] eachStudentScoreInSubject : allStudents){
			
				if(eachStudentScoreInSubject[eachSubjectIndex] > highest){
					highest = eachStudentScoreInSubject[eachSubjectIndex];
					highestScoringStudent = indexOfHighest + 1;
				}
				if(eachStudentScoreInSubject[eachSubjectIndex] < lowest){
					lowest = eachStudentScoreInSubject[eachSubjectIndex];
					lowestScoringStudent = indexOfLowest + 1;
				}

				if(eachStudentScoreInSubject[eachSubjectIndex] >= 50)countNoOfPasses++;
				else{countNoOfFailures++;}

				sumSubjectTotal += eachStudentScoreInSubject[eachSubjectIndex];
				indexOfHighest++;
				indexOfLowest++;
			}
			
			System.out.printf("Subject %d%n", (eachSubjectIndex + 1));
			System.out.printf("Highest scoring student is: Student %d scoring %.2f.%n", highestScoringStudent, highest);
			System.out.printf("Lowest scoring student is: Student %d scoring %.2f.%n", lowestScoringStudent, lowest);
			System.out.printf("Total score is: %.2f.%n", sumSubjectTotal);
			System.out.printf("Average score is: %.2f.%n", (sumSubjectTotal/noOfStudents));
			System.out.printf("Number of passes: %d.%n", countNoOfPasses);
			System.out.printf("Number of failures: %d.%n", countNoOfFailures);
	
		}


	}

	public static void classSummary(double[][] allStudents, int noOfSubjects, int noOfStudents){
		
		int compareSubjectFailures = 0;
		int compareSubjectPasses = 0;
		double overallHighestSubjectScore = Integer.MIN_VALUE;
		double overallLowestestSubjectScore = Integer.MAX_VALUE;
		int allStudentsIndex = 0;
		int highestScoreInAllIndex = 0;
		int subjectWithHighestScore = 0;
		int lowestScoreInAll = 0;
		int subjectWithLowestScore = 0;
		double sumStudentTotal = 0;
		double highestStudentsTotal = Integer.MIN_VALUE;
		double lowestStudentsTotal = Integer.MAX_VALUE;
		int bestGraduatingStudent = 0;
		int worstGraduatingStudent = 0;
		int hardestSubjectIndex = 0;
		int easiestSubjectIndex = 0;
		double classTotalScore = 0;
		int countNoOfPasses = 0;
		int countNoOfFailures = 0;

		for(int eachSubjectIndex = 0; eachSubjectIndex < noOfSubjects; eachSubjectIndex++){

			for(double[] eachStudentScoreInSubject : allStudents){

				if(eachStudentScoreInSubject[eachSubjectIndex] > overallHighestSubjectScore){
					overallHighestSubjectScore = eachStudentScoreInSubject[eachSubjectIndex];
					highestScoreInAllIndex = allStudentsIndex + 1;
					subjectWithHighestScore = eachSubjectIndex + 1;
				}
				
				if(eachStudentScoreInSubject[eachSubjectIndex] < overallLowestestSubjectScore){
					overallLowestestSubjectScore = eachStudentScoreInSubject[eachSubjectIndex];
					lowestScoreInAll = allStudentsIndex + 1;
					subjectWithLowestScore = eachSubjectIndex + 1;	
				}

				if(eachStudentScoreInSubject[eachSubjectIndex] >= 50)countNoOfPasses++;

				else{countNoOfFailures++;}

				if(eachSubjectIndex == 0){
					for(int addScoresOfStudent = 0; addScoresOfStudent < noOfSubjects; addScoresOfStudent++){
						sumStudentTotal += eachStudentScoreInSubject[addScoresOfStudent];
					}
					classTotalScore += sumStudentTotal;

					if(sumStudentTotal > highestStudentsTotal){
						highestStudentsTotal = sumStudentTotal;
						bestGraduatingStudent = allStudentsIndex + 1;	
					}
					if(sumStudentTotal < lowestStudentsTotal){
						lowestStudentsTotal = sumStudentTotal;
						worstGraduatingStudent = allStudentsIndex + 1;	
					}
				}
				allStudentsIndex++;
				sumStudentTotal = 0;
			}
			if(eachSubjectIndex == 0){
				compareSubjectFailures = countNoOfFailures;
				compareSubjectPasses = compareSubjectPasses;
				hardestSubjectIndex = eachSubjectIndex + 1;
				easiestSubjectIndex = eachSubjectIndex + 1;
			}
			else{
				if(countNoOfPasses > compareSubjectPasses){
					compareSubjectPasses = countNoOfPasses;
					easiestSubjectIndex = eachSubjectIndex + 1;
				}
				else if(countNoOfFailures > compareSubjectFailures){
					compareSubjectFailures = countNoOfFailures;
					hardestSubjectIndex = eachSubjectIndex + 1;
				}
			}
			countNoOfFailures = 0;
			countNoOfPasses = 0;
			allStudentsIndex = 0;
		}
		if(compareSubjectPasses > 0 && compareSubjectFailures > 0){
			if(compareSubjectFailures > compareSubjectPasses || compareSubjectFailures < compareSubjectPasses){
				System.out.printf("%n%nThe hardest subject is subject %d with %d failures.%n", hardestSubjectIndex, compareSubjectFailures);
				System.out.printf("The easiest subject is subject %d with %d passes.%n", easiestSubjectIndex, compareSubjectPasses);	
				System.out.printf("The overall highest score is scored by student %d in subject %d scoring %.2f.%n", highestScoreInAllIndex, subjectWithHighestScore, overallHighestSubjectScore);
				System.out.printf("The overall lowest score is scored by student %d in subject %d scoring %.2f.%n", lowestScoreInAll, subjectWithLowestScore, overallLowestestSubjectScore);
			}else{
				System.out.println("There were equal number of passes and failures in all subjects.");
				System.out.printf("The overall highest score is scored by student %d in subject %d scoring %.2f.%n", highestScoreInAllIndex, subjectWithHighestScore, overallHighestSubjectScore);
				System.out.printf("The overall lowest score is scored by student %d in subject %d scoring %.2f.%n", lowestScoreInAll, subjectWithLowestScore, overallLowestestSubjectScore);
			}
			System.out.printf("%n%n%nCLASS SUMMARY%n%n");
			System.out.printf("Best graduating student is: student %d scoring %.2f.%n", bestGraduatingStudent, highestStudentsTotal);
			System.out.printf("Worst graduating student is: student %d scoring %.2f.%n%n%n", worstGraduatingStudent, lowestStudentsTotal);

		}else if(compareSubjectPasses > 0 && compareSubjectFailures == 0){
			System.out.println("No failures");
		}else if(compareSubjectPasses == 0 && compareSubjectFailures > 0){
			System.out.println("All students failed all subjects");
		}
		
		System.out.printf("Class total score is %.2f.%n", classTotalScore);
		System.out.printf("Class average score is %.2f.%n", (classTotalScore/noOfStudents));

	}

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		String noOfStudents = "";
		String noOfSubjects = "";
		int convertStu = 0;
		int convertSub = 0;
		boolean runApp = true;

			while(runApp){
				try{
					System.out.println("How many students do you have?");
					noOfStudents = input.next();
					convertStu = Integer.parseInt(noOfStudents);
					if(convertStu < 1)throw new IllegalArgumentException("This field requires a positive integer.");
						
					
					System.out.println("How many subjects do they offer?");
					noOfSubjects = input.next();
					convertSub = Integer.parseInt(noOfSubjects);
					if(convertSub < 1)throw new IllegalArgumentException("This field requires a positive integer. Retry.");
					

					double[][] allStudents = {{}};
					allStudents = inputStudentScores(allStudents, convertStu, convertSub);
					allStudents =  postionOfStudents(allStudents);
					displayRecordSheet(allStudents, convertSub);
					subjectSummary(allStudents, convertSub, convertStu);
					classSummary(allStudents, convertSub, convertStu);
					runApp = false;
				

				}catch(NumberFormatException invalidInput){
					System.out.println("You may have entered an invalid character. Start over");
				}
				catch(IllegalArgumentException invalid){
					System.out.println(invalid.getMessage());
				}
			}
			
				
			
		
	}


}
