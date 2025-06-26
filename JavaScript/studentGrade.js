const prompt = require('prompt-sync')();

function inputStudentScores(allStudents, noOfStudents, noOfSubjects) {
	let studentCounter = 1;
	allStudents = [];

	for(let index = 0; index < noOfStudents; index++){
		let eachStudent = [];
		let total = 0;

		for(let subjectScore = 0; subjectScore < noOfSubjects; subjectScore++) {
			while(true){
				try{
					console.log("Entering score for student " + studentCounter);
					let score = parseFloat(prompt("Enter score for subject " + (subjectScore + 1) + ": "));
					if(isNaN(score))throw new Error("You may have entered an invalid character. Retry.");
					if (score >= 0 && score <= 100){
						total += score;
						eachStudent[subjectScore] = score;
						break;
					} else {
						console.log("Invalid score.\nScore must be between 0 and 100!");
					}
				} catch(invalid){
					console.log(invalid.message);
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

		let highest = Number.MIN_SAFE_INTEGER;
		let lowest = Number.MAX_SAFE_INTEGER;
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

			} if(eachStudentScoreInSubject[eachSubjectIndex] < lowest){
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


function classSummary(allStudents, noOfSubjects, noOfStudents) {
	let compareSubjectFailures = 0;
	let compareSubjectPasses = 0;
	let overallHighestSubjectScore = Number.MIN_SAFE_INTEGER;
	let overallLowestestSubjectScore = Number.MAX_SAFE_INTEGER;
	let allStudentsIndex = 0;
	let highestScoreInAllIndex = 0;
	let subjectWithHighestScore = 0;
	let lowestScoreInAll = 0;
	let subjectWithLowestScore = 0;
	let sumStudentTotal = 0;
	let highestStudentsTotal = Number.MIN_SAFE_INTEGER;
	let lowestStudentsTotal = Number.MAX_SAFE_INTEGER;
	let bestGraduatingStudent = 0;
	let worstGraduatingStudent = 0;
	let hardestSubjectIndex = 0;
	let easiestSubjectIndex = 0;
	let classTotalScore = 0;
	let countNoOfPasses = 0;
	let countNoOfFailures = 0;

	for(let eachSubjectIndex = 0; eachSubjectIndex < noOfSubjects; eachSubjectIndex++){

		for(let eachStudentScoreInSubject of allStudents){

			if(eachStudentScoreInSubject[eachSubjectIndex] > overallHighestSubjectScore){
				overallHighestSubjectScore = eachStudentScoreInSubject[eachSubjectIndex];
				highestScoreInAllIndex = allStudentsIndex + 1;
				subjectWithHighestScore = eachSubjectIndex + 1;
			}

			if(eachStudentScoreInSubject[eachSubjectIndex] < overallLowestestSubjectScore) {
				overallLowestestSubjectScore = eachStudentScoreInSubject[eachSubjectIndex];
				lowestScoreInAll = allStudentsIndex + 1;
				subjectWithLowestScore = eachSubjectIndex + 1;
			}

			if (eachStudentScoreInSubject[eachSubjectIndex] >= 50) {
				countNoOfPasses++;
			} else{
				countNoOfFailures++;
			}

			if(eachSubjectIndex === 0){
				for(let addScoresOfStudent = 0; addScoresOfStudent < noOfSubjects; addScoresOfStudent++){
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

		if(eachSubjectIndex === 0){
			compareSubjectFailures = countNoOfFailures;
			compareSubjectPasses = compareSubjectPasses;
			hardestSubjectIndex = eachSubjectIndex + 1;
			easiestSubjectIndex = eachSubjectIndex + 1;
		} else {
			if(countNoOfPasses > compareSubjectPasses){
				compareSubjectPasses = countNoOfPasses;
				easiestSubjectIndex = eachSubjectIndex + 1;
			} else if(countNoOfFailures > compareSubjectFailures){
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
			console.log(`\n\nThe hardest subject is subject ${hardestSubjectIndex} with ${compareSubjectFailures} failures.`);
			console.log(`The easiest subject is subject ${easiestSubjectIndex} with ${compareSubjectPasses} passes.`);
			console.log(`The overall highest score is scored by student ${highestScoreInAllIndex} in subject ${subjectWithHighestScore} scoring ${overallHighestSubjectScore.toFixed(2)}.`);
			console.log(`The overall lowest score is scored by student ${lowestScoreInAll} in subject ${subjectWithLowestScore} scoring ${overallLowestestSubjectScore.toFixed(2)}.`);
		} else{
			console.log("There were equal number of passes and failures in all subjects.");
			console.log(`The overall highest score is scored by student ${highestScoreInAllIndex} in subject ${subjectWithHighestScore} scoring ${overallHighestSubjectScore.toFixed(2)}.`);
			console.log(`The overall lowest score is scored by student ${lowestScoreInAll} in subject ${subjectWithLowestScore} scoring ${overallLowestestSubjectScore.toFixed(2)}.`);
		}

		console.log(`\n\n\nCLASS SUMMARY\n\n`);
		console.log(`Best graduating student is: student ${bestGraduatingStudent} scoring ${highestStudentsTotal.toFixed(2)}.`);
		console.log(`Worst graduating student is: student ${worstGraduatingStudent} scoring ${lowestStudentsTotal.toFixed(2)}.\n\n\n`);
	} else if(compareSubjectPasses > 0 && compareSubjectFailures === 0){
		console.log("No failures");
	} else if(compareSubjectPasses === 0 && compareSubjectFailures > 0){
		console.log("All students failed all subjects");
	}

	console.log(`Class total score is ${classTotalScore.toFixed(2)}.`);
	console.log(`Class average score is ${(classTotalScore / noOfStudents).toFixed(2)}.`);
}


let noOfStudents = "";
let noOfSubjects = "";
let convertStu = 0;
let convertSub = 0;
let runApp = true;

while(runApp){
	try{
		noOfStudents = prompt("How many students do you have? ");
		convertStu = parseInt(noOfStudents);
		if(isNaN(noOfStudents))throw new Error("You may have entered an invalid character. Start over");
		if(convertStu < 1) throw new Error("This field requires a positive integer.");
     

		noOfSubjects = prompt("How many subjects do they offer? ");
		convertSub = parseInt(noOfSubjects);
		if(isNaN(convertSub))throw new Error("You may have entered an invalid character. Start over");
		if(convertSub < 1)throw new Error("This field requires a positive integer. Retry.");

		let allStudents = [];
		allStudents = inputStudentScores(allStudents, convertStu, convertSub);
		allStudents = postionOfStudents(allStudents);
		displayRecordSheet(allStudents, convertSub);
		subjectSummary(allStudents, convertSub, convertStu);
		classSummary(allStudents, convertSub, convertStu);

		runApp = false;

	} catch(invalid) {
		console.log(invalid.message);
	}
}
