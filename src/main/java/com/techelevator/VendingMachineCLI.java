package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;


/*
 * Hello and welcome to Andrew and Camilla's Module 1 Capstone Project!
 * We'd like to give a big thank you to all of our awesome Kitty Coders:
 * Yoda, Butters, Kyo, Sophie, Castiel, and Luna <3
 * Please be kind when grading our project! Our Kitty Coders worked very
 * hard, so if you give us a bad grade they will be very sad. ₍^._.^₎𐒡
 * Thank you, and enjoy the Vending Machine 3000!
 */

/* [x] finalize displayInventory
*\ [] finalize Logger
*  [x] show list of products for (2) Select Product
*  [x] create error message for product not existing
*  [x] create error message for product sold out
*  [x] when item is chosen for purchase: print name, cost, money remaining, + item message (subtractCost)
*  [x] return to Main Menu after purchase is finished
*  [x] check BOGODO
*  [] unit testing
*  [] Sales Report?
 */
public class VendingMachineCLI {

	private Map<String, Item> inventoryMap = new HashMap<>();
	private Set<String> keys = inventoryMap.keySet();
	private double currentBalance = 0.00;
	private CustomDisplay display = new CustomDisplay();
	private LoggerSalesREport log = new LoggerSalesREport();


	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();

	}
	//logger start

	public void run() {

		setup();
		log.loggerWrite("NEW INSTANCE:",currentBalance,currentBalance);


		boolean stay = true;
		Scanner mainMenuScanner = new Scanner(System.in);

		while(stay) {
			System.out.println("*** Welcome to the Vending Machine 3000! ***");

			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("*** Main Menu ***");
			System.out.println("");
			System.out.println("(1) Display Vending Machine Items");
			System.out.println("(2) Make A Purchase");
			System.out.println("(3) Exit");
			String selection = mainMenuScanner.nextLine();

			if(selection.equals("1")) {
				System.out.println("DisplayInv");
				display.customDisplayBuilder(inventoryMap);
				System.out.println("");


			} else if(selection.equals("2")) {
				purchaseScreen();

			} else if(selection.equals("3")) {
				stay = false;

			} else if(selection.equals("4")) {
				//answer a security question
				//sales report

			} else {

			}
		}

		System.out.println("Goodbye! Don't forget to take your yummy snacks!");

	}


	public void purchaseScreen() {

		boolean stay = true;
		boolean canBuy = false;
		int itemsBought =0 ;
		while (stay){
			//replace with proper setting of decimal places
			String balance = String.valueOf(getBalance());
			String[] decimalSplit = balance.split("\\.");
			if(decimalSplit[1].length()==1){
				balance = String.join(".",decimalSplit)+"0";

			}
			System.out.println(String.format("Current Money Provided: $%s",balance));
			System.out.println("");
			display.customDisplayBuilder(inventoryMap);
			System.out.println("");
			System.out.println("");

			System.out.println("(1) Feed Money");
			System.out.println("(2) Select Product");
			System.out.println("(3) Finish Transaction");
			Scanner scanner = new Scanner(System.in);
			String userSelection = scanner.nextLine();

			if(userSelection.equals("1")){
				System.out.println("How much would you like to add? (1 or 5)");
				try{Double moneyIn = Double.parseDouble(scanner.nextLine());
				if (moneyIn ==1.00){
					feedMoneyIn(moneyIn);

				} else if (moneyIn ==5.00) {
					feedMoneyIn(moneyIn);

				}else{
					System.out.println("I'm sorry, we only accept 1 or 5 dollar bills.");
				}}catch (NumberFormatException e){
					System.out.println("Please use numbers");
				}
			} else if (userSelection.equals("2")) {
				System.out.println("Please enter your selection as LetterNumber (Ex: A1)");
				String userSelectedItem = scanner.nextLine().toUpperCase();
				try {
					Item values = inventoryMap.get(userSelectedItem);

					//approvePurchase (Does the user have enough money?)

					canBuy = approvePurchase(values.getPrice(), values.getQty(), itemsBought);
					if (canBuy) {
						System.out.println("Selection Approved!");
						itemsBought += 1;

						subtractPrice(values, itemsBought);

					} else System.out.println("I'm sorry, this item is invalid.");

				}catch (NullPointerException e) {
					System.out.println("Please enter a valid selection.");
				}

			} else if (userSelection.equals("3")) {
				System.out.println("Thank you for your purrchase! =^.^= ");
				System.out.println(makeChange());
				break;
			}
		}

	}

	public void setup() {

		File file = new File("main.csv");
		if(!file.exists() || !file.isFile()){
			System.out.println("Error: No inventory file available.");
			System.exit(1);
		}

		try {
			Scanner fileScanner = new Scanner(file);
			while(fileScanner.hasNextLine()){
				//read file and split by "," to get array
				String lineInText = fileScanner.nextLine();
				String[] lineTextArr = lineInText.split(",");

				if(lineTextArr[3].equals("Gum")) {
					// make a Gum object & put it into the map
					Item gum = new Gum(
							lineTextArr[0],
							lineTextArr[1],
							Double.parseDouble(lineTextArr[2]),
							lineTextArr[3],
							5
							);
					inventoryMap.put(lineTextArr[0], gum);

				}else if (lineTextArr[3].equals("Munchy")) {
					// make a Munchy object & put it into the map
					Item munchies = new Munchy(
							lineTextArr[0],
							lineTextArr[1],
							Double.parseDouble(lineTextArr[2]),
							lineTextArr[3],
							5
					);
					inventoryMap.put(lineTextArr[0], munchies);

				}else if (lineTextArr[3].equals("Drink")) {
					// make a Drink object & put it into the map
					Item drinks = new Drink(
							lineTextArr[0],
							lineTextArr[1],
							Double.parseDouble(lineTextArr[2]),
							lineTextArr[3],
							5
					);
					inventoryMap.put(lineTextArr[0], drinks);

				}else if (lineTextArr[3].equals("Candy")) {
					// make a Candy object & put it into the map
					Item candy = new Candy(
							lineTextArr[0],
							lineTextArr[1],
							Double.parseDouble(lineTextArr[2]),
							lineTextArr[3],
							5
					);
					inventoryMap.put(lineTextArr[0], candy);
				}
			}
		}catch (FileNotFoundException e){
			System.out.println("file not found");
		}
	}

	public double getBalance() {
		this.currentBalance = Math.round(this.currentBalance * 100.00) / 100.00;
		return currentBalance;
	}

	public void feedMoneyIn(double input) { //user adds money for purchasing
		if(input > 0) {
			this.currentBalance += input;
			log.loggerWrite("FEED MONEY:",input,currentBalance);
		}
	}

	public boolean approvePurchase(double price,int currentQty, int itemsBought) {
		//compares the price of the item being purchased to the amt of cash the user has fed in to make sure they can afford it
		if (currentQty<=0){
			System.out.println("Sorry, this item is SOLD OUT. Please make another selection.");
			return false;
		}

		if(itemsBought%2 ==0 && itemsBought !=0){
			price -=1;
		}

		if(price <= currentBalance) {
			return true;
		} else {
			System.out.println("Insufficient funds, please insert more bills.");
			return false;
		}
	}

	public double subtractPrice(Item value,int itemsBought) { //subtracts the item price from the currentBalance

		double price = value.getPrice();
		String type = value.getType();

		if(itemsBought%2 ==0 && itemsBought !=0){
			price -=1;


		}
		currentBalance -= price;
		//log purchase, amount, and balance
		String message = String.format("%s %s",value.getName(),value.getSlotId());
		log.loggerWrite(message,price,currentBalance);
		//adding qty removal
		int qty = value.getQty();
		qty--;
		value.setQty(qty);

		System.out.println(String.format("Your order: %s, $%s",value.getName(),Math.round(price*100.0)/100.0));
		System.out.println(value.getMessage()); //used polymorphism to avoid using multiple if statements
		return currentBalance;
	}

	public String makeChange() {
		int quarters = 0;
		int dimes = 0;
		int nickels = 0;

		this.currentBalance = Math.round(this.currentBalance * 100.0) / 100.0; //rounds currentBalance to two decimal places for accuracy:
		double printBalance = this.currentBalance;                             //multiples currentBalance by 100 and rounds to the nearest
		                                                                       //integer then divides by 100 to bring it back down to scale
		while(this.currentBalance >= .25) {
			quarters++;
			this.currentBalance -= .25;
			this.currentBalance = Math.round(this.currentBalance * 100.0) / 100.0;
		}
		while(this.currentBalance >= .10) {
			dimes++;
			this.currentBalance -= .10;
			this.currentBalance = Math.round(this.currentBalance * 100.0) / 100.0;
		}
		while(this.currentBalance >= .05) {
			nickels++;
			this.currentBalance -= .05;
			this.currentBalance = Math.round(this.currentBalance * 100.0) / 100.0;
		}
		log.loggerWrite("GIVE CHANGE:",printBalance,currentBalance);
		String message = String.format("Here's your change! Please take your $ %s in %s Quarters, %s Dimes, and %s Nickels.",printBalance,quarters,dimes,nickels);

		return  message;
	}

	//logger
	//print out final order
}
//Thanks for checking out our Module 1 Capstone Project! You've been working hard at grading
//all these projects, don't forget to take a cat nap when you need some rest! /ᐠ - ˕ -マ - ᶻ 𝗓 𐰁