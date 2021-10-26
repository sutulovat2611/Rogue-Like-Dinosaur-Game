package game;


import edu.monash.fit2099.engine.*;

/**
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends Dinosaur {

	/**
	 * Constructor.
	 * All Stegosaurs are represented by a 's' and have 100 hit points.
	 *
	 * @param name the name of this Stegosaur
	 * @param foodLevel food level of the Stegosaur when it is initialised
	 */
	public Stegosaur(String name, int foodLevel) {
		super(name, 's', 100, foodLevel, false, true);
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
