package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;

import java.util.List;

/**
 * An egg of carnivorous dinosaur of archaeopteryx specie
 */
public class ArchaeopteryxEgg extends EggItem {

    /**
     * Constructor
     * All Archaeopteryx Eggs are represented by 'Q' , have food points of 10 and price of 600 eco points
     * @param name The name of the Archaeopteryx Egg
     */
    public ArchaeopteryxEgg(String name) {
        super(name, 'Q',10,600);
    }

    /**
     * This function allows the egg to hatch by removing the egg item and adding
     * a new Archaeopteryx.
     * If there is an actor standing on the location of the egg, it will
     * hatch to the nearest empty location.
     * @param currentLocation the current location of the egg.
     */
    public void hatchEgg(Location currentLocation){
        currentLocation.removeItem(this);
        this.increasePlayerPoints(currentLocation,600);
        Archaeopteryx newArchaeopteryx = new Archaeopteryx("Archaeopteryx");
        //if the actor is standing on the egg, the dinosaur hatches to the nearest empty square
        if(currentLocation.containsAnActor()){
            List<Exit> exits = currentLocation.getExits();
            for(Exit exit: exits){
                Location newLoc = exit.getDestination();
                if(newLoc.canActorEnter(newArchaeopteryx) && !newLoc.containsAnActor()){
                    newLoc.addActor(newArchaeopteryx);
                    break;
                }
            }
        }
        else{
            currentLocation.addActor(newArchaeopteryx);
        }

    }
}
