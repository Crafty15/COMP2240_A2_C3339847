//File: P2.java
//Purpose:	Main class for part TWO of assignment 2 - Covid-safe restaurant
//Programmer: Liam Craft - c3339847
//Date: 13/10/2020

import java.util.ArrayList;

public class P2 {
	private static int globalTime = 0;
	public static void main(String[] args) {
		//args for file name
		if(args.length != 1) {
			System.out.println("Error: please check command line argument");
			return;
		}
		//create a Restaurant object
		Restaurant r = new Restaurant();
		//create list of customers, include the restaurant object
		ArrayList<Customer> cList = Customer.getCustList(args[0], r);

		//NOTE: try to implement the counter here. 
		//This will run independently, until all threads complete
		while(!Customer.checkAllFinished(cList)) {
			//loop through and start customer threads on their arrival time
			for(int i = 0; i < cList.size(); i++) {
				Customer c = cList.get(i);
				if(c.getArrivalTime() == r.getGlobalTime()) {
					c.start();
				}
			}
			
			try {
				//trash loop for sync
				while(r.getTimeControl().availablePermits() != 100) {
					System.out.print("");
					Thread.sleep(1);
				}
				Thread.sleep(1);			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//check if cleaning is required
			//if no clean required - inc time normally
			if(!r.needsClean() || r.getCustsToLeave() != 0) {
				r.incGlobalTime();
			}
			else {			
				//represents cleaning time
				r.incGlobalTime(5);
				//release seats
				r.setNeedsClean(false);
				r.getSeatSem().release(5);
				//System.out.println("all cust done = " + );
			}
		}

		System.out.println(Customer.getReport(cList));
		
	}
	public static int getGlobalTime() {
		return globalTime;
	}

}
