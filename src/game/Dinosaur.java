package game;

import edu.monash.fit2099.engine.*;
import java.lang.Math;
import java.util.ArrayList;

/**
 * An abstract class that holds all the attributes and methods shared by both kinds of the dinosaur.
 */
public abstract class Dinosaur extends Actor {

    protected int foodLevel;
    protected int waterLevel;
    private ArrayList<Behaviour> behaviours = new ArrayList();
    private String gender; //female or male
    private int age;
    private boolean is_alive=true;
    private int turn=0;
    private boolean is_carnivore;
    private boolean is_vegetarian;

    /**
     * Constructor.
     * Sets the gender of the dinosaur by random.
     * Both dinosaurs have Wander and Hungry behaviours.
     *
     * @param name        Name to of the dinosaur
     * @param displayChar Character to represent the dinosaur in the UI
     * @param hitPoints   Dinosaur's starting number of hitpoints
     * @param foodLevel   level of food, when the dinosaur appears on the map
     */
    public Dinosaur(String name, char displayChar, int hitPoints, int foodLevel, boolean is_carnivore, boolean is_vegetarian) {
        super(name, displayChar, hitPoints);
        setGender();
        behaviours.add(new WanderBehaviour());
        behaviours.add(new HungryBehaviour());
        behaviours.add(new ThirstyBehaviour());
        this.foodLevel = foodLevel;
        this.is_carnivore=is_carnivore;
        this.is_vegetarian=is_vegetarian;
        this.waterLevel=50;
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the Dinosaur.
     * Player is allowed to attack both dinosaurs species
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map){
        Actions actions = new Actions(new FeedAction(this));
        actions.add(new AttackAction(this));
        return actions;
    }

    /**
     * Returns the current age of the dinosaur
     */
    public int getAge() {
        return age;
    }

    /**
     *Returns the current food level
     */
    public int getFoodLevel() {
        return foodLevel;
    }

    /**
     *Returns the current water level
     */
    public int getWaterLevel()
    {
        return waterLevel;
    }

    /**
     * Returns the gender of the dinosaur
     */
    public String getGender() {
        return gender;
    }

    /**
     * Returns if the dinosaur is carnivore
     */
    public boolean getIs_carnivore()
    {
        return is_carnivore;
    }

    /**
     * Returns if the dinosaur is vegetarian
     */
    public boolean getIs_vegetarian()
    {
        return is_vegetarian;
    }

    /**
     * Decreases dinosaur food level and water level by 1
     * This method is called once per turn, if the food level and water level
     * of the dinosaur is more than 0 and the dinosaur is alive.
     */
    public void decreaseFoodWaterLevel()
    {
        if(this.foodLevel >0) {
            this.foodLevel -= 1;
        }
        if(this.waterLevel>0) {
            this.waterLevel -= 1;
        }
    }

    /**
     * Decreases dinosaur food level by 1
     * This method is called once per turn, if the food level of the dinosaur is more than 0 and the dinosaur is alive.
     */
    public void increasingFoodLevel(int increment)
    {   if (increment < 100 - foodLevel){
        this.foodLevel+=increment;
    }
    else
    {
        this.foodLevel = 100; }
    }

    /**
     * Allows the dinosaur to eat food, which increases its food points
     * This method is called if the dinosaur is fed or eating the food it found
     * @param food is the food item that dinosuar is willing to eat
     */
    public boolean eat(FoodItem food){
        if (this.getFoodLevel()==100)
        {
            return false;
        }
        else {
            //if mealKit increases dinosaur food level to maximum
            if (food instanceof MealKitItem) {
                this.foodLevel = 100;
            }
            // if not mealKit increases dinosaur food level by amount food points of this food
            else {
                int increment = food.getFoodPoints();
                this.increasingFoodLevel(increment);
            }
            return true;
        }
    }

    /**
     * Allows the dinosaur to drink water which increases its water points
     * This method is called if the dinosaur is drinking water it found
     * @param water is the food item that dinosuar is willing to eat
     */
    public boolean drink(Water water){
        if (this.getWaterLevel()==100)
        {
            return false;
        }
        else {
            int increment = water.getDRINK_POINTS();
            if (increment < 100 - waterLevel) {
                this.waterLevel+=increment;
            }
            else {
                this.waterLevel = 100;
            }
        }
        return true;
    }

    /**
     * Increases the current dinosaur age by 1
     * This method is called once per turn, if the dinosaur is alive.
     */
    public void increaseAge() {
        this.age+=1;
        if(this.age == 30){
            behaviours.add(new BreedingBehaviour());
        }
    }

    /**
     * Checks the dinosaur food level and hit points to see if the dinosaur is conscious.
     * This method is called once per turn, if the dinosaur is alive
     * @return whether the dinosaur is conscious
     */
    @Override
    public boolean isConscious(){
        boolean consciousness=false;
        if (super.isConscious()&&foodLevel>0&&waterLevel>0)
            consciousness=true;
        return consciousness;
    }

    /**
     * This method randomly sets the gender: female/ male to the dinosaur
     * This method is called once, when the dinosaur object is created
     */
    public void setGender() {
        String[] genders= new String[]{"male", "female"};
        double randomChoice =  Math.random();
        if(randomChoice<0.5){
            randomChoice= 0;
        }
        else{
            randomChoice= 1;
        }
        this.gender=genders[(int) randomChoice];
    }

    /**
     * This method counts how many rounds does the dinosaur stay unconsious.
     * If the counter reaches 20, the dinosaur dies
     * This method is called once per iteration, if the dinosaur is alive
     */
    public void countToDie() {
        if (!isConscious())
        {
            this.turn+=1;
        }
        else
        {
            this.turn=0;
        }
        if (this.turn==20)
        {
            this.is_alive=false;
            //the dinosaur dies and becomes a meat
        }
    }

    /**
     * Every turn:
     * dinosaur food and water level is getting decreased,
     * age is increased
     * checks whether the dinosaur is conscious and if not count turns to die.
     * Dinosaurs have:
     * Hungry behaviour if its food level is lower than 30
     * Thirsty behaviour if its water level is lower than 30
     * Breeding behaviour if its age is more than 30 and food level is more than 50
     *
     * @param actions  collection of possible Actions for this dinosaur
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        decreaseFoodWaterLevel();
        increaseAge();
        countToDie();
        if(this.foodLevel <30){
            System.out.println(this + " at (" + map.locationOf(this).x() + "," +map.locationOf(this).y()+ ") is getting hungry!");
        }
        if (!is_alive) {
            Location locationOfActor = map.locationOf(this);
            map.removeActor(this);
            Item corpse = new CorpseItem();
            locationOfActor.addItem(corpse);
        }
        if (isConscious()) {
            if(this.foodLevel <30){
                Action hungry = behaviours.get(1).getAction(this, map);
                if(hungry != null){
                    return hungry;
                }
            }
            if(this.foodLevel>=30&&this.waterLevel<30){
                Action thirsty = behaviours.get(2).getAction(this, map);
                if(thirsty != null){
                    return thirsty;
                }
            }
            if(this.getAge() >=30 && this.foodLevel>50){
                Action breed = behaviours.get(3).getAction(this,map);
                if(breed != null){
                    return breed;
                }
            }
            Action wander = behaviours.get(0).getAction(this,map);
            if (wander != null) {
                return wander;
            }
        }
        return new DoNothingAction();
    }
}
