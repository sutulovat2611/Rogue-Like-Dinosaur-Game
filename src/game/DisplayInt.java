package game;

import edu.monash.fit2099.engine.Display;
import java.util.Scanner;

/**
 * The child class of Display, that allows the user input an integer that will be read
 */
public class DisplayInt extends Display {

    private Scanner keyboard = new Scanner(System.in);

    /**
     * Reads an integer from the keyboard
     *
     * @return the value that the user input
     */
    public int readInt() {
        int num = Integer.parseInt(String.valueOf(keyboard.nextLine()));
        return num;
    }
}
