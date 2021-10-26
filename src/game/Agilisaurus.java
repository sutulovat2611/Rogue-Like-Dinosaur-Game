package game;

import edu.monash.fit2099.engine.*;

/**
 * A omnivorous dinosaur of agilisaurus specie
 */
public class Agilisaurus extends Dinosaur{
    /**
     * Constructor
     * All Agilisaurus are represented by 'i' , have hit points=100,
     * food level=10, and they are omnivorous
     * @param name Name to of the dinosaur
     */
    public Agilisaurus(String name) {
        super(name, 'i', 100, 10, true, true);
    }

    /**
     * Select and return an action to perform on the current turn.
     * Beside actions that both dinosaurs can perform,
     * Stegosaur is able to graze on grass, which increases its food level.
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
            if (this.getFoodLevel() < 100) {
                Location locationOfPlayer = map.locationOf(this);
                if (locationOfPlayer.getGround() instanceof GrassDirt) {
                    Action grazingGrass = ((GrassDirt) locationOfPlayer.getGround()).getGrazingGrassAction();
                    if (grazingGrass != null) {
                        return grazingGrass;
                    }
                }
            }
        }
        return super.playTurn(actions, lastAction, map, display);
    }
}
