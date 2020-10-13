//File: Farmer.java
//Purpose: Farmer object class for part 1 of assignment 2.
//Programmer: Liam Craft - c3339847
//Date: 10/10/2020

//import java.util.concurrent.Semaphore;
//import java.util.concurrent.TimeUnit;
import java.io.*;

public class Farmer implements Runnable {
	//private static final int DIST_STEPS = 20; NOTE: Change to the length of the bridge
	private boolean goingNorth = false;
	
	//
	private String name = "";
	private int steps = 0;
//	private Semaphore bridgeSem;
	private Thread t;
	private Bridge bridge;
	
	//default constructor
	//Preconditions:
	//Postconditions:
	public Farmer() {
		// TODO Auto-generated constructor stub
	}
	
	//constructor
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
	
	public String getDirection() {
		if(this.goingNorth) {
			return "North";
		}
		else {
			return "South";
		}
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public int getSteps() {
		return this.steps;
	}
	
	//*****Setters*****
	public void incStep() {
		this.steps++;
	}
	
	public void swapDirection() {
		if(this.goingNorth) {
			this.goingNorth = false;
		}
		else {
			this.goingNorth = true;
		}
	}
	
	//*****Thread related*****
	
	@Override
	public void run() {
		//RUN TEST
		System.out.println("RUN TEST: " + this.name + " running.....");
		//Call to bridge , where the semaphore will be acquired and the farmers steps will be incremented
		//passes this farmer object to the bridge that was passed as a constructor arg.
		this.bridge.cross(this);
		//NOTE:Old attempt. Seems to hang on sem acquire.
		//Thread sleep (implement after 5 steps)
//		try {
//			System.out.println(this.name + "acquiring sem.....");
//			bridgeSem.acquire();
//			//TimeUnit.MILLISECONDS.sleep(2);
//			System.out.println(this.name + "releasing sem sem.....");
//			bridgeSem.release();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("RUN TEST: " + this.name + " finished.....");
	}
	
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
