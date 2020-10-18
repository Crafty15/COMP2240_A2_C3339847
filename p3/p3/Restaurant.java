//File: Customer.java
//Purpose:	Customer class for part TWO of assignment 2 - Covid-safe restaurant
//Programmer: Liam Craft - c3339847
//Date: 13/10/2020

package p3;

//import java.util.concurrent.Semaphore;
//import java.util.ArrayList;
import java.util.concurrent.atomic.*;

public class Restaurant {
	//private Semaphore seat;
	//private ArrayList<Seat> seats;
	private AtomicInteger availableSeats;
	//private int availableSeats;
	private AtomicInteger mainTimeControl;		//for making the main thread wait
	private AtomicInteger threadTimeControl;	//makes the child threads wait
	private AtomicInteger threadsInRun;
	private final int MAXSEATS = 5;
	private int globalTime = 0; 
	private boolean needsClean;
	private int custsToLeave;


	//Constructor
	//Preconditions:
	//Postconditions:	
	public Restaurant() {
		//this.numSeats = 5;
		this.availableSeats = new AtomicInteger(5);
		this.mainTimeControl = new AtomicInteger(0);
		this.threadTimeControl = new AtomicInteger(0);
		this.threadsInRun = new AtomicInteger(0);
		this.needsClean = false;
		this.custsToLeave = -1;
	}
	//run() - 
	//Preconditions: A Restaurant object has been instantiated. 
	//Postconditions:	
	public void run(Customer c) {
		while(c.getLeaveTime() == -1) {
			threadsInRun.incrementAndGet();
			if(!c.isSeated()) {
				//take seat
				takeSeat(c);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//if c is done
			if(globalTime >= (c.getSeatedTime() + c.getEatTime())) {
				//release if no clean is required
				if(!needsClean) {
					availableSeats.incrementAndGet();
				}				
				c.setLeaveTime(c.getSeatedTime() + c.getEatTime());
				c.setSeated(false);
				c.setFinished(true);
				custsToLeave--;				
			}
		}
	}
		
	//****Getters****
	
	//takeSeats() - 
	//Preconditions:
	//Postconditions:
	public synchronized void takeSeat(Customer c) {
		if(this.availableSeats.get() == 0) {
			synchronized(availableSeats) {
				try {
					availableSeats.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		availableSeats.decrementAndGet();
		c.setSeated(true);
		c.setSeatedTime(globalTime);
		//if last customer to take a seat
		if(availableSeats.get() == 0) {
			needsClean = true;
			custsToLeave = MAXSEATS;
		}
	}
	
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

	//getMainTimeControl() - gets the object itself
	//Preconditions:
	//Postconditions:
	public AtomicInteger getMainTimeControl() {
		return this.mainTimeControl;
	}
	//getThreadTimeControl() - gets the object itself
	//Preconditions:
	//Postconditions:
	public AtomicInteger getThreadTimeControl() {
		return this.threadTimeControl;
	}
	
	//timeControlVal - 
	//Preconditions:
	//Postconditions:
	public int timeControlVal() {
		return this.mainTimeControl.get();
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
	public int getMAXSEATS() {
		return MAXSEATS;
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
	
	//releaseSeats() - 
	//Preconditions:
	//Postconditions:
	public void releaseSeats() {
		availableSeats.set(MAXSEATS);
		synchronized(availableSeats) {
			//timeControl.notify();
			availableSeats.notify();
		}		
	}
}
