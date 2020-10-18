//File: Restaurant.java
//Purpose:	Restaurant class for part TWO of assignment 2 - Covid-safe restaurant
//Programmer: Liam Craft - c3339847
//Date: 13/10/2020

import java.util.concurrent.Semaphore;

public class Restaurant {
	private Semaphore seat;
	private Semaphore timeControl;
	private final int MAXNUM = 5;
	private int globalTime = 0; 
	private boolean needsClean;
	private int custsToLeave;


	//Constructor
	//Preconditions:
	//Postconditions:	
	public Restaurant() {
		//semaphore with 5 permits - NOTE: use seat.availablePermits() OR .getQueueLength() to check if full
		seat = new Semaphore(MAXNUM, true);
		timeControl = new Semaphore(100, true);
		needsClean = false;
		custsToLeave = -1;
	}

	//run() - 
	//Preconditions: A Restaurant object has been instantiated. 
	//Postconditions:	
	public void run(Customer c) {
		boolean pauseTime = false;
		//while a customer has not left
		while(c.getLeaveTime() == -1) {
			try {
				timeControl.acquire();
				pauseTime = true;
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//if customer is not running(seated)
			if(!c.isSeated()) {
				//try to get a seat
				try {
					if(seat.availablePermits() == 0) {
						timeControl.release();
						pauseTime = false;
					}
					//acquire seat permit
					seat.acquire();
					if(!pauseTime) {
						timeControl.acquire();
						pauseTime = true;
					}
					//check if seats are full - set boolean flag if needs a clean
					if(seat.availablePermits() == 0) {
						needsClean = true;
						custsToLeave = 5;
					}
					c.setSeated(true);
					c.setSeatedTime(globalTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(globalTime >= (c.getSeatedTime() + c.getEatTime())) {
				//release if no clean is required
				if(!needsClean) {
					seat.release();
				}				
				c.setLeaveTime(c.getSeatedTime() + c.getEatTime());
				c.setFinished(true);
				custsToLeave--;
				
			}
			timeControl.release();
			pauseTime = false;		
		}

	}
	
	//****Getters****

	//getGlobalTime() - 
	//Preconditions:
	//Postconditions:
	public int getGlobalTime() {
		return globalTime;
	}

	//needsClean() - 
	//Preconditions:
	//Postconditions:
	public boolean needsClean() {
		return this.needsClean;
	}

	//getTimeControl() - 
	//Preconditions:
	//Postconditions:
	public Semaphore getTimeControl() {
		return this.timeControl;
	}

	//getSeatSeam() - 
	//Preconditions:
	//Postconditions:
	public Semaphore getSeatSem() {
		return this.seat;
	}

	//getCustsToLeave() - 
	//Preconditions:
	//Postconditions:
	public int getCustsToLeave() {
		return this.custsToLeave;
	}

	//getMAXIMUM() - 
	//Preconditions:
	//Postconditions:
	public int getMAXNUM() {
		return MAXNUM;
	}
	
	//****Setters****

	//setGlobalTime(int) - 
	//Preconditions:
	//Postconditions:
	public void setGlobalTime(int newGlobalTime) {
		globalTime = newGlobalTime;
	}

	//incGlobalTime() - increment the global time by 1
	//Preconditions:
	//Postconditions:
	public void incGlobalTime() {
		globalTime++;
	}	

	//incGlobalTime(int) - increment the global time by the argument amount
	//Preconditions:
	//Postconditions:
	public void incGlobalTime(int incAmount) {
		globalTime += incAmount;
	}

	//setNeedsClean(boolean) - 
	//Preconditions:
	//Postconditions:
	public void setNeedsClean(boolean newNeedsClean) {
		this.needsClean = newNeedsClean;
	}

	//setCustsToLeave(int) - Used to set the 
	//Preconditions:
	//Postconditions:
	public void setCustsToLeave(int newCustsToLeave) {
		this.custsToLeave = newCustsToLeave;
	}

	//decCustsToLeave() - 
	//Preconditions:
	//Postconditions:
	public void decCustsToLeave() {
		custsToLeave--;
	}
}
