package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

/**
 * A class that finds the nearest dinosaur to breed and follows it.
 * When target and actor are in adjacent cells, they mate
 */
public class BreedingBehaviour implements Behaviour  {
   /** Searches for the nearest dinosaur of the opposite gender, same kind as an actor and with no egg in its inventory
    * and if there is one found, moves towards it.
    * If the target dinosaur and actor are in the adjacent cells, they perform a breed action.
    * If no dinosaur is found, returns null.
    */
    public Action getAction(Dinosaur dinosaur, GameMap map) {
        Dinosaur target;
        if (!map.contains(dinosaur))
            return null;

        NumberRange xRange = map.getXRange();
        NumberRange yRange = map.getYRange();
        for (int x : xRange) {
            for (int y : yRange) {
                Location possibleLocation = map.at(x, y);
                if (map.isAnActorAt(possibleLocation)) {
                    if (map.getActorAt(possibleLocation) instanceof Dinosaur) {
                        target = (Dinosaur) map.getActorAt(possibleLocation);
                        if (dinosaur.getClass().equals(target.getClass())) {
                            if (dinosaur.getGender() != target.getGender()) {
                                if (dinosaur.getInventory().size() == 1 || target.getInventory().size() == 1) {
                                    return null;
                                }
                                List<Exit> exits = map.locationOf(dinosaur).getExits();
                                for (Exit exit : exits) {
                                    if (exit.getDestination() == map.locationOf(target)) {
                                        return new BreedingAction(target);
                                    }
                                }
                                return new FollowBehaviour(target).getAction(dinosaur, map);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}

