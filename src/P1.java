//File: P1.java
//Purpose:	Main class for part one of assignment 2 - Bridge crossing problem
//Programmer: Liam Craft - c3339847
//Date: 10/10/2020

public class P1 {

	public static void main(String[] args) {
		//NOTE: args as a relative file path for input
		if(args.length != 1) {
			System.out.println("Error: please check command line argument");
			return;
		}
		//create farmer objects as individual threads
		
		//TEST
		int[] nums = Farmer.readFile(args[0]);
		for (int num : nums) {
			System.out.println(num);
		}
		
	}
	
	

}
