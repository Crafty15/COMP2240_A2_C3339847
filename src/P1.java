//File: P1.java
//Purpose:	Main class for part one of assignment 2 - Bridge crossing problem
//Programmer: Liam Craft - c3339847
//Date: 10/10/2020

import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class P1 {

	public static void main(String[] args) {
		//NOTE: args as a relative file path for input
		if(args.length != 1) {
			System.out.println("Error: please check command line argument");
			return;
		}
		ArrayList<Farmer> nFarmers = new ArrayList<Farmer>();
		ArrayList<Farmer> sFarmers = new ArrayList<Farmer>();
		
		//TEST
		int[] nums = Farmer.readFile(args[0]);
		for (int num : nums) {
			System.out.println(num);
		}
		//create bridge (semaphore)
//		Semaphore bridge = new Semaphore(0, false);
		//create bridge object
		Bridge b = new Bridge();
		
		//create farmer objects as individual threads
		//Create north farmers
		for(int i = 0; i < nums[0]; i++) {
			nFarmers.add(new Farmer("N_Farmer" + i + 1, true, 0, b));
		}
		//Create south farmers
		for(int i = 0; i < nums[0]; i++) {
			sFarmers.add(new Farmer("S_Farmer" + i + 1, false, 0, b));
			
		}
//		//run the farmers TEST....
		for(int i = 0; i < nFarmers.size(); i++) {
			nFarmers.get(i).start();
		}
		for(int i = 0; i < sFarmers.size(); i++) {
			sFarmers.get(i).start();
		}
	
	}
	
	

}
