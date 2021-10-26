package game;

import edu.monash.fit2099.engine.*;

/**
 * A class that represents the vending machine, which sells different items.
 */
public class VendingMachine extends Ground {

    /**
     * Constructor.
     * Vending machine is represented by 'V'
     */
    public VendingMachine() {
        super('V');
    }

    /**
     * Does not allow actors to step on the vending machine.
     * @param actor The actor that is next to the vending machine.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Override this to implement terrain that blocks thrown objects but not movement, or vice versa
     * @return true
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    /**
     * Returns an Action List with the BuyItemAction, that allows to buy the Item from the vending machine
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        return new Actions(new BuyItemAction(this));
    }

    /**
     * Returns the amount of eco points required to buy the chosen item.
     * @param boughtItem the Item to buy
     * @return requiredPoints number of eco points required to buy the chosen item
     */
    public int getRequiredPoints(Item boughtItem){
        int requiredPoints =0;
        if (boughtItem instanceof BoughtItem){
            requiredPoints = ((BoughtItem) boughtItem).getPriceEcoPoints();
        }
        else if (boughtItem instanceof LaserGun ){
            requiredPoints = ((LaserGun) boughtItem).getPriceEcoPoints();
        }
        return requiredPoints;
    }

    /**
     * Prompts the user to choose the item to buy from the vending machine
     * @return boughtItem item chosen by the user from the vending machine
     */
    public Item menuVendingMachine()
    {

        Display display= new Display();
        display.println("What would you like to purchase?");
        display.println("1. Hay - 20 EP ");
        display.println("2. Fruit - 30 EP ");
        display.println("3. Vegetarian meal kit - 100 EP ");
        display.println("4. Carnivore meal kit - 500 EP ");
        display.println("5. Stegosaur eggs  - 200 EP ");
        display.println("6. Allosaur eggs  - 1000 EP ");
        display.println("7. Archaeopretyx eggs  - 600 EP ");
        display.println("8. Agilisaurus eggs  - 600 EP ");
        display.println("9. Laser gun - 500 EP \n");
        boolean check=true;
        do
        {
            char playerChoice=display.readChar();
            if (playerChoice == '1'){
                return new HayItem();
            }
            else if(playerChoice == '2')
            {
                return new FruitItem();
            }
            else if(playerChoice == '3')
            {
                return new VegetarianMealKitItem("VMK");
            }
            else if(playerChoice == '4')
            {
                return new CarnivoreMealKitItem("CMK");
            }
            else if(playerChoice == '5')
            {
                return new StegosaurEgg("Stegosaur egg");
            }
            else if(playerChoice == '6')
            {
                return new AllosaurEgg("Allosaur egg");
            }
            else if(playerChoice == '7')
            {
                return  new ArchaeopteryxEgg("Archaeopteryx egg");
            }
            else if(playerChoice == '8')
            {
                return new AgilisaurusEgg("Agilisaurus Egg");
            }
            else if(playerChoice == '9')
            {
                return new LaserGun();
            }
            else
            {
                check=false;
            }
        } while (! check);
        return null;
    }
}
