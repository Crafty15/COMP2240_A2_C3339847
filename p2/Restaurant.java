//File: Restaurant.java
//Purpose:	Restaurant class for part TWO of assignment 2 - Covid-safe restaurant
//Programmer: Liam Craft - c3339847
//Date: 13/10/2020

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Restaurant {
	private static Semaphore seat;
	private static final int MAXNUM = 5;
	//private static Customer cleaner = new Customer();
	//private int custTime;						//Flag that is set once the seats become completely full, blocking any other customers from entering until the seats are completely empty and a clean has been done.
	private static int globalTime = 0; 
	private static boolean needsClean = false;
	
	public Restaurant() {
		//semaphore with 5 permits - NOTE: use seat.availablePermits() OR .getQueueLength() to check if full
		seat = new Semaphore(MAXNUM, true);
		//this.custTime = 0;
	}
	
	public static void takeSeat(Customer c) {
		//boolean needsClean = false;
		
		//trigger something if all 5 seats become full. Only releases when all seats are empty and a 5 unit clean time has passed
		if(needsClean) {
//			TEST OUTPUT
			System.out.println("DRAINING PERMITS");
			seat.drainPermits();
//			try {
//				//If the cleaning flag is tripped, the threads will wait here....i think/hope
//				seat.drainPermits();
//				//c.wait();
//				//TEST OUTPUT
//				System.out.println(c.getName() + ": waiting");
//			} catch (Exception e) {
//				System.out.println("Interrupted Exception in Restaurant.takeSeat() - Waiting for permit");
//				e.printStackTrace();
//			}
		}
		//if there are spare seats, take one, else wait
		try {
			seat.acquire();
			//check number of permits (seats) and set needsClean if they are full.
			if(seat.availablePermits() == 0) {
				//TEST OUTPUT
				System.out.println("Cleaning needed");
				needsClean = true;
			}
			//TEST OUTPUT
			System.out.println(c.getName() + " TAKES a permit. Permits left = "+seat.availablePermits());
			//log start time
			c.setRunning(true);
			c.setSeatedTime(globalTime);	
			
			//do the things - count or whatever - remember to log global time			
			while(c.getEatTime() > 0) {
				//TEST OUTPUT
				System.out.println("Cust " + c.getName() + " is eating");
				c.decEatTime();
				globalTime++;
			}
			seat.release();
			//TEST OUTPUT
			System.out.println("Cust " + c.getName() + " RETURNS PERMIT. Now available = " +seat.availablePermits());
			//c.setLeaveTime(globalTime);
			c.setFinished(true);
				
			//Only reset needsClean if seats are completely empty - clean, then notifyAll???, to wake the waiting threads
			//check the flag
			
			//TEST OUTPUT
			//System.out.println("Available permits = "+seat.availablePermits());
			if(needsClean && seat.availablePermits() == MAXNUM) {
				//check if they are empty now that current customer has left
				
				//reset flag, release all permits, clean takes 5 time units
				needsClean = false;
				//NOTE: use notify all?
				//seat.release(MAXNUM);
				//c.notifyAll();
				
				//clean
				globalTime += 5;
				//TEST OUTPUT
				System.out.println("CLEANING COMPLETE");
			}
			
			
		} catch (InterruptedException e) {
			System.out.println("Interrupted Exception in Restaurant.takeSeat() - Acquiring permit");
			e.printStackTrace();
		}


	}
	
	//****Getters****
	public static int getGlobalTime() {
		return globalTime;
	}
	
	//****Setters****
	public static void setGlobalTime(int newGlobalTime) {
		globalTime = newGlobalTime;
	}
	public static void incGlobalTime() {
		globalTime++;
	}	
	public static void incGlobalTime(int incAmount) {
		globalTime += incAmount;
	}
}
