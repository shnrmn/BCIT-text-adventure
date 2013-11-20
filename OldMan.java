
/**
 * An old man who likes to drink.
 * 
 * @author Shawn Norman
 * @version 2013.02.26
 */
public class OldMan extends NPC
{
    private Character player;
    public static final int BOTH_ITEMS = 2;

    /**
     * Constructor for objects of class OldMan
     */
    public OldMan(String description, double maxWeight, Character player)
    {
        super(description, maxWeight, player);
        setDialogue("The old man just stares at you.");
        this.player = player;
    }

    public boolean wantsItem(Item item)
    {
        if((item.getName() == "bottle of scotch") || (item.getName() == "bottle of liqueur")) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public String takeItem(Item item)
    {
        items.addItem(item.getName(), item);
        if(getItems().size() == BOTH_ITEMS) { 
            player.getItems().put("exotic fly", new Item("exotic fly", 0.2, true));
            setDialogue("The old man is busy drinking.");
            return "The old man is slurs as he thanks you, 'Perfect! Now I can drink in peash! Take thish weird fly I found'" + "\n" + "You receive an exotic fly.";
        }
        else {
            return "The old man is happy, but not satisfied, 'That won't do on it'sh own!'";
        }
    }
   
}
