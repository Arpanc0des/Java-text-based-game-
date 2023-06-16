import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
	// Existing properties
	private String name;
	private String description;
	private Map<Direction, Room> linkedRooms;
	private List<Thing> things;
	
	// New property to associate things with directions
	private Map<Direction, List<Thing>> thingsByDirection;
	
	public Room(String name, String description) {
		this.name = name;
		this.description = description;
		linkedRooms = new HashMap<>();
		things = new ArrayList<>();
		thingsByDirection = new HashMap<>();
	}
	
	// Existing methods
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void addLink(Direction direction, Room room) {
		linkedRooms.put(direction, room);
	}
	
	public Room getLinkedRoom(Direction direction) {
		return linkedRooms.get(direction);
	}
	
	public void addThing(Thing thing) {
		things.add(thing);
	}
	
	public void removeThing(Thing thing) {
		things.remove(thing);
	}
	
	public List<Thing> getThings() {
		return things;
	}
	
	// New methods to associate things with directions
	
	public void addThing(Thing thing, Direction direction) {
		if (!thingsByDirection.containsKey(direction)) {
			thingsByDirection.put(direction, new ArrayList<>());
		}
		List<Thing> thingsInDirection = thingsByDirection.get(direction);
		thingsInDirection.add(thing);
	}
	
	public void removeThing(Thing thing, Direction direction) {
		if (thingsByDirection.containsKey(direction)) {
			List<Thing> thingsInDirection = thingsByDirection.get(direction);
			thingsInDirection.remove(thing);
		}
	}
	
	public List<Thing> getThings(Direction direction) {
		return thingsByDirection.getOrDefault(direction, new ArrayList<>());
	}
	
	public Thing getThing(String itemName) {
		for (Thing thing : things) {
			if (thing.getName().equalsIgnoreCase(itemName)) {
				return thing;
			}
		}
		return null;
	}
}
