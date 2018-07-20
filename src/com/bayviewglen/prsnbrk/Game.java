package com.bayviewglen.prsnbrk;

/**
 * Class Game - the main class of the "Zork" game.
 *
 * Author: Michael Kolling Version: 1.1 Date: March 2000
 * 
 * This class is the main class of the "Zork" application. Zork is a very
 * simple, text based adventure game. Users can walk around some scenery. That's
 * all. It should really be extended to make it more interesting!
 * 
 * To play this game, create an instance of this class and call the "play"
 * routine.
 * 
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates the commands that
 * the parser returns.
 */

class Game {

	private Room currentRoom;

	/**
	 * Create the game and initialise its internal map.
	 */
	public Game() {
		createRooms();
		parser = new Parser();
	}

	private Parser parser;

	/**
	 * Create all the rooms and link their exits together.
	 */
	private void createRooms() {
		Room outside, visitRoom, hallA, hallB, hallC, hallLocked, cellLockedA, cellLockedB, cell, vents, cafeteria,
				abandonedSectionA, abandonedSectionB, abandonedSectionC, abandonedSectionD, abandonedSectionE,
				abandonedSectionF, securityRoom, washroomCell, cellar, controlcentre;

		// create the rooms
		outside = new Room("You breathe fresh air and you are free! Freedom!");
		visitRoom = new Room("You are now in a Visiting room. It is cold and you see many people.");
		hallA = new Room("The main hall. The hustle and bustle of the main prison hall.");
		cellLockedA = new Room("You have entered a locked cell. It is dark and you probably need a light");
		hallB = new Room("The main hall. The hustle and bustle of the main prison hall.");
		cellLockedB = new Room("You have entered a locked cell. It is dark and you probably need a light");
		hallC = new Room("The main hall. The hustle and bustle of the main prison hall.");
		cell = new Room("Your cell. What rhymes with hell? Your cell!");
		washroomCell = new Room("Your washroom, you look up at a mirror and you see your ugly self");
		cafeteria = new Room("The cafeteria. Who doesn't like food?");
		hallLocked = new Room("You have just opened the locked hall. Prisoners are prohibited here be careful.");
		securityRoom = new Room("The security room. Control systems are beeping be careful.");
		vents = new Room("The vents. These vents sure are a great way to get places without being seen...");
		abandonedSectionA = new Room("This section of the prison is abandoned, It is likely to get caught");
		abandonedSectionB = new Room("This section of the prison is abandoned, It is likely to get caught");
		abandonedSectionC = new Room("This section of the prison is abandoned, It is likely to get caught");
		abandonedSectionD = new Room("This section of the prison is abandoned, It is likely to get caught");
		abandonedSectionE = new Room("This section of the prison is abandoned, It is likely to get caught");
		abandonedSectionF = new Room("This section of the prison is abandoned, It is likely to get caught");
		cellar = new Room("You have entered a hidden cellar");
		controlcentre = new Room("You have entered a control centre many shiny complicated buttons.");

		// Initialize room exits FORMAT IS N, E, S, W
		visitRoom.setExits(null, hallA, null, outside);
		hallA.setExits(cellLockedA, hallB, controlcentre, visitRoom);
		cellLockedA.setExits(vents, null, hallA, null);
		controlcentre.setExits(hallA, null, null, null);
		hallB.setExits(cellLockedB, hallC, abandonedSectionA, hallA);
		cellLockedB.setExits(vents, null, hallB, null);
		abandonedSectionA.setExits(hallB, abandonedSectionB, abandonedSectionD, null);
		hallC.setExits(cell, cafeteria, abandonedSectionB, hallB);
		cafeteria.setExits(null, hallLocked, null, null);
		hallLocked.setExits(null, null, securityRoom, null);
		securityRoom.setExits(hallLocked, null, null, null);
		cell.setExits(null, washroomCell, hallC, vents);
		washroomCell.setExits(null, null, null, cell);
		abandonedSectionB.setExits(hallC, abandonedSectionC, abandonedSectionE, abandonedSectionA);
		abandonedSectionC.setExits(null, null, abandonedSectionF, abandonedSectionB);
		abandonedSectionD.setExits(abandonedSectionA, abandonedSectionE, null, null);
		abandonedSectionE.setExits(abandonedSectionB, abandonedSectionF, null, abandonedSectionD);
		abandonedSectionF.setExits(abandonedSectionC, cellar, null, abandonedSectionE);
		cellar.setExits(null, null, null, abandonedSectionF);

		// set inventories for rooms

		// This is how you make items

		// Making the items done by David Hew-wing on Rodin's computer

		Item fish = new Item("fish", 5); // food that restores health
		Item cellKeyA = new Item("Key", 5); // key that works for abandoned cell

		// 2nd essential item (need for story to advance)
		Item walkieTalkie = new Item("Walkie Talkie", 20);
		// 1st essential item (need for story to advance)
		Item letter = new Item("Letter", 1);
		// 3rd essential item (need for story to advance)
		Item knife = new Item("Knife", 10);
		// useless item (throw off)
		Item fork = new Item("Fork", 3);
		// key for the button case in the control room
		Item buttonCaseKey = new Item("Button Case Key", 5);
		// useless item (has a quirky comment)
		Item clothing = new Item("New Set of Clothes", 10);
		// useless item
		Item shoelace = new Item("Shoelace", 3);
		// useless item
		Item string = new Item("String", 1);

		visitRoom.setItems(letter, null, null);
		hallA.setItems(shoelace, string, null);
		cellLockedA.setItems(clothing, null, null);
		controlcentre.setItems(null, null, null);
		hallB.setItems(null, null, null);
		cellLockedB.setItems(buttonCaseKey, clothing, null);
		abandonedSectionA.setItems(null, null, null);
		hallC.setItems(null, null, null);
		securityRoom.setItems(walkieTalkie, null, null);
		cell.setItems(null, null, null);
		washroomCell.setItems(null, null, null);
		abandonedSectionB.setItems(null, null, null);
		abandonedSectionC.setItems(null, null, null);
		abandonedSectionD.setItems(null, null, null);
		abandonedSectionE.setItems(null, null, null);
		cafeteria.setItems(fish, fork, knife);
		abandonedSectionF.setItems(null, null, null);
		cellar.setItems(null, null, cellKeyA);

		currentRoom = visitRoom; // start game outside
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();

		// Enter the main command loop. Here we repeatedly read commands and
		// execute them until the game is over.

		boolean finished = false;
		while (!finished) {
			Command command = parser.getCommand();
			finished = processCommand(command);
		}
		System.out.println("Thank you for playing.  Good bye.");
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		System.out.println();
		System.out.println("Welcome to Prison Break");
		System.out.println("The objective is to escape the prison.");
		System.out.println("Type 'help' if you need help.");
		System.out.println();
		System.out.println(currentRoom.longDescription());
	}

