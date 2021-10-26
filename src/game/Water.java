package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * Class that represents water pools.
 * The drink points is the amount of points the dinosaur's water level will increase by
 * when they drink from the pool
 */
public class Water extends Ground {
    private int DRINK_POINTS=25;

    /**
     * Constructor
     */
    public Water() {
        super('~');
    }

    /**
     * Does not allow actors, except Archaeopteryx to step on water.
     * @param actor The actor that is next to water pool.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor instanceof Archaeopteryx)
        {
            return true;
        }
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
     * @return the number of water points that the the pool will give to the dinosaur, if it drinks from it
     */
    public int getDRINK_POINTS()
    {
        return DRINK_POINTS;
    }
}
