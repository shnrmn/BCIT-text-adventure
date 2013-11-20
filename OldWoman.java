
/**
 * An old woman who likes to sing.
 * 
 * @author Shawn Norman
 * @version 2013.02.26
 */
public class OldWoman extends NPC
{
    private Character player;

    /**
     * Constructor for objects of class OldWoman
     */
    public OldWoman(String description, double maxWeight, Character player)
    {
        super(description, maxWeight, player);
        setDialogue("You attempt to get the old woman's attention, 'Excuse me, my lady, I come seeking a jewel known as the Sand Ruby. Have you heard of it?'" + "\n" + "She stops singing and looks up at you, 'I ain't heard of no Sand Ruby, I'm trying to finish a new song, but I can't find the right words.'");
        this.player = player;
    }

    public boolean wantsItem(Item item)
    {
        if(item.getName() == "huge dictionary") {
            return true;
        }
        else {
            return false;
        }
    }
    
    public String takeItem(Item item)
    {
        items.addItem(item.getName(), item);
        player.getItems().put("warm tunic", new Item("warm tunic", 5.0, true));
        setDialogue("The old woman looks up, 'Almost finished!'");
        return "The old woman's eyes light up at the sight of the dictionary, 'Thank you so much! This will surely help me with my song.'" + "\n" + "'I don't have much to offer, but take this warm tunic I've been sewing. Good luck on your quest!'" + "\n" + "You receive a warm tunic.";
    }
}
