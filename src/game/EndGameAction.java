package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class that is responsible for ending the game.
 */
public class EndGameAction extends Action {

    /**
     * Removes the player from the map, which stops the game
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return this.menuDescription(actor);
    }

    /**
     * Descriptive string.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Player quits the game";
    }
}
