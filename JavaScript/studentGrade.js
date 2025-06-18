const prompt = require('prompt-sync')();

function inputStudentScores(allStudents, noOfStudents, noOfSubjects) {
	let studentCounter = 1;
	allStudents = [];

	for(let index = 0; index < noOfStudents; index++){
		let eachStudent = [];
		let total = 0;

		for(let subjectScore = 0; subjectScore < noOfSubjects; subjectScore++) {
			while(true){
				console.log("Entering score for student " + studentCounter);
				let score = parseFloat(prompt("Enter score for subject " + (subjectScore + 1) + ": "));
				if (score >= 0 && score <= 100){
					total += score;
					eachStudent[subjectScore] = score;
					break;
				} else {
					console.log("Invalid score.\nScore must be between 0 and 100!");
				}
			}
		}

		eachStudent[noOfSubjects] = total;
		eachStudent[noOfSubjects + 1] = total / noOfSubjects;
		eachStudent[noOfSubjects + 2] = 0; // placeholder for position
		allStudents.push(eachStudent);
		console.log("Saving >>>>>>>>>>>>>>>>\nSaved successfully");
		studentCounter++;
	}

	return allStudents;
}

function postionOfStudents(allStudents) {
	let averageScoresOfEachStudent = [];

	for(let index = 0; index < allStudents.length; index++){
		let eachStudent = allStudents[index];
		averageScoresOfEachStudent.push(eachStudent[eachStudent.length - 2]); //adds each studen average score to a list.
	}

	for(let index = 0; index < averageScoresOfEachStudent.length - 1; index++){
		for (let spanIndex = index + 1; spanIndex < averageScoresOfEachStudent.length; spanIndex++) {
			if(averageScoresOfEachStudent[index] < averageScoresOfEachStudent[spanIndex]) {
				let tempHold = averageScoresOfEachStudent[index];
				averageScoresOfEachStudent[index] = averageScoresOfEachStudent[spanIndex];
				averageScoresOfEachStudent[spanIndex] = tempHold;
			}
		}
	}

	let studentHasPosition = [];

	for (let index = 0; index < allStudents.length; index++) {
		let eachStudent = allStudents[index];

		for (let highestToLowest = 0; highestToLowest < averageScoresOfEachStudent.length; highestToLowest++) {
			if (eachStudent[eachStudent.length - 2] === averageScoresOfEachStudent[highestToLowest] &&
				!studentHasPosition.includes(eachStudent)) {
				eachStudent[eachStudent.length - 1] = highestToLowest + 1;
				studentHasPosition.push(eachStudent);
			}
		}
	}

	return allStudents;
}

function displayRecordSheet(allStudents, noOfSubjects){
	console.log("===============================================");

	let header = [];
	header[0] = "STUDENT";
	for (let index = 1; index <= noOfSubjects; index++) {
		header[index] = "SUB" + index;
	}
	header[header.length] = "TOTAL";
	header[header.length] = "AVE";
	header[header.length] = "POS";

	for(let eachHeader of header){
		process.stdout.write(eachHeader + "\t");
		if(header[0] === eachHeader) process.stdout.write("\t");
	}
	console.log("\n==============================================================================================");

	let studentCounter = 1;
	for (let indexOfEach = 0; indexOfEach < allStudents.length; indexOfEach++) {
		let eachStudentAndScores = allStudents[indexOfEach];
		process.stdout.write("Student " + studentCounter + "\t");

		for (let index = 0; index < eachStudentAndScores.length; index++) {
			let printEach = eachStudentAndScores[index];
			if (eachStudentAndScores[eachStudentAndScores.length - 1] === printEach)
				process.stdout.write(parseInt(printEach) + "");
			else if (eachStudentAndScores[eachStudentAndScores.length - 2] === printEach)
				process.stdout.write(printEach.toFixed(2) + "\t");
			else
				process.stdout.write(printEach + "\t");
		}

		studentCounter++;
		console.log();
	}
	console.log("==========================================================================================");
	console.log("==========================================================================================");
}

function subjectSummary(allStudents, noOfSubjects, noOfStudents) {

	for (let eachSubjectIndex = 0; eachSubjectIndex < noOfSubjects; eachSubjectIndex++){

		let highest = -Infinity;
		let lowest = Infinity;
		let indexOfHighest = 0;
		let highestScoringStudent = 0;
		let indexOfLowest = 0;
		let lowestScoringStudent = 0;
		let sumSubjectTotal = 0;
		let countNoOfPasses = 0;
		let countNoOfFailures = 0;

		for (let indexOfEach = 0; indexOfEach < allStudents.length; indexOfEach++){
			let eachStudentScoreInSubject = allStudents[indexOfEach];

			if (eachStudentScoreInSubject[eachSubjectIndex] > highest){
				highest = eachStudentScoreInSubject[eachSubjectIndex];
				highestScoringStudent = indexOfHighest + 1;
			} else if(eachStudentScoreInSubject[eachSubjectIndex] < lowest){
				lowest = eachStudentScoreInSubject[eachSubjectIndex];
				lowestScoringStudent = indexOfLowest + 1;
			}

			if (eachStudentScoreInSubject[eachSubjectIndex] >= 50) countNoOfPasses++;
			else{countNoOfFailures++;}

			sumSubjectTotal += eachStudentScoreInSubject[eachSubjectIndex];
			indexOfHighest++;
			indexOfLowest++;
		}

		console.log("Subject " + (eachSubjectIndex + 1));
		console.log("Highest scoring student is: Student " + highestScoringStudent + " scoring " + highest.toFixed(2) + ".");
		console.log("Lowest scoring student is: Student " + lowestScoringStudent + " scoring " + lowest.toFixed(2) + ".");
		console.log("Total score is: " + sumSubjectTotal.toFixed(2) + ".");
		console.log("Average score is: " + (sumSubjectTotal / noOfStudents).toFixed(2) + ".");
		console.log("Number of passes: " + countNoOfPasses + ".");
		console.log("Number of failures: " + countNoOfFailures + ".");

	}
}


let noOfStudents = Number(prompt('How many students do you have? '))
let noOfSubjects = Number(prompt('How many subjects do they offer? '))
let allStudents = []
allStudents = inputStudentScores(allStudents, noOfStudents, noOfSubjects)
allStudents = postionOfStudents(allStudents)
displayRecordSheet(allStudents, noOfSubjects)
subjectSummary(allStudents, noOfSubjects, noOfStudents)
