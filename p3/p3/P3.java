//File: P3.java
//Purpose:	Main class for part THREE of assignment 2 - Covid-safe restaurant with monitors
//Programmer: Liam Craft - c3339847
//Date: 13/10/2020

package p3;

import java.util.ArrayList;

//Main
public class P3 {

	public static void main(String[] args) {
		//command line args for file name
		if(args.length != 1) {
			System.out.println("Error: please check command line argument");
			return;
		}
		//create a Restaurant object
		Restaurant r = new Restaurant();
		//create list of customers using the Customer utility method, include the restaurant object
		ArrayList<Customer> cList = Customer.getCustList(args[0], r);
		//*******
		//This will run independently of customer threads, until all threads complete.
		while(!Customer.checkAllFinished(cList)) {
			//loop through and start customer threads on their arrival time
			for(int i = 0; i < cList.size(); i++) {
				Customer c = cList.get(i);
				if(c.getArrivalTime() == r.getGlobalTime()) {
					c.start();
				}
			}		
			try {
				//loop for time sync reasons
//				while(r.getTimeControl().availablePermits() != 100) {
//					System.out.print("");
//					Thread.sleep(1);
//				}
				Thread.sleep(2);			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//check if cleaning is required
			//if no clean required - inc time normally
			if(!r.needsClean() || r.getCustsToLeave() != 0) {
				r.incGlobalTime();
			}
			//Cleaning is required, add clean time to global time, reset flag and release permits.
			else {			
				//represents cleaning time
				r.incGlobalTime(5);
				//reset boolean flag
				r.setNeedsClean(false);
				//release all seat permits
				r.releaseSeats();
			}
		}
		
		//Print results
		System.out.println(Customer.getReport(cList));	
	}

}
