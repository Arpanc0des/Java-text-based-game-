import java.util.ArrayList;
import java.util.List;

public class Adventurer {
	private String name;
	private Room location;
	private List<Thing> inventory;
	private int score;
	private int maxInventorySize;
	
	public Adventurer(String name, Room startingLocation, int maxInventorySize) {
		this.name = name;
		this.location = startingLocation;
		this.inventory = new ArrayList<>();
		this.score = 0;
		this.maxInventorySize = maxInventorySize;
	}
	
	// Method to pick up an item from the current room and add it to the inventory
	public void pickup(String itemName) {
		// Check if the inventory is full
		if (inventory.size() >= maxInventorySize) {
			System.out.println("Your inventory is full. You can't carry any more items.");
		} else {
			Thing item = location.getThing(itemName);   // Get the item from the current room
			if (item != null) {
				inventory.add(item);   // Add the item to the inventory
				location.removeThing(item);   // Remove the item from the current room
				System.out.println("You picked up the " + item.getName());
				score++;   // Increase the score
			} else {
				System.out.println("You don't see " + itemName + " in the room.");
			}
		}
	}
	
	// Method to drop an item from the inventory and add it to the current room
	public void drop(String itemName) {
		Thing item = getInventoryItem(itemName);   // Get the item from the inventory
		if (item != null) {
			inventory.remove(item);   // Remove the item from the inventory
			location.addThing(item);   // Add the item to the current room
			System.out.println("You dropped the " + item.getName());
		} else {
			System.out.println("You don't have " + itemName + " in your inventory.");
		}
	}
	
	// Method to print the description of the current room and the items in it
	public void look() {
		System.out.println(location.getDescription());   // Print the room description
		List<Thing> thingsInRoom = location.getThings();   // Get the items in the current room
		if (!thingsInRoom.isEmpty()) {
			System.out.println("You see the following items in the room:");
			for (Thing item : thingsInRoom) {
				System.out.println("- " + item.getName());   // Print the name of each item
			}
		}
	}
	
	// Method to use an item from the inventory
	public void use(String itemName) {
		Thing item = getInventoryItem(itemName);   // Get the item from the inventory
		if (item != null) {
			item.use(this);   // Use the item
		} else {
			System.out.println("You don't have " + itemName + " in your inventory.");
		}
	}
	
	// Method to move to a new room in the specified direction
	public void move(Direction direction) {
		Room linkedRoom = location.getLinkedRoom(direction);   // Get the linked room in the specified direction
		if (linkedRoom != null) {
			location = linkedRoom;   // Move to the linked room
			System.out.println("You have moved to " + location.getName());
			score++;   // Increase the score
		} else {
			System.out.println("You can't move in that direction.");
		}
	}
	
	// Method to get the door in the specified direction
	private Door getDoor(Direction direction) {
		List<Thing> thingsInRoom = location.getThings();   // Get the items in the current room
		for (Thing thing : thingsInRoom) {
			if (thing instanceof Door) {   // Check if the item is a door
				Door door = (Door) thing;
				if (location.getLinkedRoom(direction) == door.getLinkedRoom()) {
					return door;   // Return the door in the specified direction
				}
			}
		}
		return null;   // Return null if no door is found in the specified direction
	}
	
	// Method to open a door in the specified direction
	public void open(Direction direction) {
		Room linkedRoom = location.getLinkedRoom(direction);   // Get the linked room in the specified direction
		if (linkedRoom != null) {
			Door door = getDoor(direction);   // Get the door in the specified direction
			if (door != null) {
				door.open();   // Open the door
				location = linkedRoom;   // Move to the linked room
				System.out.println("You have moved to " + location.getName());
				score++;   // Increase the score
			} else {
				System.out.println("There is no door in that direction.");
			}
		} else {
			System.out.println("You can't move in that direction.");
		}
	}
	
	// Method to get an item from the inventory by its name
	private Thing getInventoryItem(String itemName) {
		for (Thing item : inventory) {
			if (item.getName().equalsIgnoreCase(itemName)) {
				return item;   // Return the item with the specified name
			}
		}
		return null;   // Return null if no item is found with the specified name
	}
	
	public String getName() {
		return name;   // Return the name of the adventurer
	}
	
	public Room getLocation() {
		return location;   // Return the current location of the adventurer
	}
	
	public int getScore() {
		return score;   // Return the score of the adventurer
	}
}
