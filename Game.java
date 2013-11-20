
/**
 *  A simple game where our hero is on a quest to find an item that will save his village. 
 *  This is the main class and implements all of the others - rooms, items and characters - 
 *  that the player can interact with in the game.
 * 
 * @author  Michael Kölling and David J. Barnes, modified by Shawn Norman
 * @version 2013.02.26
 */

public class Game 
{
    private Parser parser;
    private Character player;
    public static final double NPC_MAX_WEIGHT = 100.0;
    public static final double PLAYER_MAX_WEIGHT = 20.0;
        
    /**
     * Create the game and initialize its internal map.
     */
    public Game() 
    {
        player = new Character("You", PLAYER_MAX_WEIGHT); 
        createRooms();
        parser = new Parser();
        play();
    }
    
    public static void main(String[] args)
    {
    	new Game();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room courtyard, diningRoom, corridor, library, dungeon, tower, lockedRoom;
      
        // create the rooms
        courtyard = new Room("in a large courtyard");
        corridor = new Room("in a long, dimly lit corridor. Bats flutter about here and there, letting out shrill squeaks");
        diningRoom = new Room("in a grand dining room. There is a long table, set for dinner");
        library = new Room("in a dusty old library. The shelves are lined with hundreds of old books");
        dungeon = new Room("in a dungeon with huge spiders crawling along the walls and ceiling. You are likely to be eaten");
        tower = new Room("in a tower overlooking the castle and the lush lands around it");
        lockedRoom = new LockedRoom("in a room that seems much larger than you thought. The walls shimmer with unnatural light");
        
        // initialise room exits
        courtyard.setExit("north", corridor);
        courtyard.setExit("west", diningRoom);
        courtyard.setExit("up", tower);
        
        corridor.setExit("east", lockedRoom);
        corridor.setExit("south", courtyard);
        
        diningRoom.setExit("east", courtyard);
        diningRoom.setExit("up", library);
        diningRoom.setExit("down", dungeon);
        
        library.setExit("down", diningRoom);
        
        dungeon.setExit("up", diningRoom);
        
        tower.setExit("down", courtyard);
        
        lockedRoom.setExit("north", corridor);
        
        // add items to the rooms
        courtyard.putInRoom("spinning wheel", new Item("spinning wheel", 20.0, false));
        
        diningRoom.putInRoom("bottle of wine", new Item("bottle of wine", 1.0, true));
        diningRoom.putInRoom("bottle of beer", new Item("bottle of beer", 0.5, true));
        diningRoom.putInRoom("bottle of scotch", new Item("bottle of scotch", 1.0, true));
        diningRoom.putInRoom("bottle of liqueur", new Item("bottle of liqueur", 1.0, true));
        diningRoom.putInRoom("loaf of bread", new Item("loaf of bread", 0.5, true));
        
        library.putInRoom("huge dictionary", new Item("huge dictionary", 20.0, true));
        
        lockedRoom.putInRoom("sand ruby", new Item("sand ruby", 5.0, true));
        
        // add characters to the rooms
        courtyard.addCharacter(new OldWoman("an old woman at the spinning wheel, singing to herself.", NPC_MAX_WEIGHT, player));
        corridor.addCharacter(new OldMan("an old man sitting, quietly in a shaft of light.", NPC_MAX_WEIGHT, player));
        tower.addCharacter(new Guard("a guard, looking out over the battlement. He shivers in the cold.", NPC_MAX_WEIGHT, player));
        dungeon.addCharacter(new Spider("one spider, larger than all of the others, holding a key in its web.", NPC_MAX_WEIGHT, player));
        
        player.setCurrentRoom(courtyard);
    }

    /**
     *  Main play routine. Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            if(player.getItems().containsKey("sand ruby")) {
                win();
                finished = true;
            }
            else {
                Command command = parser.getCommand();
                finished = processCommand(command);
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Greetings adverturer!");
        System.out.println("You have little time and a very important task ahead of you.");
        System.out.println("A deadly fever has gripped the people of your village. The only cure is the Sand Ruby.");
        System.out.println("You have followed whispers and rumours to the castle before you. You open the giant doors and find yourself in a courtyard.");
        System.out.println("Good luck!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
            printLocationInfo();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("back")) {
            System.out.println(player.goBack());
            printLocationInfo();
        }
        else if (commandWord.equals("look")) {
            printLocationInfo();
        }
        else if (commandWord.equals("talk")) {
            System.out.println(player.talk());
        }
        else if (commandWord.equals("take")) {
            Item item = checkForItem(command, player.getCurrentRoom(), null); //makes sure the room has the item
            System.out.println(player.takeItem(item));
        }
        else if (commandWord.equals("give")) {
            Item item = checkForItem(command, null, player); //makes sure the player has the item
            System.out.println(player.giveItem(item));
        }
        else if (commandWord.equals("drop")) {
            Item item = checkForItem(command, null, player); //makes sure the player has the item
            System.out.println(player.dropItem(item));
        }
        else if (commandWord.equals("items")) {
            System.out.println(player.getItemsString());
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are worried about the fate of your village. You wander");
        System.out.println("around the castle.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /** Display the current location info */
    private void printLocationInfo()
    {
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println(player.getItemsString());
    }
    
    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else if (nextRoom instanceof LockedRoom) {
            if(player.hasKey()) {
                System.out.println("You use the key to open the door.");
                player.setCurrentRoom(nextRoom);
            }
            else {
                System.out.println("The door is locked");
            }
        }
        else {
            player.setCurrentRoom(nextRoom);
        }
    }
    
    /** Checks for an item in an inventory */
    private Item checkForItem(Command command, Room room, Character character)
    {
        if(command.hasSecondWord()) {
            String name = command.getSecondWord();
            if(room == null && character.hasItem(name)) {
                return character.getItem(name);
            }
            else if(character == null && room.hasItem(name)) {
                return room.getItem(name);
            }
            else {
                return null;
            }
        }
        else {
            return new Item("dummy", 0.0, true); // a dummy item if no second word is given
        }
    }
    
    /** Wins the game. */
    private void win()
    {
        System.out.println("Congratulations! You have recovered the Sand Ruby and saved your village!");
    }
}
