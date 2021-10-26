package game;

import edu.monash.fit2099.engine.*;

/**
 * Special action that can be performed by the player for harvesting grass
 * target is the grass cell that will be harvested
 */
public class HarvestGrassAction extends Action {

    protected Ground target;

    /**
     * Constructor.
     * @param target grass square to interact with
     */
    public HarvestGrassAction(Ground target) {
        this.target = target;
    }

    /**
     * Performs the Action, that allows the player to harvest the grass of the square he/she is standing on
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description  that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        target.setDisplayChar('.');
        if(actor instanceof Player){
            HayItem hay = new HayItem();
            actor.addItemToInventory(hay);
            ((Player) actor).gainEcoPoint(1);
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
        return actor + " harvest grass ";
    }
}
