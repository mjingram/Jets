package com.skilldistillery.jets.entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AirField {

	private List<Jet> jets;
	private List<Pilot> pilots;

	public AirField(String file) {
		this.jets = readFile(file);
		this.pilots = new ArrayList<>();
		Pilot pilot1 = new Pilot("Maverick", 6, 100000);
		Pilot pilot2 = new Pilot("Amelia Earhhart", 80, 120000);
		Pilot pilot3 = new Pilot("Charles Lindbergh", 34, 75000);
		Pilot pilot4 = new Pilot("Red Baron", 23, 50000);
		Pilot pilot5 = new Pilot("Goose", 4, 70000);
		Pilot pilot6 = new Pilot("Mal Reynolds", 10, 85000);
		
		pilots.add(pilot1);
		pilots.add(pilot2);
		pilots.add(pilot3);
		pilots.add(pilot4);
		pilots.add(pilot5);
		pilots.add(pilot6);
	}

	private static List<Jet> readFile(String fileName) {
		List<Jet> jets = new ArrayList<>();
		try {
			BufferedReader bufIn = new BufferedReader(new FileReader(fileName));

			String line;
			while ((line = bufIn.readLine()) != null) {
				String[] planeText = line.split(",");
				String model = planeText[0];
				double speed = Double.parseDouble(planeText[1]);
				int range = Integer.parseInt(planeText[2]);
				long price = Long.parseLong(planeText[3]);
				int type = Integer.parseInt(planeText[4]);
				if (type == 1) {
					Jet j = new FighterJet(model, speed, range, price);
					jets.add(j);
				} else if (type == 2) {
					Jet j = new CommercialJet(model, speed, range, price);
					jets.add(j);
				} else if (type == 3) {
					Jet j = new UFO(model, speed, range, price);
					jets.add(j);
				} else {
					System.out.println("Invalid Type!!!");
				}
				// System.out.println(p);
				
			} // while loop
			bufIn.close();
		} // try
		catch (IOException e) {
			System.err.println(e);
		} // catch
		return jets;
	}


	public List<Jet> getJets() {
		return jets;
	}

	public void setJets(List<Jet> jets) {
		this.jets = jets;
	}
	
	public List<Pilot> getPilots() {
		return pilots;
	}

	public void setPilots(List<Pilot> pilots) {
		this.pilots = pilots;
	}
	public Pilot getPilotAtIndex(int index) {
		int counter = 0;
		Pilot empty = null;
		if(index>pilots.size()) {
			System.out.println("Invalid Input");
			return empty;
		}
		for(Pilot p: pilots) {
			if(counter == index) {
				return p;
			}
			counter++;
		}
		return empty;
		
	}
	
	public Pilot getPilotByName(String name) {
		Pilot empty = null;
		
		for(Pilot p: pilots) {
			if(p.getName().equals(name)) {
				return p;
			}
		}
		return empty;
		
	}
	public int numberOfPilotsOnStaff() {
		int pilotCount  = this.pilots.size();
		return pilotCount;
	}
	public int numberOfPlanesInAirField() {
		int numofPlanes = this.jets.size();
		return numofPlanes;
	}
	
	public Jet fastestJet(List<Jet> jets) {
		double fastest = 0;
		Jet fastestplane = null;
		for(Jet j: jets) {
			if(j.getSpeed()>fastest) {
				fastestplane = j;
				fastest = j.getSpeed();
			}
		}
		
		
		return fastestplane;
	}
	
	public Jet farthestRangeJet(List<Jet> jets) {
		int farthest = 0;
		Jet farthestplane = null;
		for(Jet j: jets) {
			if(j.getRange()>farthest) {
				farthestplane = j;
				farthest = j.getRange();
			}
		}
		
		
		return farthestplane;
	}

	@Override
	public String toString() {
		return "AirField [jets=" + jets;
	}
	
	
}
