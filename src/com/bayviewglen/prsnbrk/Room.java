package com.bayviewglen.prsnbrk;

/*
 * Class Room - a room in an adventure game.
 *
 * Author:  Michael Kolling
 * Version: 1.1
 * Date:    August 2000
 * 
 * This class is part of Zork. Zork is a simple, text based adventure game.
 *
 * "Room" represents one location in the scenery of the game.  It is 
 * connected to at most four other rooms via exits.  The exits are labelled
 * north, east, south, west.  For each direction, the room stores a reference
 * to the neighbouring room, or null if there is no exit in that direction.
 */

import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

class Room {
	private String roomName;;
	private String description;
	// stores exits of this room.
	private HashMap<String, Room> exits;

	/**
	 * Create a room described "description". Initially, it has no exits.
	 * "description" is something like "a kitchen" or "an open court yard".
	 */
	public Room(String description) {
		this.description = description;
		exits = new HashMap<String, Room>();
	}

	/**
	 * Define the exits of this room. Every direction either leads to another
	 * room or is null (no exit there).
	 * 
	 * @throws Exception
	 */
	public void setExit(char direction, Room r) throws Exception {
		String dir = "";

		switch (direction) {
		case 'E':
			dir = "east";
			break;
		case 'W':
			dir = "west";
			break;
		case 'S':
			dir = "south";
			break;
		case 'N':
			dir = "north";
			break;
		case 'U':
			dir = "up";
			break;
		case 'D':
			dir = "down";
			break;
		default:
			throw new Exception("Invalid Direction");
		}
		exits.put(dir, r);
	}

	public void setExits(Room north, Room east, Room south, Room west) {
		if (north != null)
			exits.put("North", north);
		if (east != null)
			exits.put("East", east);
		if (south != null)
			exits.put("South", south);
		if (west != null)
			exits.put("West", west);
	}

	/**
	 * Return the description of the room (the one that was defined in the
	 * constructor).
	 */
	public String shortDescription() {
		return "Room: " + roomName + "\n\n" + description;
	}

	/**
	 * Return a long description of this room, on the form: You are in the
	 * kitchen. Exits: north west
	 */
	// i also changed the way to display the description...
	public String longDescription() {
		if (hasItems() == true) {
			return description.replaceAll("<br>", "\n") + "\n" + "The following items are avaliable: " + printItems()
					+ "\n" + exitString();
		} else {
			return description.replaceAll("<br>", "\n") + "\n" + exitString();
		}
	}

	/**
	 * Return a string describing the room's exits, for example
	 * "Exits: north west ".
	 */
	private String exitString() {
		String returnString = "Exits:";
		Set<String> keys = exits.keySet();
		for (Iterator<String> iter = keys.iterator(); iter.hasNext();)
			returnString += " " + iter.next();
		return returnString;
	}

	/**
	 * Return the room that is reached if we go from this room in direction
	 * "direction". If there is no room in that direction, return null.
	 */
	public Room nextRoom(String direction) {
		return (Room) exits.get(direction);
	}

	private ArrayList<Item> items;

	// construct the item you want in a room (you can add more if it is needed.
	// e.g, Item itemFour)
	public void setItems(Item itemOne, Item ItemTwo, Item itemThree) {
		items = new ArrayList<Item>();
		items.add(itemOne);
		items.add(ItemTwo);
		items.add(itemThree);
	}

	// remove an item from the room
	public void removeItem(Item item) {
		int currentIndex = items.indexOf(item);
		items.remove(currentIndex);
	}

	// print the item you have in a room
	public String printItems() {
		String itemsDescription = "";
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) != null) {
				itemsDescription += items.get(i).getDescription() + " ";
			}
		}
		return itemsDescription;
	}

	// getter for the arraylist
	public ArrayList<Item> getItems() {
		return items;
	}

	// check whether a room has item
	public boolean hasItems() {
		boolean hasItems = true;
		int count = 0;
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) == null) {
				count++;
			}
		}
		if (count == items.size()) {
			hasItems = false;
		}
		return hasItems;
	}

}