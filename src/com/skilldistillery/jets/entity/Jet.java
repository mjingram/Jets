package com.skilldistillery.jets.entity;

public abstract class Jet {
	private String model;
	private double speed;
	private int range;
	private long price;
	
	public Jet(String model, double speed, int range, long price) {
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
	}
	
	public void fly() {
		System.out.println(model + " is now flying");
		System.out.println(model + " has a speed of " + speed + " and a range of " + range);
		double flightTime = (speed/range);
		System.out.println(model + " has a flight time of " + flightTime + " hours");
	}
	
	public double getSpeedInMach() {
		double machSpeed = speed/767;
		return machSpeed;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Model: " + getModel() + ", Speed: " + getSpeed()
				+ ", Range: " + getRange() + ", Price: " + getPrice();
	}

	
	
	
	
}
