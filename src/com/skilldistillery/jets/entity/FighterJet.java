package com.skilldistillery.jets.entity;

public class FighterJet extends Jet implements CombatReady{

	public FighterJet(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	public void fight() {
		System.out.println("Combat Engaged");
	}

	@Override
	public String toString() {
		return "[FighterJet: " + super.toString() + "]";
	}

	
}
