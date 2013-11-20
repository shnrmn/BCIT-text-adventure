
/**
 * A character that is not the player.
 * 
 * @author Shawn Norman
 * @version 2013.02.26
 */
public abstract class NPC extends Character
{
    protected String dialogue;
    protected Character player;

    /**
     * Constructor for objects of class NPC
     */
    public NPC(String description, double maxWeight, Character player)
    {
        super(description, maxWeight);
        this.player = player;
    }

    public String talk()
    {
        return dialogue;
    }
    
    /** 
     * Change the dialogue.
     * @param dialogue the new dialogue
     */
    public void setDialogue(String dialogue)
    {
        this.dialogue = dialogue;
    }
    
    public abstract boolean wantsItem(Item item);
    
}
