//File: Customer.java
//Purpose:	Customer class for part TWO of assignment 2 - Covid-safe restaurant
//Programmer: Liam Craft - c3339847
//Date: 13/10/2020

import java.util.ArrayList;
import java.io.*;

public class Customer implements Runnable {

	//****class variables****
	private int arrivalTime;
	private String name;
	private int eatTime;
	private int leaveTime;
	private int seatedTime;
	private Restaurant restaurant;
	
	
	//****Constructors****
	//Default constructor
	//Preconditions:
	//Postconditions:
	public Customer() {
		this.arrivalTime = 0;
		this.name = "";
		this.eatTime = 0;
		this.leaveTime = 0;
		this.seatedTime = 0;
		this.restaurant = new Restaurant();
	}
	
	//Constructor 1
	//Preconditions:
	//Postconditions:
	public Customer(int newArrivalTime, String newName, int newEatTime) {
		this.arrivalTime = newArrivalTime;
		this.name = newName;
		this.eatTime = newEatTime;
		this.leaveTime = 0;
		this.seatedTime = 0;
		this.restaurant = new Restaurant();
	}	
	
	//Constructor 2
	//Preconditions:
	//Postconditions:
	public Customer(int newArrivalTime, String newName, int newEatTime, Restaurant newRestaurant) {
		this.arrivalTime = newArrivalTime;
		this.name = newName;
		this.eatTime = newEatTime;
		this.leaveTime = 0;
		this.seatedTime = 0;
		this.restaurant = newRestaurant;
	}
	


	//****Getters****
	public int getArrivalTime() {
		return this.arrivalTime;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getEatTime() {
		return this.eatTime;
	}
	
	public int getLeaveTime() {
		return this.leaveTime;
	}
	
	//****Setters****
	public void setLeaveTime(int newLeaveTime) {
		this.leaveTime = newLeaveTime;
	}
	
	//Preconditions:
	//Postconditions:
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	
	//Preconditions:
	//Postconditions:
	public void start() {
		
	}
	
	//****Utility****
	//getCustList - read text file and return an arrayList of Customer objects
	public static ArrayList<Customer> getCustList(String fileName){
		ArrayList<Customer> result = new ArrayList<Customer>();
		ArrayList<String> inputLines = new ArrayList<String>();
		try {
			FileReader fRead = new FileReader(fileName);
			BufferedReader bRead = new BufferedReader(fRead);
			String newLine = bRead.readLine();
			//inputLines.add(newLine);
			while(!newLine.equals("END")) {
				inputLines.add(newLine);
				newLine = bRead.readLine();
			}
			bRead.close();
			fRead.close();
		}
		catch(IOException e) {
			System.out.println("IOException in Customer.getCustList: " + e.getMessage());
		}
		catch(Exception e) {
			System.out.println("General exception in Customer.getCustList: " + e.getMessage());
		}
		//sort into customer objects
		for(int i = 0; i < inputLines.size(); i++) {
			String[] parts = inputLines.get(i).split(" ");
			//test parts
//			for(int j = 0; j < parts.length; j++) {
//				System.out.println("Part "+j+ " = " + parts[j]);
//			}
			result.add(new Customer(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2])));
		}
		return result;
	}
	
	//toString - returns a string representation of a customer object (to match the spec requirements).
	public String toString() {
		return this.name + "\t" + this.arrivalTime + "\t" + this.seatedTime + "\t" + this.leaveTime;
	}

	
}
