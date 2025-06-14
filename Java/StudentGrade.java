import java.util.Scanner;
import java.util.Arrays;

public class StudentGrade{

	public static void inputStudentScores(int[][] allStudents, int noOfStudents, int noOfSubjects){
		Scanner input = new Scanner(System.in);
 		int studentCounter = 1;
		allStudents = new int[noOfStudents][noOfSubjects + 3];

		for(int[] eachStudent : allStudents){
			System.out.println("Entering score for student " + studentCounter);
			for(int subjectScore = 0; subjectScore < noOfSubjects; subjectScore++){
				while(true){
					System.out.println("Entering score for subject " + (subjectScore + 1));
					int score = input.nextInt();
					if(100 >= score && score >= 0){
						eachStudent[subjectScore] = score;
						break;
					}
					else{
						System.out.println("Invalid score.\nScore must be between 0 and 100!");
					}
				}
			}	
			
			System.out.println("Saving >>>>>>>>>>>>>>>>\nSaved successfully");
			studentCounter++;
		}

	}




	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("How many students do you have?");
		int noOfStudents = input.nextInt();
		System.out.println("How many subjects do they offer?");
		int noOfSubjects = input.nextInt();
		int[][] allStudents = {{}};
		inputStudentScores(allStudents, noOfStudents, noOfSubjects);

	}



}