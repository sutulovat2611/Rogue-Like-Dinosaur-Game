package game;

import edu.monash.fit2099.engine.Location;

/**
 * Class that is responsible for initialising the fruit item.
 * It has an attribute turns, which shows how long the fruit
 * has been lying on the ground.
 **/
public class FruitItem extends FoodItem {

    private int turns = 0;

    /**
     * Constructor.
     * All fruits are represented by 'F', have the name 'fruit', food points of 30 and price of 30 in eco points.
     */
    public FruitItem() {
        super("Fruit", 'F',30, 30);
    }

    /**
     * Allows the fruit to rot away if it's left on the ground for too long
     * @param currentLocation location where the fruit is left
     */
    @Override
    public void tick(Location currentLocation) {
        turns +=1;
        if (turns ==20){
            currentLocation.removeItem(this);
        }
    }
}
