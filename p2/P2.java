//File: P2.java
//Purpose:	Main class for part TWO of assignment 2 - Covid-safe restaurant
//Programmer: Liam Craft - c3339847
//Date: 13/10/2020

import java.util.ArrayList;

public class P2 {

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
		
		//TEST OUTPUT
//		System.out.println("args = " + args[0]);
		
		//start all customers
//		Customer.startCustomerList(cList);
//		Customer test = cList.get(0);
//		Customer test1 = cList.get(1);
//		Customer test2 = cList.get(2);
//		test.start();
//		test1.start();
//		test2.start();
		for(int i = 0; i < cList.size(); i++) {
			//System.out.println(cList.get(i).toString());
			cList.get(i).start();
		}
		while(!Customer.checkAllFinished(cList)) {
			//wait?
		}
		//NOTE: 14/10/2020 - Global time is not implementation is not correct, 
		//this dictates when other threads start, so needs to be re-designed.
		//
//		while(!test.isFinished() && !test1.isFinished()&& !test2.isFinished()) {
//			
//		}
		System.out.println(Customer.getReport(cList));
		
	}

}
