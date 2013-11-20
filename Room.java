import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes, modified by Shawn Norman
 * @version 2013.02.16
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Inventory items;
    private ArrayList<Character> characters;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new Inventory();
        characters = new ArrayList<Character>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param direction The direction of the exit.
     * @param neighbour The room in the given direction.
     */
    public void setExit(String direction, Room neighbour) 
    {
        exits.put(direction, neighbour);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Return a long description of the current room.
     * @return a description of the room and it's exits
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + "\n" + getRoomItems() + "\n" + getCharactersString();
    }
    
    /**
     * Get the exit in the chosen direction
     * @param direction the direction to check
     * @return the room in that direction
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /** 
     * Return a description of the room's exits
     * @return A description of available exits
     */
    public String getExitString()
    {
        String exitString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            exitString += " " + exit;
        }
        return exitString;
    } 
    
    /**
     * Add an item to the room
     * @param item the item to add
     */
    public void putInRoom(String name, Item item)
    {
        items.addItem(name, item);
    }
    
    /**
     * Remove an item from the room
     * @param name the name of the item
     */
    public void removeItem(String name)
    {
        items.removeItem(name);
    }
    
    /** 
     * @return an Item in the room
     * @param name the name of the Item
     */
    public Item getItem(String name)
    {
        return items.getItem(name);
    }
    
    /**
     * @return a check if the room has an item
     * @param name the item's name
     */
    public boolean hasItem(String name)
    {
        return items.hasItem(name);
    }
    
    /** @return a String describing the items in the room */
    public String getRoomItems()
    {
        if(!items.getItems().isEmpty()) {
            return ("This room contains: " + items);    
        }
        else {
            return ("There are no items here.");
        }
    }
    
    /**
     * Add a character to the room
     * @param character to add
     */
    public void addCharacter(Character character)
    {
        characters.add(character);
    }
    
    /** @return the items */
    public HashMap<String, Item> getItems()
    {
        return items.getItems();
    }
    
    /** @return the characters ArrayList */
    public ArrayList<Character> getCharacters()
    {
        return characters;
    }
    
    /** @return a String describing the characters in the room */
    public String getCharactersString()
    {
        if(characters.size() > 0) {
            String charactersString = "You see ";
            for(Character character: characters) {
                charactersString += character;
            }
            return charactersString;
        }
        else {
            return "You are alone here.";
        }
    }
}
