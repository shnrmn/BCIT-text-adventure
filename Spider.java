
/**
 * A giant spider queen.
 * 
 * @author Shawn Norman
 * @version 2013.02.26
 */
public class Spider extends NPC
{
    private Character player;

    /**
     * Constructor for objects of class Spider
     */
    public Spider(String description, double maxWeight, Character player)
    {
        super(description, maxWeight, player);
        setDialogue("The spider has nothing to say to you.");
        this.player = player;
    }

    public boolean wantsItem(Item item)
    {
        if(item.getName() == "exotic fly") {
            return true;
        }
        else {
            return false;
        }
    }
    
    public String takeItem(Item item)
    {
        items.addItem(item.getName(), item);
        player.getItems().put("key", new Item("key", 0.1, true));
        setDialogue("The spider seems sad.");
        return "The spider grabs the fly with one of her many legs. She is so satisfied for a moment that she flings the key to you." + "\n" + "You receive a key.";
    }
}
