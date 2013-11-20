import java.util.HashMap;
import java.util.Stack;

/**
 * A character in the game.
 * 
 * @author Shawn Norman
 * @version 2013.02.19
 */
public class Character
{
    private String description;
    private double maxWeight;
    private double currentWeight;
    private Room currentRoom;
    protected Inventory items;
    private Stack<Room> route;
    public static final double STARTING_WEIGHT = 0.0;
    public static final int FIRST_CHARACTER = 0;

    /**
     * Constructor for objects of class Character
     * @param description the description
     * @param maxWeight the max weight they can carry in kilos
     * @param startingRoom the starting room
     */
    public Character(String description, double maxWeight)
    {
        this.description = description;
        this.maxWeight = maxWeight;
        currentWeight = STARTING_WEIGHT;
        route = new Stack<Room>();
        items = new Inventory();
    }

    /**
     * @param description the new description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    // Handling items.
    /**
     * Adds an item to the inventory.
     * @param item the item to add
     * @param name the name
     */
    public String takeItem(Item item)
    {
        if(item == null || !item.getCanPickUp()) {
            return "Can't take that item.";
        }
        else if(item.getName().equals("dummy")) {
            return "Take what?";
        }
        else if((currentWeight + item.getWeightKg()) <= maxWeight) { // check the carrying capacity
            currentRoom.removeItem(item.getName());
            items.addItem(item.getName(), item);
            currentWeight += item.getWeightKg();
            return "You take the " + item.getName() + ".";
        }
        else {
            return "Carrying too much weight for that!";
        }
    }
    
    /** 
     * Gives an item to another character. Each room currently has only one character. 
     * If more characters are added we will have to find a better way to search for characters. 
     * 
     * @param item to give
     */
    public String giveItem(Item item)
    {
        if(item !=null && item.getName().equals("dummy")) {
            return "Give what?";
        }
        else if(currentRoom.getCharacters().size() > 0) {
            Character character = currentRoom.getCharacters().get(FIRST_CHARACTER);
            if(item != null && character.wantsItem(item)) {
                items.removeItem(item.getName());
                currentWeight -= item.getWeightKg();
                return "You give away the " + item.getName() + "\n" + character.takeItem(item);
            }
            else if(item != null && !character.wantsItem(item)) {
                return "They don't seem to want that item.";
            }
            else {
                return "You don't have that item.";
            }
        }
        else {
            return "There is no one here.";
        }
    }
    
    /** 
     * Drops an item in the room.
     * @param item to drop
     */
    public String dropItem(Item item)
    {
        if(item !=null && item.getName().equals("dummy")) {
            return "Drop what?";
        } else if (item !=null) {
            currentRoom.putInRoom(item.getName(), item);
            items.removeItem(item.getName());
            currentWeight -= item.getWeightKg();
            return "You drop the " + item.getName();
        }
        else {
        	return "You don't have that item.";
        }
    }
    
    /** 
     * @return an Item in the inventory
     * @param name the name of the Item
     */
    public Item getItem(String name)
    {
        return items.getItem(name);
    }
    
    /** @return the inventory */
    public HashMap<String, Item> getItems()
    {
        return items.getItems();
    }
    
    /** @return a String describing the items in the inventory */
    public String getItemsString()
    {
        if(!items.getItems().isEmpty()) {
            return "You are carrying: " + items; 
        }
        else {
            return "You are carrying nothing.";
        }
    }
    
    /** 
     * @return a check if the character has an item
     * @param name the item's name
     */
    public boolean hasItem(String name)
    {
        return items.hasItem(name);
    }
    
    /** @return whether the character wants an item or not, implemented in subclasses. */
    public boolean wantsItem(Item item)
    {
        return true;
    }
    
    /** @return whether the character has a key or not */
    public boolean hasKey()
    {
        return getItems().containsKey("key");
    }
    
    // Handling the character's location
    /**
     * Sets the current room
     * @param currentRoom the room to set
     */
    public void setCurrentRoom(Room currentRoom)
    {
        route.push(currentRoom);
        this.currentRoom = currentRoom;
    }
    
    /** @return the current room */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    /** 
     * Takes the player back to the previous room, allowing the
     * player to retrace the route through the game 
     */
    public String goBack()
    {
        if(!route.empty()) {
            currentRoom = route.pop();
            return "You retrace your steps";
        }
        else {
            return "You are already back where you started.";
        }
    }
    
    /** @return a line of dialogue */
    public String talk()
    {
        if(currentRoom.getCharacters().size() <= 0) {
            return "You have no one to talk to.";
        }
        else {
            return currentRoom.getCharacters().get(FIRST_CHARACTER).talk();
        }
    }
    
    /** @return a String describing the character */
    public String toString()
    {
        return description;
    }
    
}
