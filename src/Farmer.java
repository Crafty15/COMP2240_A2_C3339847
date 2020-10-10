//File: Farmer.java
//Purpose: Farmer object class for part 1 of assignment 2.
//Programmer: Liam Craft - c3339847
//Date: 10/10/2020

import java.util.concurrent.Semaphore;
import java.io.*;

public class Farmer implements Runnable {
	//private static final int DIST_STEPS = 20; NOTE: Change to the length of the bridge
	private boolean goingNorth = false;
	
	//default constructor
	//Preconditions:
	//Postconditions:
	public Farmer() {
		// TODO Auto-generated constructor stub
	}
	
	//constructor
	//Preconditions:
	//Postconditions:
	public Farmer(boolean newGoingNorth) {
		// TODO Auto-generated constructor stub
		this.goingNorth = newGoingNorth;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	
	
	//readFile(): read text file that specifies the number of farmer objects to create
	//Preconditions: None
	//Postconditions: An integer array of size 2 containing the ints representing the number of north and south farmers to be created. [0] = N, [1] = S
	public static int[] readFile(String fileName) {
		int[] result = new int[2];
		String input = "";
		try {
			FileReader fRead = new FileReader(fileName);
			BufferedReader bRead = new BufferedReader(fRead);
			input = bRead.readLine();
			//System.out.println("file read test: " + newLine);
			bRead.close();
			fRead.close();
		}
		catch(IOException e) {
			System.out.println("IOException in Farmer.readFile: " + e.getMessage());
		}
		catch(Exception e) {
			System.out.println("General exception in Farmer.readFile: " + e.getMessage());
		}
		//split into north and south numbers on (,)
		String[] NS = input.split(", ");
		//System.out.println("file read split test: " + NS[0] + NS[1]);
		//get the int values from each with regex - sort on the N & S just incase
		for(int i = 0; i < NS.length; i++) {
			if(NS[i].charAt(0) == 'N' || NS[i].charAt(0) == 'n') {
				result[0] = Integer.parseInt(NS[i].replaceAll("[^0-9]", ""));
				//System.out.println("file read N int test: " + result[0]);
			}
			else {
				result[1] = Integer.parseInt(NS[i].replaceAll("[^0-9]", ""));
				//System.out.println("file read S int test: " + result[1]);
			}
		}
			
		return result;	
	}

}
