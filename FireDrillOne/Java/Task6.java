
	public static void main(String[] args){

//Task 1
		for(int counter = 1; counter <= 10; counter++){
			System.out.println(counter);
		}

//Task 2
		for(int counter = 2; counter <= 10; counter+=2){
			System.out.println(counter);
		}

//Task 3
		for(int counter = 1; counter <= 10; counter+=2){
			System.out.println(counter);
		}

//Task 4
		for(int counter = 4; counter <= 10; counter+=4){
			System.out.println(counter);
		}

//Task 5
		for(int counter = 4; counter <= 10; counter+=4){
			for(int printFiveTimes = 0; printFiveTimes < 5; printFiveTimes++){
				System.out.print(counter);
			}
			System.out.print(" "); 
		}
		System.out.println();
//Task 6
		for(int counter = 4; counter <= 10; counter+=4){
			for(int multiples = 1; multiples <= 5; multiples++){
				System.out.printf("%d ", (int)Math.pow(counter,multiples));
			} 
		}

		System.out.println();
