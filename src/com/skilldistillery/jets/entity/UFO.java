package com.skilldistillery.jets.entity;

public class UFO extends Jet implements CombatReady{

	public UFO(String model, double speed, int range, long price) {
		super(model, speed, range, price);}
	
	
	
	public void fight() {
		System.out.println();
		System.out.println(this.getModel() + " Combat Engaged");
		System.out.println("Preparing for World Domination!!!");
		System.out.println("Mwhahahahah");
		System.out.println();
	}



	@Override
	public String toString() {
		return "[UFO: " + super.toString()+"]";
	}



	
	
}
