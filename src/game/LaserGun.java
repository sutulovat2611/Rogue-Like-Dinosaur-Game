package game;

import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * The class that represents the laser gun.
 * It is sold in the vending machine and therefore it has
 * PRICE_ECO_POINTS attribute.
 **/
public class LaserGun extends WeaponItem implements Weapon {

    private final int PRICE_ECO_POINTS = 500;

    /**
     * Constructor.
     * Laser guns are represented by a 'L', have the name 'LaserGun', damage of 50 and a verb representing it "shooting"
     */
    public LaserGun() {
        super("LaserGun", 'L', 50, "shooting");
    }

    /**
     * Getter for the price in eco points
     * @return price in eco points.
     */
    public int getPriceEcoPoints() {
        return PRICE_ECO_POINTS;
    }
}
