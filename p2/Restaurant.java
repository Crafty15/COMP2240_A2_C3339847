//File: Restaurant.java
//Purpose:	Restaurant class for part TWO of assignment 2 - Covid-safe restaurant
//Programmer: Liam Craft - c3339847
//Date: 13/10/2020

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Restaurant {
	private final Semaphore seat;
	private int custTime;						//Flag that is set once the seats become completely full, blocking any other customers from entering until the seats are completely empty and a clean has been done.
	private static int globalTime = 0; 
	private static boolean needsClean = false;
	
	public Restaurant() {
		//semaphore with 5 permits - NOTE: use seat.availablePermits() OR .getQueueLength() to check if full
		this.seat = new Semaphore(5, true);
		this.custTime = 0;
	}
	
	public void takeSeat(Customer c) {
		//boolean needsClean = false;
		
		//trigger something if all 5 seats become full. Only releases when all seats are empty and a 5 unit clean time has passed
		if(needsClean) {
			try {
				c.wait();
				System.out.println(c.getName() + ": waiting");
			} catch (InterruptedException e) {
				System.out.println("Interrupted Exception in Restaurant.takeSeat() - Waiting for permit");
				e.printStackTrace();
			}
		}
		//There are spare seats, take one
		try {
			seat.acquire();
			//TEST OUTPUT
			System.out.println("Available permits start = "+seat.availablePermits());
			//log start time
			c.setRunning(true);
			c.setSeatedTime(globalTime);
			//check number of permits (seats) and set needsClean if they are full.
			if(seat.availablePermits() == 0) {
				needsClean = true;
			}
			//do the things - count or whatever - remember to log global time
			while(custTime < c.getEatTime()) {
				custTime++;
				globalTime++;
			}
			seat.release();
			c.setLeaveTime(globalTime);
			c.setFinished(true);
			//Only reset needsClean if seats are completely empty - clean, then notifyAll(), to wake the waiting threads
			//check the flag
			//TEST OUTPUT
			System.out.println("Available permits end = "+seat.availablePermits());
			if(needsClean) {
				//check if they are empty now that current customer has left
				
				if(seat.availablePermits() == 5) {
					//reset flag, notifyAll(), clean takes 5 time units
					needsClean = false;
					seat.notifyAll();
					//clean
					globalTime += 5;
				}
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
