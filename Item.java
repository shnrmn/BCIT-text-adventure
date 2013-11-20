
/**
 * An item that canbe added to an inventory.
 * 
 * @author Shawn Norman
 * @version 2013.02.26
 */
public class Item
{
    protected String name;
    private double weightKg;
    private boolean canPickUp;

    /**
     * Constructor for objects of class Item
     * @param name the item's description
     * @param weightKg the item's weight in kilos
     * @param canPickUp can the item be picked up or not
     */
    public Item(String name, double weightKg, boolean canPickUp)
    {
        this.name = name;
        setWeightKg(weightKg);
        this.canPickUp = canPickUp;
    }

    /**
     * Validates the value for weight and sets the weight of the item
     * @param weightKg the weight in kilos
     */
    public void setWeightKg(double weightKg)
    {
        if(weightKg > 0.0) {
            this.weightKg = weightKg;
        }
        else {
            weightKg = 0.0;
        }
    }
    
    /** @return the weight in kilos */
    public double getWeightKg()
    {
        return weightKg;
    }
    
    /** @return the name */
    public String getName()
    {
        return name;
    }
    
    /** @return canPickUp boolean */
    public boolean getCanPickUp()
    {
        return canPickUp;
    }
    
    /** @return a String desribing the item */
    public String toString()
    {
        return name;
    }
    
}
