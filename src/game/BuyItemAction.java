package game;

import edu.monash.fit2099.engine.*;

/**
 * Class that allows the user to buy from vending machine
 */
public class BuyItemAction extends Action {

    protected VendingMachine vendingMachine;

    /**
     * Constructor.
     * @param vendingMachine the vending machine that the player interacts with
     */
    public BuyItemAction(VendingMachine vendingMachine)
    {
        this.vendingMachine=vendingMachine;
    }

    /**
     * Performs the Action, that allows the player to buy the item from the vending machine
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description  that can be displayed to the user.
     */
    public String execute(Actor actor, GameMap map) {
        String output = "";
        Item itemToBuy = vendingMachine.menuVendingMachine();
        int requiredEcoPoints = vendingMachine.getRequiredPoints(itemToBuy);
        if (actor instanceof Player) {
            if (((Player) actor).getEcoPoints() >= requiredEcoPoints) {
                ((Player) actor).payEcoPoints(requiredEcoPoints);
                actor.addItemToInventory(itemToBuy);
                output += "Item is successfully bought";
            } else {
                output += "Not enough money to buy the following item";
            }
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
        return actor+" buys from vending Machine";
    }
}
