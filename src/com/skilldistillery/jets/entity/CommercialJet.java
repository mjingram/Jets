package com.skilldistillery.jets.entity;

public class CommercialJet extends Jet{

	public CommercialJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}
	
	public void loadPassengers() {
		System.out.println();
		System.out.println(this.getModel() + " is now loading passengers");
		System.out.println("Welcome Aboard!!!");
		System.out.println();
	}

	@Override
	public String toString() {
		return "[Commercial Jet: " + super.toString() + "]";
	}

	
	
}
