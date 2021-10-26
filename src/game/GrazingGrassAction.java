package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;

/**
 *  Special action that can be performed by the dinosaur for eating grass
 *  target is the cell of GrassDirt that the dinosaur can graze on.
 */
public class GrazingGrassAction extends Action {

    protected Ground target;

    /**
     * Constructor.
     * @param target grass square to interact with
     */
    public GrazingGrassAction(Ground target) {
        this.target = target;
    }

    /**
     * Performs the Action, that allows the dinosaur to graze on the grass of the square it is standing on
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description  that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        target.setDisplayChar('.');
        if (((Dinosaur)actor).getIs_vegetarian()) {
            ((Dinosaur) actor).increasingFoodLevel(5);
        }
        return menuDescription(actor);
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text to put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " ate grass";
    }
}


