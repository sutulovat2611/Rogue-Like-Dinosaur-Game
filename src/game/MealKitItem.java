package game;

/**
 * Abstract base class representing a meal kit item.
 **/
public abstract class MealKitItem extends FoodItem {

    /**
     * Constructor.
     * Sets food points to 100.
     * @param name the name of the meal kit
     * @param displayChar the character that is representing the following item if its dropped
     * @param priceEcoPoints the price of the following item in eco points
     */
    public MealKitItem(String name, char displayChar, int priceEcoPoints) {
        super(name, displayChar,100, priceEcoPoints);
    }
}
