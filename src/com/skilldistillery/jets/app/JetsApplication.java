package com.skilldistillery.jets.app;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.jets.entity.AirField;
import com.skilldistillery.jets.entity.Jet;
import com.skilldistillery.jets.entity.Pilot;
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
		int nameCounter = 0;
		for(Jet j: airField.getJets()) {
			j.setPilot(airField.getPilotAtIndex(nameCounter));
			airField.getPilotAtIndex(nameCounter).setWorking(true);
			nameCounter++;
		}
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
			System.out.println("2: Fly Jets");
			System.out.println("3: View Fastest Jet");
			System.out.println("4: View Jet With Longest Range");
			System.out.println("5: Load (or Unload) Commercial Planes with Passengers");
			System.out.println("6: Engage Combat!");
			System.out.println("7: Add a Jet to the Fleet");
			System.out.println("8: Remove a Jet to the Fleet");
			System.out.println("9: Pilots");
			System.out.println("10: Save Jets to File");
			System.out.println("0: Quit");
			System.out.println();

			System.out.println("Enter a menu option: ");
			selection = scanner.nextInt();

			if (selection == 1) {
				System.out.println(airField.toString());
				System.out.println("Total Planes in AirField: " + airField.numberOfPlanesInAirField());
			} else if (selection == 2) {
				flyJetMenu(input,airField);
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

			}else if (selection == 9) {
				pilotMenu(input, airField);

			}  else if (selection == 10) {
				System.out.println("Enter the file name to save to:");
				System.out.println("Do not add extension(.txt, .csv, etc)");
				scanner.nextLine();
				String outFile = scanner.nextLine();
				outFile = outFile.trim();
				outFile = outFile+".txt";
				System.out.println(outFile);
				System.out.println("Saving Jets to " + outFile +"...");
				writeFile(outFile, airField);
				
			}  else if (selection == 0) {
				System.out.println("Exiting...");
				break;

			}
			
			
			else {
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
				System.out.println("Exiting plane removal menu");
				break;
			}else {
				System.out.println("Invalid input, try again");
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
			}else {
				System.out.println("Invalid input, try again");
			}
		}
	}
	
	private void flyJetMenu(Scanner scanner, AirField airField) {
		int selection;

		while (true) {

			System.out.println();
			System.out.println("_____Fly Plane Menu_____");
			System.out.println("1: Fly Jet from Fleet by Name");
			System.out.println("2: Fly Jet from Fleet by Type");
			System.out.println("3: Fly All Jets");
			System.out.println("4: Show Current Fleet");
			System.out.println("5: Exit to Main Menu");

			System.out.println();

			System.out.println("Enter a menu option: ");
			selection = scanner.nextInt();

			if (selection == 1) {
				
				System.out.println("Enter the model: ");
				scanner.nextLine();
				String model = scanner.nextLine();
				
				
				if(airField.getJets().isEmpty() == true) {
					System.out.println("The Air Field is empty");
					break;
				}
				boolean modelFound = false;
				for (Jet j : airField.getJets()) {
					
					if (model.equals(j.getModel())) {
						
						j.fly();
						modelFound = true;
					}
					
					
				}
				
				
				if(modelFound == false) {
					System.out.println("Model Not Found");
					continue;
				}
				
				
				
			} else if (selection == 2) {
				System.out.println("Enter the Type of the plane to fly: ");
				scanner.nextLine();
				String type = scanner.nextLine();
				
				
				if(airField.getJets().isEmpty() == true) {
					System.out.println("The Air Field is empty");
					break;
				}
				
				boolean modelFound = false;
				for (Jet j : airField.getJets()) {
					
					if (type.equals((j.getClass().getSimpleName()))) {
						
						j.fly();
						modelFound = true;
					}
					
					
				}
				
				if(modelFound == false) {
					System.out.println("Model Not Found");
					continue;
				}

				

			}else if(selection == 3) {
				for (Jet j : airField.getJets()) {
					j.fly();
				}
				
			}
			else if (selection == 4) {
				System.out.println(airField.toString());
				System.out.println("Total Planes in AirField: " + airField.numberOfPlanesInAirField());

			} else if (selection == 5) {
				System.out.println("Exiting flight menu");
				break;
			}else {
				System.out.println("Invalid input, try again");
			}
		}
	}
	
	private void pilotMenu(Scanner scanner, AirField airField) {
		int selection;

		while (true) {

			System.out.println();
			System.out.println("_____Pilot Menu_____");
			System.out.println("1: Add Pilot on Staff");
			System.out.println("2: Fire Pilot on Staff");
			System.out.println("3: Add Pilot to Planes Crew");
			System.out.println("4: Fire Pilot from Planes Crew");
			System.out.println("5: View all Pilots on Staff");
			System.out.println("6: Exit Pilot Menu");
			System.out.println();

			System.out.println("Enter a menu option: ");
			selection = scanner.nextInt();

			if (selection == 1) {
				
				System.out.println("Enter the pilots name: ");
				scanner.nextLine();
				String name = scanner.nextLine();
				System.out.println("Enter pilots years of experience: ");
				int YoE = scanner.nextInt();
				scanner.nextLine();
				System.out.println("How much are you going to pay the pilot?: ");
				double salary = scanner.nextDouble();
				scanner.nextLine();
				
				Pilot addition = new Pilot(name, YoE, salary);
				List<Pilot> newList = airField.getPilots();
				newList.add(addition);
				airField.setPilots(newList);
				
				
				
			} else if (selection == 2) {
				System.out.println("Enter the pilots name to fire: ");
				scanner.nextLine();
				String name = scanner.nextLine();
				int counter = 0;
				int indexToRemove = -1000;
				
				if(airField.getPilots().isEmpty() == true) {
					System.out.println("No Pilots on Staff");
					break;
				}
				for (Pilot p : airField.getPilots()) {
					
					if (name.equalsIgnoreCase(p.getName())) {
						indexToRemove = counter;
					}
					
					counter++;
				}
				
				
				if(indexToRemove == -1000) {
					System.out.println("Pilot Not Found");
					continue;
				}
				for(Jet j:airField.getJets()) {
					if(j.getPilot().getName().equalsIgnoreCase(name)) {
						Pilot empty = new Pilot("Unmanned", 0, 0);
						j.setPilot(empty);
					}
				}
				
				List<Pilot> newList = airField.getPilots();
				newList.remove(indexToRemove);
				airField.setPilots(newList);
				
				


			}

			else if (selection == 3) {
				System.out.println("The currently unmanned planes are: ");
				List<Jet> unmanned = new ArrayList<>();
				for(Jet j: airField.getJets()) {
					if(j.getPilot().getName().equals("Unmanned")) {
						unmanned.add(j);
						System.out.println(j.toString());
					}
				}
				if(unmanned.isEmpty()) {
					System.out.println("There are no unmanned crafts at this time");
					continue;
				}
				System.out.println("The pilots unassigned to a jet are: ");
				List<Pilot> unassigned = new ArrayList<>();
				for(Pilot p: airField.getPilots()) {
					if(p.isWorking() == false) {
						unassigned.add(p);
						System.out.println(p.toString());
					}
				}
				if(unassigned.isEmpty()) {
					System.out.println("There are no unassigned pilots at this time");
					continue;
				}
				System.out.println("Which unassigned pilot do you want to crew?: ");
				scanner.nextLine();
				String name = scanner.nextLine();
				boolean alreadyWorking = true;
				for(Pilot p: unassigned) {
					if(p.getName().equalsIgnoreCase(name)) {
						alreadyWorking = false;
					}
				}
				if(alreadyWorking == true) {
					System.out.println("That pilot is already working!!!");
					continue;
				}
				System.out.println("Choose the name of the jet you would like to assign the pilot to: ");
				
				String model = scanner.nextLine();
				
				//Check if user inputed model is unmanned
				boolean alreadyPiloted = true;
				for(Jet j: unmanned) {
					if(j.getModel().equalsIgnoreCase(model)) {
						alreadyPiloted = false;
					}
				}
				if(alreadyPiloted == true) {
					System.out.println("That plane is already piloted!!!");
					continue;
				}
				//Sets the pilot to the plane
				for(Jet j: airField.getJets()) {
					if(j.getModel().equalsIgnoreCase(model)) {
						j.setPilot(airField.getPilotByName(name));
						airField.getPilotByName(name).setWorking(true);
					}
				}
			
				
			}
			else if( selection == 4) {
				System.out.println("Enter the Pilot you wish to remove from the crew: ");
				scanner.nextLine();
				String name = scanner.nextLine();
				boolean pilotFound = false;
				for(Jet j: airField.getJets()) {
					if(j.getPilot().getName().equalsIgnoreCase(name)) {
						Pilot empty = new Pilot("Unmanned", 0 ,0);
						
						j.setPilot(empty);
						pilotFound = true;
						
					}
				}
				if(pilotFound == false) {
					System.out.println("Pilot not found");
					continue;
				}
				for(Pilot p: airField.getPilots()) {
					if(p.getName().equalsIgnoreCase(name)) {
						p.setWorking(false);
					}
				}
				
			}
			else if (selection == 5) {
				System.out.println("Pilots On Staff: ");
				for(Pilot p: airField.getPilots()) {
					System.out.println("Name: " + p.getName() + ", YoE: " + p.getYearsOfExperience() + ", Salary: " + p.getSalary());
				}
				System.out.println();
				System.out.println("Total Pilots: " + airField.numberOfPilotsOnStaff());
				System.out.println("Total Planes: " + airField.numberOfPlanesInAirField());
				System.out.println();
				for(Jet j: airField.getJets()) {
					System.out.println("Model: " + j.getModel() + ", Pilot: " + j.getPilot().getName());
				}
				System.out.println();
				System.out.println("Unassigned Pilots: ");
				boolean allWorking = true;
				for(Pilot p: airField.getPilots()) {
					if(p.isWorking() == false) {
						System.out.println("Name: " + p.getName());
						allWorking = false;
					}
				}
				if(allWorking == true) {
					System.out.println("No unassigned pilots!");
				}
			}
			
			else if (selection == 6) {
				System.out.println("Exiting pilot menu");
				break;
			}else {
				System.out.println("Invalid input, try again");
			}
		}
	}
	
	private void writeFile(String outFileName, AirField airField) {
		
		if(outFileName.equals("jets.txt")) {
			System.err.println("Output file name entered is the same as the input filename");
			return;
		}

		try {
			FileWriter fw = new FileWriter(outFileName);
			PrintWriter pw = new PrintWriter(fw);

			StringBuilder sb = new StringBuilder();
			for(Jet j: airField.getJets()) {
			sb.append(j.getModel());
			sb.append(',');
			sb.append(j.getSpeed());
			sb.append(',');
			sb.append(j.getRange());
			sb.append(',');
			sb.append(j.getPrice());
			sb.append(',');
			if(j.getClass().getSimpleName().equals("CommercialJet")) {
				sb.append(1);
			}
			else if(j.getClass().getSimpleName().equals("FighterJet")) {
				sb.append(2);
			}else if(j.getClass().getSimpleName().equals("UFO")) {
				sb.append(3);
			}
			sb.append('\n');
			
			}

			pw.println(sb);

			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
