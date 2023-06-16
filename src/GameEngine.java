import java.util.Scanner;

public class GameEngine {
	private Adventurer adventurer;
	
	public void start() {
		// Create rooms
		Room lobby = new Room("Lobby", "You are in the lobby. There is a chair here.");
		Room waterRoom = new Room("Water Room", "You are in the water room.");
		Room livingRoom = new Room("Living Room", "You are in the living room.");
		
		// Create things
		Thing chair = new Thing("Chair", "A comfortable chair.");
		Door lobbyToWaterRoomDoor = new Door("Wooden Door", "A wooden door leading to the water room.", waterRoom);
		Door lobbyToLivingRoomDoor = new Door("Metal Door", "A metal door leading to the living room.", livingRoom);
		
		// Add things to rooms
		lobby.addThing(chair);
		lobby.addThing(lobbyToWaterRoomDoor);
		lobby.addThing(lobbyToLivingRoomDoor);
		
		// Connect rooms
		lobby.addLink(Direction.NORTH, waterRoom);
		lobby.addLink(Direction.EAST, livingRoom);
		waterRoom.addLink(Direction.SOUTH, lobby);
		livingRoom.addLink(Direction.WEST, lobby);
		
		// Create adventurer and start the game
		adventurer = new Adventurer("Player", lobby, 3);
		playGame();
	}
	
	public void playGame() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Text Adventure Game!");
		
		while (true) {
			System.out.println("\n" + adventurer.getName() + ", what would you like to do?");
			System.out.print("> ");
			String input = scanner.nextLine().trim().toLowerCase();
			
			if (input.startsWith("pickup ")) {
				String itemName = input.substring(7);
				adventurer.pickup(itemName);
			} else if (input.startsWith("drop ")) {
				String itemName = input.substring(5);
				adventurer.drop(itemName);
			} else if (input.equals("look")) {
				adventurer.look();
			} else if (input.startsWith("use ")) {
				String itemName = input.substring(4);
				adventurer.use(itemName);
			} else if (Direction.isValidDirection(input)) {
				adventurer.move(Direction.fromString(input));
			} else if (input.startsWith("open ")) {
				String directionStr = input.substring(5);
				Direction direction = Direction.fromString(directionStr);
				if (direction != null) {
					adventurer.open(direction);
				} else {
					System.out.println("Invalid direction. Please try again.");
				}
			} else if (input.equals("quit")) {
				System.out.println("Thank you for playing. Goodbye!");
				break;
			} else {
				System.out.println("Invalid command. Please try again.");
			}
		}
	}
	
	public static void main(String[] args) {
		GameEngine gameEngine = new GameEngine();
		gameEngine.start();
	}
}
