package com.skilldistillery.jets.entity;

public class UFO extends Jet{

	public UFO(String model, double speed, int range, long price) {
		super(model, speed, range, price);}
	
	
	
	public void fight() {
		System.out.println("Combat Engaged");
		System.out.println("Preparing for Wold Domination!!!");
		System.out.println("Mwhahahahah");
	}



	@Override
	public String toString() {
		return "[UFO: " + super.toString()+"]";
	}



	
	
}
