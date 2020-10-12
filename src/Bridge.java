//File: Bridge.java
//Purpose: Bridge object class for part 1 of assignment 2.
//Programmer: Liam Craft - c3339847
//Date: 10/10/2020

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Bridge {
	private static final int LENGTH = 20;
	private Semaphore bridgePermit;
	private int neon = 0;
	
	public Bridge() {
		//this.neon = 0;
		this.bridgePermit = new Semaphore(1, true);
	}
	//NOTE: have a "cross" method that each farmer object must access for the amount of time it takes to cross the bridge, step 1, add 200ms delay per 5 steps.
	public void cross(Farmer f) {
		System.out.println(f.getName() + ": Waiting for bridge. Going " + f.getDirection());
		try {
			bridgePermit.acquire();
			for(int i = 0; i <= LENGTH; i++) {
				f.incStep();
				if(i == 20) {
					System.out.println(f.getName() + ": Across the bridge.");
				}
				else if(i % 5 == 0) {
					System.out.println(f.getName() + ": Crossing bridge Step " + i);
					//NOTE: change the sleep setting before submission
					TimeUnit.MILLISECONDS.sleep(2000);
				}
			}
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		this.neon++;
		System.out.println("NEON = " + neon);
		//release sem, swap farmer direction
		bridgePermit.release();
		f.swapDirection();
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
