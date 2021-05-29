package com.skilldistillery.jets.entity;

public class CommercialJet extends Jet implements Loading{
	private boolean passengersOnBoard = false;

	public CommercialJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}
	
	public void loadPassengers() {
		System.out.println();
		System.out.println(this.getModel() + " is now loading passengers");
		System.out.println("Welcome Aboard!!!");
		System.out.println();
		passengersOnBoard = true;
	}
	
	public void unload() {
		System.out.println();
		System.out.println(this.getModel() + " is now unloading passengers");
		System.out.println("Thanks for flying on " + this.getModel());
		System.out.println();
		passengersOnBoard = false;
	}
	

	public boolean isPassengersOnBoard() {
		return passengersOnBoard;
	}

	

	@Override
	public String toString() {
		return "[Commercial Jet: " + super.toString() + "]";
	}

	
	
}
