//File: P1.java
//Purpose:	Main class for part ONE of assignment 2 - Bridge crossing problem
//Programmer: Liam Craft - c3339847
//Date: 10/10/2020
//NOTE: This program takes a relative filepath to a text file as a command line argument.
//

//import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class P1 {

	public static void main(String[] args) {
		//NOTE: args as a relative file path for input
		if(args.length != 1) {
			System.out.println("Error: please check command line argument");
			return;
		}
		//north and south farmer lists
		ArrayList<Farmer> nFarmers = new ArrayList<Farmer>();
		ArrayList<Farmer> sFarmers = new ArrayList<Farmer>();
		
		//get the farmer values as an int array using the command line 
		//args and the farmer classes readFile utility method.
		int[] nums = Farmer.readFile(args[0]);
		
		//create bridge object
		Bridge b = new Bridge();
		
		//create farmer objects as individual threads
		//Create north farmers
		for(int i = 0; i < nums[0]; i++) {
			nFarmers.add(new Farmer("N_Farmer" + (i + 1), true, 0, b));
		}
		//Create south farmers
		for(int i = 0; i < nums[0]; i++) {
			sFarmers.add(new Farmer("S_Farmer" + (i + 1), false, 0, b));
			
		}
		//run the farmer threads
		for(int i = 0; i < nFarmers.size(); i++) {
			nFarmers.get(i).start();
		}
		for(int i = 0; i < sFarmers.size(); i++) {
			sFarmers.get(i).start();
		}
	
	}
	
	

}
