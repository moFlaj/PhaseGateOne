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
			System.out.println("Entering score for student " + studentCounter);
			for(int subjectScore = 0; subjectScore < noOfSubjects; subjectScore++){
				while(true){
					System.out.println("Entering score for subject " + (subjectScore + 1));
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

	public static void postionOfStudents(double[][] allStudents){

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
	}


	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("How many students do you have?");
		int noOfStudents = input.nextInt();
		System.out.println("How many subjects do they offer?");
		int noOfSubjects = input.nextInt();
		double[][] allStudents = {{}};
		postionOfStudents(inputStudentScores(allStudents, noOfStudents, noOfSubjects));

	}



}
