package com.bayviewglen.prsnbrk;

public class Item {

	private String description;
	 private int mass;
	 private boolean isFood;
	 private int healingPower;
	 
	 //create getters and setters
	 public String getDescription() {
	 return description;
	 }
	 public void setDescription(String description) {
	 this.description = description;
	 }
	 public int getMass() {
	 return mass;
	 }
	 public void setMass(int mass) {
	 this.mass = mass;
	 }
	 public Item(String description, int mass) {
	 super();
	 this.description = description;
	 this.mass = mass;
	 }

	 public Item(String description, int mass, boolean isFood, int healingPower) {
	 super();
	 this.description = description;
	 this.mass = mass;
	 this.isFood = isFood;
	 this.healingPower = healingPower;
	 }
}