package game;

import edu.monash.fit2099.engine.*;

/**
 * A carnivorous dinosaur of archaeopteryx specie
 */
public class Archaeopteryx extends Dinosaur {
    /**
     * Constructor
     * All Archaeopteryxes are represented by 'x' , have hit points=100,
     * food level=10, and they are carnivores
     * @param name Name to of the dinosaur
     */
    public Archaeopteryx(String name) {
        super(name, 'x', 100, 10, true, false);
    }

}
