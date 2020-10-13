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
		
		//TEST OUTPUT
		//System.out.println("args = " + args[0]);
		ArrayList<Customer> cList = Customer.getCustList(args[0]);
		for(int i = 0; i < cList.size(); i++) {
			System.out.println(cList.get(i).toString());
		}
	}

}
