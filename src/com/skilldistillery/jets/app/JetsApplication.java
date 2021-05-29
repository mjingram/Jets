package com.skilldistillery.jets.app;

import java.util.Scanner;

import com.skilldistillery.jets.entity.AirField;
import com.skilldistillery.jets.entity.Jet;
import com.skilldistillery.jets.entity.CommercialJet;
import com.skilldistillery.jets.entity.UFO;
import com.skilldistillery.jets.entity.FighterJet;

public class JetsApplication {
	
	private Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		JetsApplication app = new JetsApplication();
		app.launch();
		app.input.close();
	}
	
	private void launch() {
		AirField airField = new AirField("jets.txt");
		System.out.println("***************");
		System.out.println("WELCOME TO JETS");
		System.out.println("***************");
		displayUserMenu(input, airField);
		
		
	}
	
	private void displayUserMenu(Scanner scanner, AirField airField) {
		
		int selection;

		while (true) {
			
			System.out.println();
			System.out.println("_____Menu_____");
			System.out.println("1: List Fleet");
			System.out.println("2: Fly All Jets");
			System.out.println("3: View Fastest Jet");
			System.out.println("4: View Jet With Longest Range");
			System.out.println("5: Load Commercial Planes with Passengers");
			System.out.println("6: Dog Fight!");
			System.out.println("7: Add a Jet to the Fleet");
			System.out.println("8: Remove a Jet to the Fleet");
			System.out.println("9: Quit");
			System.out.println();
			
			System.out.println("Enter a menu option: ");
			selection = scanner.nextInt();

			if (selection == 1) {
				System.out.println(airField.toString());
			} else if (selection == 2) {
				for(Jet j : airField.getJets()) {
					j.fly();
				}
			} else if (selection == 3) {
				System.out.println(airField.fastestJet(airField.getJets()).toString());

			} else if (selection == 4) {
				System.out.println(airField.farthestRangeJet(airField.getJets()).toString());

			} else if (selection == 5) {
				for(Jet j : airField.getJets()) {
					if(j.getClass().getSimpleName().equals("CommercialJet")) {
						
						((CommercialJet) j).loadPassengers();
					}
					
					
				}
				
			}
			else if (selection == 6) {
				//TODO
				
			}
			else if (selection == 7) {
				//TODO
				
			}
			else if (selection == 8) {
				//TODO
				
			}
			else if (selection == 9) {
				System.out.println("Exiting...");
				break;
				
			}
			else {
				System.out.println("Not an option. Try again.");
			}
		}
	}
}
