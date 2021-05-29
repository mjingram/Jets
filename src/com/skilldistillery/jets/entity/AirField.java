package com.skilldistillery.jets.entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AirField {

	private List<Jet> jets;

	public AirField(String file) {
		this.jets = readFile(file);

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
