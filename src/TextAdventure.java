import javax.annotation.processing.SupportedSourceVersion;
import java.util.Scanner;

public class TextAdventure {
	public static void main(String[] args) {
		
		System.out.println("Ready to play the game?(y/n)");
		Scanner scan=new Scanner(System.in);
		System.out.println("Hahaha..,.. you are playing no matter what you say now....your fate was decided the second you ran this code");
		
		// Create things
		Thing chair = new Thing("Chair", "A small chair.");
		Thing Bottle = new Thing("Bottle", "Bottle");
		
		// Create rooms
		Room Lobby = new Room("Courtyard", "You are in a Lobby.");
		Room waterRoom = new Room("Hallway", "You find yourself in a dimly lit water room.");
		Room livingRoom = new Room("Kitchen", "You enter a bustling Living Room.");
		
		// Add links between rooms
		Lobby.addLink(Direction.NORTH, waterRoom);
		waterRoom.addLink(Direction.SOUTH, Lobby);
		waterRoom.addLink(Direction.EAST, livingRoom);
		livingRoom.addLink(Direction.WEST, waterRoom);
		
		
		// Add things to rooms
		Lobby.addThing(chair, Direction.NORTH);
		livingRoom.addThing(Bottle, Direction.WEST);
		
		// Create adventurer
		System.out.println("Hello player. What is your name: ");
		String name=scan.next();
		Adventurer player = new Adventurer(name, Lobby, 5);
		
		GameEngine game = new GameEngine();
		game.start();
	}
}