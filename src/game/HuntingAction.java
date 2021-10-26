package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

/**
 *
 * This action allows the Allosaur to hunt for the Agilisaurus and
 * Allows the Archaeopteryx to hunt for all other dinosaurs.
 * The hunting is implemented when the dinosaur is next to its target dinosaur.
 * Hunting damages other dinosaur by 50.
 */
public class HuntingAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public HuntingAction(Actor target) {
        this.target = target;
    }

    /**
     * Allows the dinosaur to attack another dinosaur
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the Action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int damage = 50;
        target.hurt(damage);
        if(!target.isConscious()){
            map.removeActor(target);
            if (target instanceof Agilisaurus){
                ((Dinosaur) actor).increasingFoodLevel(25);
            } else {
                ((Dinosaur) actor).increasingFoodLevel(50);
            }
            return menuDescription(actor);
        }
        return actor + " attacked " + target;
    }

    /**
     * Returns a description of this movement suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "actor is hunting the target"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " hunted " + target;
    }
}
