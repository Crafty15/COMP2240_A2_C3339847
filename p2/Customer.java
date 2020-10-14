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
	private Thread t;
	private boolean isFinished;
	private boolean isRunning;
//	private static int globalTime = 0;
	
	
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
		this.isFinished = false;
		this.isRunning = false;
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
		this.isFinished = false;
		this.isRunning = false;
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
		this.isFinished = false;
		this.isRunning = false;
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
	public boolean isFinished() {
		return this.isFinished;
	}
	public boolean isRunning() {
		return this.isRunning;
	}
//	public static int getGlobalTime() {
//		return globalTime;
//	}
	public static boolean checkAllFinished(ArrayList<Customer> cList) {
		boolean result = true;
		for(int i = 0; i < cList.size(); i++) {
			if(cList.get(i).isFinished == false) {
				result = false;
			}
		}
		return result;
	}
	
	public static boolean checkAllRunning(ArrayList<Customer> cList) {
		boolean result = true;
		for(int i = 0; i < cList.size(); i++) {
			if(cList.get(i).isRunning == false) {
				result = false;
			}
		}
		return result;
	}
	
	//Start list of customers as per their arrival time
	public static void startCustomerList(ArrayList<Customer> cList) {
		while(!checkAllRunning(cList)) {
			for(int i = 0; i < cList.size(); i++) {
				Customer c = cList.get(i);
				if(c.getArrivalTime() >= Restaurant.getGlobalTime()) {
					c.start();
				}
			}
		}
	}
	
	//****Setters****
	public void setLeaveTime(int newLeaveTime) {
		this.leaveTime = newLeaveTime;
	}
	public void setSeatedTime(int newSeatedTime) {
		this.seatedTime = newSeatedTime;
	}
	public void setFinished(boolean newIsFinished) {
		this.isFinished = newIsFinished;
	}
	public void setRunning(boolean newIsRunning) {
		this.isRunning = newIsRunning;
	}
//	public static void setGlobalTime(int newGlobalTime) {
//		globalTime = newGlobalTime;
//	}
//	public static void incGlobalTime() {
//		globalTime++;
//	}	
//	public static void incGlobalTime(int incAmount) {
//		globalTime += incAmount;
//	}
	
	
	//Preconditions:
	//Postconditions:
	@Override
	public void run() {
		//RUN TEST
		System.out.println("RUN TEST: " + this.name + " running.....");
//		while(this.arrivalTime < Restaurant.getGlobalTime()) {
//			//wait? How to do this better
//			System.out.println(this.name + " waiting.....");
//		}
		this.restaurant.takeSeat(this);

	}
	
	//Preconditions:
	//Postconditions:
	public void start() {
		
		if(t == null) {
			t = new Thread(this, name);
			System.out.println("Starting thread: " + t.getName());
			t.start();
		}
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

	//getCustList - read text file and return an arrayList of Customer objects, OVERLOAD to accept restaurant object
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
			//test parts
//			for(int j = 0; j < parts.length; j++) {
//				System.out.println("Part "+j+ " = " + parts[j]);
//			}
			result.add(new Customer(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]), r));
		}
		return result;
	}
	
	

	//getReport() - returns a formatted string of the entire run
	public static String getReport(ArrayList<Customer> cList) {
		//EXAMPLE FORMAT
//		System.out.format("%-11s%-12s%-8s%-6s%n", "Customer", "Arrives", "Seats", "Leaves");
//		for (int i = 0; i < customerList.size(); i++) {
//			Customer iter = customerList.get(i).getCustomer();
//			System.out.format("%-11s%-12d%-8d%-6d%n", iter.getId(), iter.getArrivalTime(), iter.getSeatedTime(), iter.getLeavingTime());
//		}
		String msg = "Customer  Arrives  Sits  Leaves\n";
		for(int i = 0; i < cList.size(); i++) {
			msg += cList.get(i).toString() + "\n";
		}
		return msg;
	}
	
	//toString - returns a string representation of a customer object (to match the spec requirements).
	public String toString() {
		return this.name + "\t  " + this.arrivalTime + "\t    " + this.seatedTime + "\t  " + this.leaveTime;
	}

	
}
