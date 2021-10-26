package game;

/**
 * FoodItem class represents items that can be eaten by Dinosaurs
 * It has foodPoints attribute, which represents its food points, that the
 * dinosaur gains if eats the following food item.
 */
public abstract class FoodItem extends BoughtItem {

    private int foodPoints;

    /**
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param foodPoints amount of food points the dinosaur gains if it eats the following item
     * @param priceEcoPoints price in eco points to buy it from the vending machine
     **/
    public FoodItem(String name, char displayChar, int foodPoints, int priceEcoPoints ) {
        super(name, displayChar, priceEcoPoints);
        this.foodPoints=foodPoints;
    }

    /**
     * getter for the food points
     * @return food points (amount of food points that the food level of the dinosaur that ate this food item
     * will be increased by)
     */
    public int getFoodPoints() {
        return foodPoints;
    }

    /**
     * getter for the name of the food item
     * @return the name of the food item
     */
    public String getName()
    {
        return this.name;
    }
}