	/**
	 * Given a command, process (that is: execute) the command. If this command
	 * ends the game, true is returned, otherwise false is returned.
	 */
	private boolean processCommand(Command command) {
		if (command.isUnknown()) {
			System.out.println("I don't know what you mean...");
			return false;
		}

		String commandWord = command.getCommandWord();
		if (commandWord.equals("help"))
			printHelp();
		else if (commandWord.equals("go"))
			goRoom(command);
		else if (commandWord.equals("quit")) {
			if (command.hasSecondWord())
				System.out.println("Quit what?");
			else
				return true; // signal that we want to quit
		}
		return false;
	}

	// implementations of user commands:

	/**
	 * Print out some help information. Here we print some stupid, cryptic
	 * message and a list of the command words.
	 */
	private void printHelp() {
		System.out.println("You are lost. You are alone. You wander");
		System.out.println("around at Monash Uni, Peninsula Campus.");
		System.out.println();
		System.out.println("Your command words are:");
		parser.showCommands();
	}

	/**
	 * Try to go to one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 */
	private void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("Go where?");
			return;
		}

		String direction = command.getSecondWord();

		// Try to leave current room.
		Room nextRoom = currentRoom.nextRoom(direction);

		if (nextRoom == null)
			System.out.println("There is no door!");
		else {
			currentRoom = nextRoom;
			System.out.println(currentRoom.longDescription());
		}
	}

	// BREAK

	// BREAK

	// Here be inventory stuff

	Inventory bag = new Inventory();

	// code for drop items
	private void dropItems(Command command) {
		if (!command.hasSecondWord()) {
			System.out.println("What do you want to drop?");
			return;
		}
		String itemName = command.getSecondWord();
		for (int i = 0; i < bag.getNumItems(); i++) {
			Item currentItem = bag.getInventory().get(i);
			if ((currentItem != null) && (currentItem.getDescription().equals(itemName))) {
				bag.removeItem(currentItem);
				currentRoom.getItems().add(currentItem);
				i = currentRoom.getItems().size();
				System.out.println(currentItem.getDescription() + " dropped.");
			}
		}
	}

	// code for get item
	private void getItem(Command command) {
		if (!command.hasSecondWord()) {
			System.out.println("What do you want to get?");
			return;
		}
		boolean found = false;
		String itemName = command.getSecondWord();
		for (int i = 0; i < currentRoom.getItems().size(); i++) {
			Item currentItem = currentRoom.getItems().get(i);
			int capacity = bag.getCapacity();
			int weight = bag.getWeight();
			if ((currentItem != null) && (currentItem.getDescription().equals(itemName))
					&& (weight + currentItem.getMass()) < capacity) {
				bag.addItem(currentItem);
				currentRoom.getItems().remove(i);
				i = currentRoom.getItems().size();
				System.out.println(currentItem.getDescription() + " taken.");
				found = true;
				i--;
			}
			if ((currentItem != null) && (weight + currentItem.getMass()) >= capacity) {
				System.out.println(currentItem.getDescription() + " is too heavy to be carried.");
				i = currentRoom.getItems().size();
				found = true;
			}
		}

		if (found == false) {
			System.out.println("The items is not in the room, please try again!");
		}
	}
}