public class Door extends Thing {
	private final Room linkedRoom;
	
	public Door(String name, String description, Room linkedRoom) {
		super(name, description);
		this.linkedRoom = linkedRoom;
	}
	
	public Room getLinkedRoom() {
		return linkedRoom;
	}
	
	public void open() {
		System.out.println("You opened the door to " + linkedRoom.getName() + ".");
	}
}
