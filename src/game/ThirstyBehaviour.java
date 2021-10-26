package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is responsible for implementing the dinosaur thirsty behaviour:
 * when the water level of the dinosaur is low it starts searching for water source
 * and when it finds it, the dinosaur drinks water
 */
public class ThirstyBehaviour implements Behaviour {

    /** Searches for the nearest water source and if there is one found, moves towards it.
     * When the dinosaur reaches the water pool and is standing in the adjacent cell,
     * it can perform a drinking action.
     *
     * @param dinosaur the dinosaur that is having this behaviour
     * @param map the map where the action is going on
     *
     * @return the Action that the dinosaur performs
     */
    public Action getAction(Dinosaur dinosaur, GameMap map) {

        ArrayList<Location> locations = new ArrayList<>();
        Location locationOfActor = map.locationOf(dinosaur);
        ArrayList<Integer> distances = new ArrayList<>();

        NumberRange xRange = map.getXRange();
        NumberRange yRange = map.getYRange();

        for (int x : xRange) {
            for (int y : yRange) {
                Location current = map.at(x, y);
                if (current.getGround() instanceof Water) {
                    locations.add(current);
                }
            }
        }
        //calculating distances
        for (Location i : locations) {
            distances.add(distance(locationOfActor, i));
        }
        //finding the minimum value
        int minIndex = distances.indexOf(Collections.min(distances));
        //getting the minimum location
        Location minLocation = locations.get(minIndex);
        // getting the exits
        List<Exit> exits = locationOfActor.getExits();
        for (Exit exit : exits) {
            if (exit.getDestination().getGround() instanceof Water) {
                Water target = (Water) exit.getDestination().getGround();
                return new DrinkAction(target);
            }
        }
        return new FollowBehaviour(minLocation).getAction(dinosaur, map);
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
