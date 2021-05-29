package com.skilldistillery.jets.entity;

public class Pilot {
	private String name;
	private int yearsOfExperience;
	private double salary;
	private boolean working;
	
	public Pilot(String name, int yearsOfExperience, double salary) {
		this.name = name;
		this.yearsOfExperience = yearsOfExperience;
		this.salary = salary;
		this.setWorking(false);
	}
	
	public Pilot() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public boolean isWorking() {
		return working;
	}

	public void setWorking(boolean working) {
		this.working = working;
	}

	@Override
	public String toString() {
		return "Pilot [name=" + name + ", yearsOfExperience=" + yearsOfExperience + ", salary=" + salary + "]";
	}
	
	
}
