package game;

import edu.monash.fit2099.engine.*;
import java.util.List;

/**
 * A carnivorous dinosaur of allosaur specie
 *
 */
public class Allosaur extends Dinosaur {

    /**
     * Constructor.
     * All Allosaurs are represented by 'a' and have 100 hit points.
     *
     * @param name the name of this Allosaur
     */
    public Allosaur(String name) {
        super(name, 'a', 100, 10, true, false);
    }

    /**
     * Select and return an action to perform on the current turn. If the
     * Allosaur is next to the stegosaur ( in the adjacent cells), it will attack
     * the Stegosaur.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (isConscious()) {
            List<Exit> exits = map.locationOf(this).getExits();
            for (Exit exit :exits){
                Location nextTo = exit.getDestination();
                if (nextTo.containsAnActor()){
                    if(nextTo.getActor() instanceof Stegosaur){
                        Dinosaur target = (Dinosaur) nextTo.getActor();
                        return new AttackAction(target);
                    }
                }
            }
        }
        return super.playTurn(actions, lastAction, map, display);
    }
}
