package game;

import edu.monash.fit2099.engine.*;
import java.util.List;


/**
 * A class that represents bare dirt and grass.
 */
public class GrassDirt extends Ground {

	/**
	 * Constructor.
	 * Initially setting the square to "."
	 */
	public GrassDirt() {
		super('.');
	}

	/**
	 * Allows the player to harvest the grass
	 *
	 * @return if the cell is grass it returns initialised harvest grass action
	 * otherwise returns null
	 */
	public HarvestGrassAction getHarvestGrassAction(){
		if (displayChar == 'g'){
			return new HarvestGrassAction(this);
		}
		return null;
	}

	/**
	 * Allows the dinosaur to graze on the grass
	 *
	 * @return if the cell is grass it returns initialised graze on grass action
	 * 	otherwise returns null
	 */
	public GrazingGrassAction getGrazingGrassAction(){
		if (displayChar == 'g'){
			return new GrazingGrassAction(this);
		}
		return null;
	}

	/**
	 * Increases the amount of player's eco points by searching for all the players on the map.
	 *
	 * @param location location where the player is standing
	 */
	public void increasePlayerPoints(Location location){
		GameMap map = location.map();
		NumberRange xRange = map.getXRange();
		NumberRange yRange = map.getYRange();
		for (int x: xRange) {
			for (int y: yRange) {
				Location possibleLocation = map.at(x,y);
				if(possibleLocation.containsAnActor()){
					Actor actor = possibleLocation.getActor();
					if (actor instanceof Player){
						((Player) actor).gainEcoPoint(1);
					}
				}
			}
		}
	}

	/**
	 * Calculates the probability based on the requirements and allows for the dirt to grow into grass.
	 * These requirements are:
	 * If there are no Grass cells nearby or there is a tree in the neighbour cell,
	 * Dirt has a 2% of growing into grass, but if there are 2 or more grass cells around,
	 * the Dirt has a probability of 10% of growing into Grass.
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);
		List<Exit> exits =location.getExits();
		boolean isTree=false;
		int grassCounter=0;
		for (Exit exit :exits){
			Location nextTo = exit.getDestination();
			Ground ground = nextTo.getGround();
			if (ground instanceof Tree)
			{
				isTree=true;
			}
			//counter for grass changes if there is grass around
			else if(ground.getDisplayChar()== 'g') {
				grassCounter+=1;
			}
		}
		if(location.getGround().getDisplayChar() != 'g')
		{
			if (isTree){
				if(Math.random()<=0.02)
				{
					location.getGround().setDisplayChar('g');
					increasePlayerPoints(location);
				}
			}
			else
			{
				if(grassCounter>=2){
					if(Math.random()<=0.1){
						location.getGround().setDisplayChar('g');
						increasePlayerPoints(location);
					}
				}
				//if there is neither tree nor grass around it has 2% probability of growing
				else{
					if(Math.random()<=0.02){
						location.getGround().setDisplayChar('g');
						increasePlayerPoints(location);
					}
				}
			}
		}
	}
}