package com.skilldistillery.jets.entity;

public abstract class Jet {
	private String model;
	private double speed;
	private int range;
	private long price;
	private Pilot pilot;
	
	public Jet(String model, double speed, int range, long price) {
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
		setPilot(new Pilot("Unmanned", 0 ,0));
	}
	
	public void fly() {
		
		System.out.println(model + " is now flying");
		System.out.println(model + " has a speed of " + speed + " and a range of " + range);
		double flightTime = (speed/range);
		System.out.println(model + " has a flight time of " + flightTime + " hours");
		System.out.println();
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

	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	@Override
	public String toString() {
		return "Model: " + getModel() + ", Speed: " + getSpeed()
				+ ", Range: " + getRange() + ", Price: " + getPrice() + ", Pilot: " + (getPilot().getName());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + (int) (price ^ (price >>> 32));
		result = prime * result + range;
		long temp;
		temp = Double.doubleToLongBits(speed);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Jet other = (Jet) obj;
		if (model == null) {
			if (other.model != null) {
				return false;
			}
		} else if (!model.equals(other.model)) {
			return false;
		}
		if (price != other.price) {
			return false;
		}
		if (range != other.range) {
			return false;
		}
		if (Double.doubleToLongBits(speed) != Double.doubleToLongBits(other.speed)) {
			return false;
		}
		return true;
	}

	
	
	
	
}
