package game;

/**
 * Class that represent the Hay item.
 */
public class HayItem extends FoodItem{

    /**
     * Constructor.
     * Hay items are represented by a 'h', have the name 'Hay', food points of 20 and price of 20 eco points
     */
    public HayItem() {
        super("Hay", 'h', 20, 20);
    }
}
