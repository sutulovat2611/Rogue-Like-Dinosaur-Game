package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * The class that represents the search tree for fruit action
 * target is an attribute that represents the tree to be searched.
 */

public class SearchTreeAction extends Action {

    private Ground target;

    /**
     * Constructor.
     * @param target the tree to search
     */
    public SearchTreeAction(Ground target)
    {
        this.target = target;
    }

    /**
     * Perform the Action, that allows the player to find a fruit on this tree with the probability of 40%
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description, whether the user found the item or not that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String output = menuDescription(actor);
        if(Math.random()<=0.4){
            FruitItem fruit = new FruitItem();
            actor.addItemToInventory(fruit);
            output+= "\n You found a fruit!";
        } else{
            output += "\n You search the tree for fruit, but you can't find ripe ones";
        }
        return output;
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text to put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " search tree ";
    }
}
