import java.util.HashMap;
import java.util.Scanner;

public class TextAdventure {
    private Room currentRoom;
    private Scanner inputScanner;

    public TextAdventure() {
        inputScanner = new Scanner(System.in);

        // create rooms
        Room start = new Room("You are in a dark room. There is a door to the north.");
        Room northRoom = new Room("You are in a room with a window to the north.");
        Room eastRoom = new Room("You are in a room with a bookshelf.");

        // set up room connections
        start.setExit("north", northRoom);
        northRoom.setExit("south", start);
        northRoom.setExit("east", eastRoom);
        eastRoom.setExit("west", northRoom);

        // set starting room
        currentRoom = start;
    }

    public void play() {
        while (true) {
            // print current room description
            System.out.println(currentRoom.getDescription());

            // get user input
            System.out.print("> ");
            String command = inputScanner.nextLine();

            // handle input
            if (command.equals("quit")) {
                System.out.println("Goodbye!");
                break;
            } else if (command.equals("look")) {
                System.out.println(currentRoom.getDescription());
            } else {
                String[] words = command.split(" ");
                String direction = words[0];
                Room nextRoom = currentRoom.getExit(direction);
                if (nextRoom == null) {
                    System.out.println("You can't go that way.");
                } else {
                    currentRoom = nextRoom;
                }
            }
        }
    }

    public static void main(String[] args) {
        TextAdventure game = new TextAdventure();
        game.play();
    }
}

class Room {
    private String description;
    private HashMap<String, Room> exits;

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public String getDescription() {
        return description;
    }
}
