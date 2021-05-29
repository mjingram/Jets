package com.skilldistillery.jets.app;

import java.util.List;
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
			System.out.println("6: Engage Combat!");
			System.out.println("7: Add a Jet to the Fleet");
			System.out.println("8: Remove a Jet to the Fleet");
			System.out.println("9: Quit");
			System.out.println();

			System.out.println("Enter a menu option: ");
			selection = scanner.nextInt();

			if (selection == 1) {
				System.out.println(airField.toString());
				System.out.println("Total Planes in AirField: " + airField.numberOfPlanesInAirField());
			} else if (selection == 2) {
				for (Jet j : airField.getJets()) {
					j.fly();
				}
			} else if (selection == 3) {
				System.out.println(airField.fastestJet(airField.getJets()).toString());

			} else if (selection == 4) {
				System.out.println(airField.farthestRangeJet(airField.getJets()).toString());

			} else if (selection == 5) {
				for (Jet j : airField.getJets()) {
					if (j.getClass().getSimpleName().equals("CommercialJet")) {
						if (((CommercialJet) j).isPassengersOnBoard() == true) {
							((CommercialJet) j).unload();
						} else {
							((CommercialJet) j).loadPassengers();
						}
					}

				}

			} else if (selection == 6) {
				for (Jet j : airField.getJets()) {
					if (j.getClass().getSimpleName().equals("FighterJet")) {
						((FighterJet) j).fight();
					} else if (j.getClass().getSimpleName().equals("UFO")) {
						((UFO) j).fight();
					}
				}

			} else if (selection == 7) {
				addPlaneMenu(input, airField);

			} else if (selection == 8) {
				removeJetMenu(input, airField);

			} else if (selection == 9) {
				System.out.println("Exiting...");
				break;

			} else {
				System.out.println("Not an option. Try again.");
			}
		}
	}// Method End

	private void removeJetMenu(Scanner scanner, AirField airField) {
		int selection;

		while (true) {

			System.out.println();
			System.out.println("_____Remove Plane Menu_____");
			System.out.println("1: Remove Jet from Fleet by Name");
			System.out.println("2: Remove Jet from Fleet by Position");
			System.out.println("3: Show Current Fleet");
			System.out.println("4: Exit to Main Menu");

			System.out.println();

			System.out.println("Enter a menu option: ");
			selection = scanner.nextInt();

			if (selection == 1) {
				
				System.out.println("Enter the model: ");
				scanner.nextLine();
				String model = scanner.nextLine();
				int counter = 0;
				int indexToRemove = -1000;
				
				if(airField.getJets().isEmpty() == true) {
					System.out.println("The Air Field is empty");
					break;
				}
				for (Jet j : airField.getJets()) {
					
					if (model.equals(j.getModel())) {
						indexToRemove = counter;
					}
					
					counter++;
				}
				
				
				if(indexToRemove == -1000) {
					System.out.println("Model Not Found");
					continue;
				}
				
				List<Jet> newList = airField.getJets();
				newList.remove(indexToRemove);
				airField.setJets(newList);
			} else if (selection == 2) {
				System.out.println("Enter the position of the plane to remove: ");
				scanner.nextLine();
				int index = scanner.nextInt();
				scanner.nextLine();
				
				if(airField.getJets().isEmpty() == true) {
					System.out.println("The Air Field is empty");
					break;
				}

				List<Jet> newList = airField.getJets();
				newList.remove(index);
				airField.setJets(newList);

			}

			else if (selection == 3) {
				System.out.println(airField.toString());
				System.out.println("Total Planes in AirField: " + airField.numberOfPlanesInAirField());

			} else if (selection == 4) {
				System.out.println("Exiting plane addition menu");
				break;
			}
		}
	}

	private void addPlaneMenu(Scanner scanner, AirField airField) {
		int selection;

		while (true) {

			System.out.println();
			System.out.println("_____Add Plane Menu_____");
			System.out.println("1: Add Commericial Jet to Fleet");
			System.out.println("2: Add Fighter Jet to Fleet");
			System.out.println("3: Add UFO to Fleet");
			System.out.println("4: Show Current Fleet");
			System.out.println("5: Exit to Main Menu");
			System.out.println();

			System.out.println("Enter a menu option: ");
			selection = scanner.nextInt();

			if (selection == 1) {
				System.out.println("Enter the model: ");
				scanner.nextLine();
				String model = scanner.nextLine();
				System.out.println("Enter the speed of the plane: ");
				double speed = scanner.nextDouble();
				scanner.nextLine();
				System.out.println("Enter the range of the plane: ");
				int range = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Enter the price of the plane: ");
				long price = scanner.nextLong();
				scanner.nextLine();

				Jet addition = new CommercialJet(model, speed, range, price);
				List<Jet> newList = airField.getJets();
				newList.add(addition);
				airField.setJets(newList);

			} else if (selection == 2) {
				System.out.println("Enter the model: ");
				scanner.nextLine();
				String model = scanner.nextLine();
				System.out.println("Enter the speed of the plane: ");
				double speed = scanner.nextDouble();
				scanner.nextLine();
				System.out.println("Enter the range of the plane: ");
				int range = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Enter the price of the plane: ");
				long price = scanner.nextLong();
				scanner.nextLine();

				Jet addition = new FighterJet(model, speed, range, price);
				List<Jet> newList = airField.getJets();
				newList.add(addition);
				airField.setJets(newList);

			} else if (selection == 3) {
				System.out.println("Enter the model: ");
				scanner.nextLine();
				String model = scanner.nextLine();
				System.out.println("Enter the speed of the plane: ");
				double speed = scanner.nextDouble();
				scanner.nextLine();
				System.out.println("Enter the range of the plane: ");
				int range = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Enter the price of the plane: ");
				long price = scanner.nextLong();
				scanner.nextLine();

				Jet addition = new UFO(model, speed, range, price);
				List<Jet> newList = airField.getJets();
				newList.add(addition);
				airField.setJets(newList);

			} else if (selection == 4) {
				System.out.println(airField.toString());
				System.out.println("Total Planes in AirField: " + airField.numberOfPlanesInAirField());

			} else if (selection == 5) {
				System.out.println("Exiting plane addition menu");
				break;
			}
		}
	}
}
