package game;

import edu.monash.fit2099.engine.*;


/**
 * An abstract class that represents Egg.
 * Has two children classes: {StegosaurEgg} and {AllosaurEgg}
 * Has two attributes: turns, which is representing how many turns
 * the egg has been in the dinosaurs inventory
 * and age, which represent how long the egg has been on the ground.
 */
public abstract class EggItem extends FoodItem {

    private int turns = 0;
    private int age =0;

    /**
     * Constructor.
     * @param name - name of the egg
     * @param displayChar - Display character of the egg
     * @param foodPoints - food points of the egg item
     * @param priceEcoPoints - price in eco points
     */
    public EggItem(String name, char displayChar, int foodPoints, int priceEcoPoints) {
        super(name, displayChar, foodPoints, priceEcoPoints );
    }

    /**
     * Create and return an action to drop this Item.
     * If this Item is not portable, returns null.
     * @return a new DropItemAction if this Item is portable, null otherwise.
     */
    @Override
    public DropItemAction getDropAction() {
       return super.getDropAction();
    }

    /**
     * Increases ecoPoints of the player when the egg hatches.
     * @param location location of the item
     * @param ecoPoints the amount of ecoPoints, that will be added to the player, when the egg hatches
     */
    public void increasePlayerPoints(Location location, int ecoPoints){
        GameMap map = location.map();
        NumberRange xRange = map.getXRange();
        NumberRange yRange = map.getYRange();
        for (int x: xRange) {
            for (int y: yRange) {
                Location possibleLocation = map.at(x,y);
                if(possibleLocation.containsAnActor()){
                    Actor actor = possibleLocation.getActor();
                    if (actor instanceof Player){
                        ((Player) actor).gainEcoPoint(ecoPoints);
                    }
                }
            }
        }
    }

    /**
     * Inform a carried Item of the passage of time. If the egg is in the dinosaurs
     * inventory for 10 turns, it will be dropped and the dinosaur will lay
     * an egg.
     *
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        if(actor instanceof Dinosaur) {
            turns+=1;
            if (turns == 10) {
                getDropAction().execute(actor, currentLocation.map());
            }
        }
    }

    /**
     * Updates the age variable. When the age reaches 10, the function calls {hatchEgg} to hatch the egg.
     * @param currentLocation The location of the ground on which actor lies on.
     */
    @Override
    public void tick(Location currentLocation) {
        age++;
        if (age == 10){
            hatchEgg(currentLocation);
        }
    }

    /**
     * Allows the egg to hatch and a new dinosaur to be born based on the type of egg.
     * @param currentLocation the location of the ground the actor lies on
     */
    public abstract void hatchEgg(Location currentLocation);
}
