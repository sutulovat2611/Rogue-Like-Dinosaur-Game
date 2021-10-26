package game;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;

import java.util.List;

/**
 * Class that represents the Allosaur eggs in the system
 */
public class AllosaurEgg extends EggItem{

    /**
     * Constructor for the AllosaurEgg
     * All Allosaurs Eggs are represented by 'O' and have food points of 10 and price of 1000 eco points
     * @param name The name of the Allosaur Egg
     */
    public AllosaurEgg(String name) {
        super(name, 'O',10,1000);
    }


    /**
     * This function allows the egg to hatch by removing the egg item and adding
     * a new Allosaur.
     * If there is an actor standing on the location of the egg, it will
     * hatch to the nearest empty location.
     * @param currentLocation the current location of the egg.
     */
    public void hatchEgg(Location currentLocation){
        currentLocation.removeItem(this);
        this.increasePlayerPoints(currentLocation,1000);
        Allosaur newAllosaur = new Allosaur("Allosaur");
        //if the actor is standing on the egg, the dinosaur hatches to the nearest empty square
        if(currentLocation.containsAnActor()){
            List<Exit> exits = currentLocation.getExits();
            for(Exit exit: exits){
                Location newLoc = exit.getDestination();
                if(newLoc.canActorEnter(newAllosaur) && !newLoc.containsAnActor()){
                    newLoc.addActor(newAllosaur);
                    break;
                }
            }
        }
        else {
            currentLocation.addActor(newAllosaur);
        }
    }
}
