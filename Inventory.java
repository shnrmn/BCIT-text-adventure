import java.util.HashMap;

/**
 * A collection of items for rooms or characters. It stores the item and a string which is the item's 
 * description for ease of looking up an item.
 * 
 * @author Shawn Norman
 * @version 2013.02.20
 */
public class Inventory
{
    private HashMap<String, Item> items;

    /**
     * Constructor for objects of class Inventory
     */
    public Inventory()
    {
        items = new HashMap<String, Item>();
    }

    /**
     * @return items
     */
    public HashMap<String, Item> getItems()
    {
        return items;
    }
    
    /**
     * Add an item to the inventory
     * @param name the name of the item (it's description)
     * @param item the item to add
     */
    public void addItem(String name, Item item)
    {
        items.put(name, item);
    }
    
    /**
     * Remove an item from the inventory
     * @param name the name of the item to remove
     */
    public void removeItem(String name)
    {
        items.remove(name);
    }
    
    /**
     * Checks to see if an item exists in the inventory
     * @param name the name to look for
     * @return true or false
     */
    public boolean hasItem(String name)
    {
        return items.containsKey(name);
    }
    
    /** 
     * @return an item from the inventory 
     * @param name the name of the item
     */
    public Item getItem(String name)
    {
        if(items.containsKey(name)) {
            return items.get(name);
        }
        else {
            return null;
        }
    }
    
    /** @return a String listing the items in the Inventory */
    public String toString()
    {
        String itemsString = "";
        for(Item item : items.values()) { 
            itemsString += (item + ", ");
        }
        return itemsString.substring(0, itemsString.length()-2);
    }
}
