import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class StudentGrade{

	public static double[][] inputStudentScores(double[][] allStudents, int noOfStudents, int noOfSubjects){
		Scanner input = new Scanner(System.in);
 		int studentCounter = 1;
		allStudents = new double[noOfStudents][noOfSubjects + 3];

		for(double[] eachStudent : allStudents){
			double total = 0;
			for(int subjectScore = 0; subjectScore < noOfSubjects; subjectScore++){
				while(true){
					System.out.println("Entering score for student " + studentCounter);
					System.out.println("Enter score for subject " + (subjectScore + 1));
					double score = input.nextDouble();

					if(100 >= score && score >= 0){
						total+=score;
						eachStudent[subjectScore] = score;
						break;
					}
					else{
						System.out.println("Invalid score.\nScore must be between 0 and 100!");
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

			averageScoresOfEachStudent.add(eachStudent[eachStudent.length - 2]); //adds each studen average score to a list.
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
				else if(eachStudentScoreInSubject[eachSubjectIndex] < lowest){
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
			System.out.printf("Highest scoring student is: Student %d scoring %2f.%n", highestScoringStudent, highest);
			System.out.printf("Lowest scoring student is: Student %d scoring %2f.%n", lowestScoringStudent, lowest);
			System.out.printf("Total score is: %.2f.%n", sumSubjectTotal);
			System.out.printf("Average score is: %.2f.%n", (sumSubjectTotal/noOfStudents));
			System.out.printf("Number of passes: %d.%n", countNoOfPasses);
			System.out.printf("Number of failures: %d.%n", countNoOfFailures);
	
		}


	}


	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("How many students do you have?");
		int noOfStudents = input.nextInt();
		System.out.println("How many subjects do they offer?");
		int noOfSubjects = input.nextInt();
		double[][] allStudents = {{}};
		allStudents = inputStudentScores(allStudents, noOfStudents, noOfSubjects);
		allStudents =  postionOfStudents(allStudents);
		displayRecordSheet(allStudents, noOfSubjects);
		subjectSummary(allStudents, noOfSubjects, noOfStudents);
		
	}


}
