package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Special Action for allowing the dinosaur to eat food items.
 * item is the food item that will be eaten by the dinosaur
 */
public class EatingAction extends Action {

    private FoodItem item;

    /**
     * Constructor.
     * @param item is the food item that will be eaten by the dinosaur
     */
    public EatingAction(FoodItem item){
        this.item = item;
    }

    /**
     * Performs the Action, that allows the dinosaur to eat the target item
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description  that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(actor instanceof Dinosaur){
            if (((Dinosaur) actor).eat(this.item)) {
                map.locationOf(actor).removeItem(this.item);
                return menuDescription(actor);
            }
        }
        return null;
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text to put on the menu
     */
    @Override
      public String menuDescription(Actor actor) {
        return actor +" ate " + this.item;
    }

}
