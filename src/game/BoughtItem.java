package game;

/**
 * BoughtItem class represents items that can be bought from the vending machine
 * It has priceEcoPoints attribute, which represents its price in eco points.
 */
public class BoughtItem extends PortableItem{

    private int priceEcoPoints;

    /**
     * Constructor, which sets the price in eco points
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param priceEcoPoints price in eco points to buy it from the vending machine
     **/
    public BoughtItem(String name, char displayChar, int priceEcoPoints) {
        super(name, displayChar);
        this.priceEcoPoints= priceEcoPoints;
    }


    /**
     * Getter for the ecopoints
     * @return price in ecopoints
     */
    public int getPriceEcoPoints() {
        return priceEcoPoints;
    }

}
