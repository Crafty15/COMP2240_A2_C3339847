//File: Customer.java
//Purpose:	Customer class for part TWO of assignment 2 - Covid-safe restaurant
//Programmer: Liam Craft - c3339847
//Date: 13/10/2020

import java.util.ArrayList;
import java.io.*;

public class Customer implements Runnable {

	//NOTE: Can probably get rid of the Restaurant passing arg???
	//****Class variables****
	private int arrivalTime;			//Time value the customer arrives at the restaurant, ready to take a seat
	private String name;				//Name of the customer object
	private int eatTime;				//Time the customer takes to eat
	private int leaveTime;				//Time the customer gives up their seat and leaves
	private int seatedTime;				//Time the customer takes a seat
	private Restaurant restaurant;		//An object representing the restaurant
	private Thread t;					//The customer thread object
	private boolean isFinished;			//A boolean representing if the customer is finished in the restaurant
	private boolean isSeated;			//A boolean representing if the customer is seated
	
	//****Constructors****
	
	//Default constructor
	//Preconditions: None
	//Postconditions: A customer object with default values has been created
	public Customer() {
		this.arrivalTime = 0;
		this.name = "";
		this.eatTime = 0;
		this.leaveTime = -1;
		this.seatedTime = 0;
		this.restaurant = new Restaurant();
		this.isFinished = false;
		this.isSeated = false;
	}
	
	//Constructor 1
	//Preconditions: None
	//Postconditions: A customer object with the parameter values has been created
	public Customer(int newArrivalTime, String newName, int newEatTime) {
		this.arrivalTime = newArrivalTime;
		this.name = newName;
		this.eatTime = newEatTime;
		this.leaveTime = -1;
		this.seatedTime = 0;
		this.restaurant = new Restaurant();
		this.isFinished = false;
		this.isSeated = false;
	}	
	
	//Constructor 2
	//Preconditions: The parameter Restaurant object has been instantiated
	//Postconditions: A customer object with the parameter values has been created
	public Customer(int newArrivalTime, String newName, int newEatTime, Restaurant newRestaurant) {
		this.arrivalTime = newArrivalTime;
		this.name = newName;
		this.eatTime = newEatTime;
		this.leaveTime = -1;
		this.seatedTime = 0;
		this.restaurant = newRestaurant;
		this.isFinished = false;
		this.isSeated = false;
	}
	
	//****Getters****

	//getArrivalTime() - Returns the 
	//Preconditions: A customer object has been instantiated
	//Postconditions: An int representing the arrival time has been returned
	public int getArrivalTime() {
		return this.arrivalTime;
	}
	
	//getSeatedTime() - The time that the customer is seated.
	//Preconditions: A customer object has been instantiated
	//Postconditions: An int representing the seating time has been returned
	public int getSeatedTime() {
		return this.seatedTime;
	}
	
	//getName - 
	//Preconditions: A customer object has been instantiated
	//Postconditions: A String representing the customer objects name is returned
	public String getName() {
		return this.name;
	}
	
	//getEatTime() - 
	//Preconditions: A customer object has been instantiated
	//Postconditions: An int representing the customers eating time has been returned
	public int getEatTime() {
		return this.eatTime;
	}
	
	//getLeaveTime() - 
	//Preconditions: A customer object has been instantiated
	//Postconditions: An int representing the customers leaving time has been returned
	public int getLeaveTime() {
		return this.leaveTime;
	}

	//isFinished() - 
	//Preconditions: A customer object has been instantiated
	//Postconditions: A boolean representing if the customer is finished eating or not is returned
	public boolean isFinished() {
		return this.isFinished;
	}

	//isSeated() - 
	//Preconditions: A customer object has been instantiated
	//Postconditions: A boolean representing if the customer is seated or not is returned
	public boolean isSeated() {
		return this.isSeated;
	}

	//getRestaurant() - 
	//Preconditions: A customer object has been instantiated
	//Postconditions: This customer's Restaurant object is returned
	public Restaurant getRestaurant() {
		return this.restaurant;
	}

	//checkAllFinished(ArrayList<Customer>) - 
	//Preconditions: None
	//Postconditions: A boolean representing whether of not ALL customers in the given list have finished eating is returned
	public static boolean checkAllFinished(ArrayList<Customer> cList) {
		boolean result = true;
		for(int i = 0; i < cList.size(); i++) {
			if(cList.get(i).isFinished == false) {
				result = false;
			}
		}
		return result;
	}

	//****Setters****

	//setLeaveTime(int) - 
	//Preconditions: A customer object has been instantiated
	//Postconditions: This Customers leave time == the parameter value
	public void setLeaveTime(int newLeaveTime) {
		this.leaveTime = newLeaveTime;
	}

	//setSeatedTime(int) - 
	//Preconditions: A customer object has been instantiated
	//Postconditions: This Customers seated time == the parameter value
	public void setSeatedTime(int newSeatedTime) {
		this.seatedTime = newSeatedTime;
	}

	//setFinished(boolean) - 
	//Preconditions: A customer object has been instantiated
	//Postconditions: This Customers isFinished boolean == the parameter value
	public void setFinished(boolean newIsFinished) {
		this.isFinished = newIsFinished;
	}

	//setSeated(boolean) - 
	//Preconditions: A customer object has been instantiated
	//Postconditions: This Customers isSeated boolean == the parameter value
	public void setSeated(boolean newIsRunning) {
		this.isSeated = newIsRunning;
	}

	//decEatTime() - 
	//Preconditions: A customer object has been instantiated
	//Postconditions: This Customers eatTime has been decremented by 1
	public void decEatTime() {
		this.eatTime--;
	}	
	
	//run() - 
	//Preconditions: A customer object has been instantiated
	//Postconditions: This Customer's thread has executed the specified code
	@Override
	public void run() {
		this.getRestaurant().run(this);
	}
	
	//start() - 
	//Preconditions: A customer object has been instantiated
	//Postconditions: This Customer's thread has been started
	public void start() {	
		if(t == null) {
			t = new Thread(this, name);
			//TEST OUTPUT
			//System.out.println("Starting thread: " + t.getName());
			t.start();
		}
	}
	
	//****Utility****
	
	//getCustList - read text file and return an arrayList of Customer objects
	//Preconditions: None
	//Postconditions: 
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
			result.add(new Customer(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2])));
		}
		return result;
	}

	//getCustList - read text file and return an arrayList of Customer objects, OVERLOAD to accept restaurant object
	//Preconditions: None
	//Postconditions:
	public static ArrayList<Customer> getCustList(String fileName, Restaurant r){
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
			result.add(new Customer(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]), r));
		}
		return result;
	}

	//getReport() - returns a formatted string of the entire restaurant/customer run
	//Preconditions: None
	//Postconditions:
	public static String getReport(ArrayList<Customer> cList) {
		String msg = "Customer  Arrives  Sits  Leaves\n";
		for(int i = 0; i < cList.size(); i++) {
			msg += cList.get(i).toString() + "\n";
		}
		return msg;
	}
	
	//toString - returns a string representation of a customer object (to match the spec requirements).
	//Preconditions: None
	//Postconditions:
	public String toString() {
		return this.name + "\t  " + this.arrivalTime + "\t    " + this.seatedTime + "\t  " + this.leaveTime;
	}

	
}
