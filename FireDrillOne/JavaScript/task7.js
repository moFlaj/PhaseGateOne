//Task 1
for(let counter = 1; counter <= 10; counter++){
	console.log(counter);
} 

//Task 2
for(let counter = 2; counter <= 10; counter+=2){
	console.log(counter);
} 

//Task 3
for(let counter = 1; counter <= 10; counter+=2){
	console.log(counter);
} 

//Task 4
for(let counter = 4; counter <= 10; counter+=4){
	console.log(counter);
} 

//Task 5
for(let counter = 4; counter <= 10; counter+=4){
	for(let printResultFiveTimes = 0; printResultFiveTimes < 5; printResultFiveTimes++){
		process.stdout.write("" + counter);
	}
	process.stdout.write(" ");
}
console.log();
//Task 6
for(let counter = 4; counter <= 10; counter+=4){
	for(let multiples = 1; multiples <= 5; multiples++){
		process.stdout.write("" + Math.pow(counter, multiples) + " ");
	}
} 
console.log();

//Task 7
for(let counter = 4; counter <= 10; counter+=4){
	let sum = 0;
	for(let multiples = 1; multiples <= 5; multiples++){
		sum += Math.pow(counter, multiples);
	}
	process.stdout.write(""  + sum + " ");
} 
console.log();

