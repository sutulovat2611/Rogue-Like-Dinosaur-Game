package game;

/**
 * Class that represent the carnivore meal kit, that can be eaten by carnivore actors.
 */
public class CarnivoreMealKitItem extends MealKitItem {

    /**
     * Constructor.
     * Carnivore meal kits are represented by a '<' and have 500 eco points price.
     * @param name the name of the carnivore meal kit
     *
     */
    public CarnivoreMealKitItem(String name) {
        super(name, '<', 500);
    }
}
