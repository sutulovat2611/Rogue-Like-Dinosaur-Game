package game;

import edu.monash.fit2099.engine.*;

/**
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to a target Actor or a target location.
 */
public class FollowBehaviour implements Behaviour {

	private Actor target;
    private Location location;

	/**
	 * Constructor.
	 * 
	 * @param subject the Actor to follow
	 */
	public FollowBehaviour(Dinosaur subject) {
		this.target = subject;
	}


	/**
	 * Constructor.
	 *
	 * @param minLoc the location of the food item to follow
	 */
	public FollowBehaviour(Location minLoc){
		this.location = minLoc;
	}




	/**
	 * Chooses the shortest path to move towards the chosen actor or location
	 * @param dinosaur the Actor acting
	 * @param map the GameMap containing the Actor
	 * @return an Action that actor can perform, or null if actor can't do this.
	 **/
	@Override
	public Action getAction(Dinosaur dinosaur, GameMap map) {
		if (!map.contains(dinosaur))
			return null;
		Location there;
		if (target != null && location == null) {
			if (!map.contains(target))
				return null;
			there = map.locationOf(target);
		} else {
			there = this.location;
		}
		Location here = map.locationOf(dinosaur);
		int currentDistance = distance(here, there);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(dinosaur)) {
				int newDistance = distance(destination, there);
				if (newDistance < currentDistance) {
					return new MoveActorAction(destination, exit.getName());
				}
			}
		}
		return null;
	}

	/**
	 * Compute the Manhattan distance between two locations.
	 * 
	 * @param a the first location
	 * @param b the first location
	 * @return the number of steps between a and b if you only move in the four cardinal directions.
	 */
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}


}