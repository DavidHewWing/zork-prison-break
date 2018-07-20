package com.bayviewglen.prsnbrk;

public class Character {

	private String description;
	private String name;
	private int health=100;
	 
	 //create getters and setters
	 public String getDescription() {
	 return description;
	 }
	 public void setDescription(String description) {
	 this.description = description;
	 }
	 public String getName() {
	 return name;
	 }
	 public void setName(String name) {
	 this.name = name;
	 }
	 public Character(String description, String name) {
	 super();
	 this.description = description;
	 this.name = name;
	 }
	 
	 public int getHealth() {
		 return health;
	 }
	 
	 
	
}
