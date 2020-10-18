//File: Farmer.java
//Purpose: Farmer object class for part 1 of assignment 2.
//Programmer: Liam Craft - c3339847
//Date: 10/10/2020

//import java.util.concurrent.Semaphore;
//import java.util.concurrent.TimeUnit;
import java.io.*;

public class Farmer implements Runnable {

	private boolean goingNorth = false;
	private String name = "";
	private int steps = 0;
	private Thread t;
	private Bridge bridge;
	
	//Default Constructor
	//Preconditions:
	//Postconditions:
	public Farmer() {
		// TODO Auto-generated constructor stub
	}
	
	//Constructor
	//Preconditions:
	//Postconditions:
	public Farmer(String newName, boolean newGoingNorth, int newSteps, Bridge newBridge) {
		this.name = newName;
		this.goingNorth = newGoingNorth;
		this.steps = newSteps;
		//this.bridgeSem = newBridgeSem;
		this.bridge = newBridge;
	}
	//*****Getters*****
	
	//getDirection() - 
	//Preconditions:
	//Postconditions:
	public String getDirection() {
		if(this.goingNorth) {
			return "North";
		}
		else {
			return "South";
		}
	}
	
	//getName() - 
	//Preconditions:
	//Postconditions:
	public String getName() {
		return this.name;
	}

	//getSteps() - 
	//Preconditions:
	//Postconditions:
	public int getSteps() {
		return this.steps;
	}
	
	//*****Setters*****
	
	//incStep() - 
	//Preconditions:
	//Postconditions:
	public void incStep() {
		this.steps++;
	}

	//swapDirection() - 
	//Preconditions:
	//Postconditions:
	public void swapDirection() {
		if(this.goingNorth) {
			this.goingNorth = false;
		}
		else {
			this.goingNorth = true;
		}
	}
	
	//*****Thread related*****
	

	//run() - 
	//Preconditions:
	//Postconditions:
	@Override
	public void run() {
		//Call to bridge , where the semaphore will be acquired and the farmers steps will be incremented
		//passes this farmer object to the bridge that was passed as a constructor arg.
		this.bridge.cross(this);
	}

	//start() - 
	//Preconditions:
	//Postconditions:
	public void start() {
		System.out.println("Starting thread: " + this.name);
		if(t == null) {
			t = new Thread(this, name);
			t.start();
		}
	}
	
	//*****Utility*****
	
	//readFile(): read text file that specifies the number of farmer objects to create
	//Preconditions: None
	//Postconditions: An integer array of size 2 containing the ints representing the number of north and south farmers to be created. [0] = N, [1] = S
	public static int[] readFile(String fileName) {
		int[] result = new int[2];
		String input = "";
		try {
			FileReader fRead = new FileReader(fileName);
			BufferedReader bRead = new BufferedReader(fRead);
			//NOTE: The input file for this program should only be a single line. This will simply ignore the rest, if any.
			input = bRead.readLine();
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
		//get the int values from each with regex - sort on the N & S just incase
		for(int i = 0; i < NS.length; i++) {
			if(NS[i].charAt(0) == 'N' || NS[i].charAt(0) == 'n') {
				result[0] = Integer.parseInt(NS[i].replaceAll("[^0-9]", ""));
			}
			else {
				result[1] = Integer.parseInt(NS[i].replaceAll("[^0-9]", ""));
			}
		}
			
		return result;	
	}

}
