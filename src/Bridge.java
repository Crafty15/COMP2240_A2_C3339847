//File: Bridge.java
//Purpose: Bridge object class for part 1 of assignment 2.
//Programmer: Liam Craft - c3339847
//Date: 10/10/2020

import java.util.concurrent.Semaphore;

public class Bridge {
	private static final int LENGTH = 20;
	private Semaphore cross;
	private int neon = 0;
	
	public Bridge() {
		//this.neon = 0;
	}
	//NOTE: have a "cross" method that each farmer object must access for the amount of time it takes to cross the bridge, step 1, add 200ms delay per 5 steps.
	public void cross() {
		
	}
	
	//getters
	
	//Preconditions:
	//Postconditions:
	public int getNeon() {
		return this.neon;
	}
	
	//setters
	
	//Preconditions:
	//Postconditions:
	public void setNeon(int newNeon) {
		this.neon = newNeon;
	}
	
	//Preconditions:
	//Postconditions:
	public void incNeon() {
		this.neon++;
	}
}
