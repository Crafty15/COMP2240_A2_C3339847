//File: Restaurant.java
//Purpose:	Restaurant class for part TWO of assignment 2 - Covid-safe restaurant
//Programmer: Liam Craft - c3339847
//Date: 13/10/2020

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Restaurant {
	private Semaphore seat;
	private int custSeatedTime;
	private static int elapsedTime = 0;
	private static boolean needsClean = false;
	
	public Restaurant() {
		//semaphore with 5 permits - NOTE: use seat.availablePermits() OR .getQueueLength() to check if full
		this.seat = new Semaphore(5, true);
		this.custSeatedTime = 0;
	}
	
	public void takeSeat(Customer c) {
		//boolean needsClean = false;
		//trigger something if all 5 seats become full. Only releases when all seats are empty and a 5 unit clean time has passed
		if(needsClean) {
			try {
				c.wait();
			} catch (InterruptedException e) {
				System.out.println("Interrupted Exception in Restaurant.takeSeat() - Waiting for permit");
				e.printStackTrace();
			}
		}
		//
		try {
			seat.acquire();
			//check number of permits and set needsClean if 0
			if(seat.availablePermits() == 0) {
				needsClean = true;
			}
			//do the things
//				while() {
//					
//				}
			seat.release();
			//Check if seats are empty - clean, then notifyAll()
			
			
		} catch (InterruptedException e) {
			System.out.println("Interrupted Exception in Restaurant.takeSeat() - Acquiring permit");
			e.printStackTrace();
		}


	}
}
