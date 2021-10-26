package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Special Action for allowing the dinosaur to drink from the water pool.
 * water is an instance of water pool that dinosaur will be drinking from
 */
public class DrinkAction extends Action {

    private Water water;

    /**
     * Constructor.
     * @param water is the water pool that will be drank by the dinosaur
     */
    public DrinkAction(Water water) {
        this.water=water;
    }

    /**
     * Performs the Action, that allows the dinosaur to drink from the target water pool
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description  that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(actor instanceof Dinosaur){
            if (((Dinosaur) actor).drink(water))
            {
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
        return actor +" drank water ";
    }

}
