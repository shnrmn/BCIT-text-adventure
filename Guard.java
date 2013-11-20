/**
 * An guard who is cold.
 * 
 * @author Shawn Norman
 * @version 2013.02.26
 */
public class Guard extends NPC
{
    /**
     * Constructor for objects of class Guard
     */
    public Guard(String description, double maxWeight, Character player)
    {
        super(description, maxWeight, player);
        setDialogue("You ask the guard about the Sand Ruby. He thinks for a moment, 'There is something very valuble in the treasure room.'" + "\n" + "'That darn spider queen stole the key.'");
    }

    public boolean wantsItem(Item item)
    {
        if(item.getName() == "warm tunic") {
            return true;
        }
        else {
            return false;
        }
    }
    
    public String takeItem(Item item)
    {
        items.addItem(item.getName(), item);
        setDialogue("'Fare well, traveller.'");
        return "The guard is quite pleased, 'I wish I could give you something in return.'" + "\n" + "'I can tell you that the old man in the hall collects bugs.'" + "\n" + "'Maybe you can bribe the spider with one of them? The old man likes rusty nails.'";
    }
}

