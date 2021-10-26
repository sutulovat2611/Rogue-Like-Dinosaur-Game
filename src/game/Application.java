package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {
		boolean check = true; //while loop iterator, lets the user to leave the app only when the quit option is chosen
		while (check){
			World world = new World(new Display());
			FancyGroundFactory groundFactory = new FancyGroundFactory(new GrassDirt(), new Wall(), new Floor(), new Tree(), new VendingMachine(), new Water());
			List<String> map = Arrays.asList(
					"................................................................................",
					"................................................................................",
					".....#######....................................................................",
					".....#V____#..................................~~~~~~............................",
					".....#_____#.........................................~~~~~~~....................",
					".....###.###................................................~~~~~~..............",
					"................................................................................",
					"......................................+++.......................................",
					".......................................++++.....................................",
					"...........~~~~....................+++++........................................",
					"..............~~~~...................++++++.....................................",
					".................~~~~.................+++.......................................",
					".....................................+++........................................",
					"................................................................................",
					"............+++.................................................................",
					".............+++++..............................................................",
					"...............++........................................+++++..................",
					".............+++....................................++++++++....................",
					"............+++..............~~~~~~...................+++.......................",
					".........................~~~~~~...~~~~~~........................................",
					".........................................................................++.....",
					"........................................................................++.++...",
					".........................................................................++++...",
					"..........................................................................++....",
					"................................................................................");
			GameMap gameMap = new GameMap(groundFactory, map );
			world.addGameMap(gameMap);
			// adding the 2nd map
			List<String> map2 = Arrays.asList(
					"................................................................................",
					"................................................................................",
					".....______.....................................................................",
					"....._V____...................................~~~~~~............................",
					".....______..........................................~~~~~~~....................",
					"............................................................~~~~~~..............",
					"................................................................................",
					"......................................+++.......................................",
					".......................................++++.....................................",
					"...........~~~~....................+++++........................................",
					"..............~~~~...................++++++.....................................",
					".................~~~~.................+++.......................................",
					".....................................+++........................................",
					"................................................................................",
					"............+++.................................................................",
					".............+++++..............................................................",
					"...............++........................................+++++..................",
					".............+++....................................++++++++....................",
					"............+++..............~~~~~~...................+++.......................",
					".........................~~~~~~...~~~~~~........................................",
					".........................................................................++.....",
					"........................................................................++.++...",
					".........................................................................++++...",
					"..........................................................................++....",
					"................................................................................");
			GameMap gameMap2 = new GameMap(groundFactory, map2 );
			world.addGameMap(gameMap2);

			// Adding exits
			// map 1:
			for (int i =0; i<=79; i++){
				Location atMap1 = gameMap.at(i,0);
				Location atMap2 = gameMap2.at(i,24);
				atMap1.addExit(new Exit("North - Map 2",atMap2, "8"));
				atMap2.addExit(new Exit("South - Map 1", atMap1, "2"));
				if (i != 79) {
					Location atMap2East = gameMap2.at(i+1,24);
					atMap1.addExit(new Exit("North-East - Map 2",atMap2East, "9"));
					Location atMap1East = gameMap.at(i+1,0);
					atMap2.addExit(new Exit("South-East - Map 1",atMap1East, "3"));
				}
				if (i!=0){
					Location atMap2West = gameMap2.at(i-1,24);
					atMap1.addExit(new Exit("North-West - Map 2",atMap2West, "7"));
					Location atMap1West = gameMap.at(i-1,0);
					atMap2.addExit(new Exit("South-West - Map 1",atMap1West, "1"));
				}
			}

			Player player = new Player("Player", '@', 100);
			world.addPlayer(player, gameMap.at(9, 3));

			// Place a pair of stegosaurs in the middle of the map
			gameMap.at(10, 12).addActor(new Stegosaur("Stegosaur", 30));
			gameMap.at(15, 12).addActor(new Stegosaur("Stegosaur", 30));

			// choosing the game mode
			Display display= new Display();
			display.println(" "); // prints a new line
			display.println("What game mode would you like to play?");
			display.println("1. Challenge game");
			display.println("2. Sandbox game");
			display.println("3. Quit program");
			DisplayInt displayInt = new DisplayInt();
			char playerChoice=display.readChar();
			if(playerChoice == '1' || playerChoice == '2'){ //checking for valid inputs
				if(playerChoice == '1'){ //challenge game
					boolean moveCheck = true;
					while(moveCheck){
						displayInt.println("choose the number of moves: ");
						boolean success = true; //stays tru if the user inputs do not raise error
						int moveChoice = 0; //initializing number of moves
						int ecoPointsChoice = 0; //initializing number of eco points
						try {
							moveChoice = displayInt.readInt(); //prompting the user for number of moves
							displayInt.println("choose the number of Eco Points: ");
							ecoPointsChoice = displayInt.readInt(); //prompting the user for number of eco points
							if(moveChoice<0 || ecoPointsChoice <0 ) //validating values
							{
								success = false;
								System.out.println("Ensure that both inputs are greater than zero!");
							}
						}
						catch(Exception e) //catching if the error is raised
						{
							success = false;
							System.out.println("Ensure that both inputs are integers!");
						} 
						if (success) //if both inputs are valid,  the game starts
						{
							player.setChallenge(moveChoice, ecoPointsChoice);
							moveCheck = false;
						}
					}
				}
				world.run();
			}
			else if( playerChoice == '3') //quit
			{
				check = false;
			}
			else //in case of invalid user input
			{
				display.println("Invalid input. Please choose from the displayed menu!");
			}
		}
	}
}
