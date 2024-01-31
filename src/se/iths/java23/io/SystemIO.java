package se.iths.java23.io;

import java.util.Scanner;

/**
 * @author Depinder Kaur
 * @date 2024-01-23
 * @version 1.0
 * <p>
 * <h2>SystemIO</h2>
 * SystemIO implements the <i>IO</i> interface and interacts with user through console.
 */
public class SystemIO implements IO {
    private Scanner sc = new Scanner(System.in);

    /**
     * @return User input of type String.
     */
    @Override
    public String input() {
        return sc.nextLine();
    }

    /**
     * This method prints out the given String in the console.
     * @param s The given String to be shown as output in the console.
     */
    @Override
    public void output(String s) {
        System.out.println(s);
    }

    @Override
    public void clear() {
        System.out.println("");
    }

    /**
     * This method asks the user if he wants to continue playing the guessing game,
     * after he has correctly guessed the secret number/word.
     * @param input Here the user is asked if he wants to continue playing the guessing game.
     * @return A boolean value which decides if the game will continue or not.
     */
    @Override
    public boolean yesNo(String input) {
        System.out.println(input);
        System.out.println("y/n");
        String userChoice = sc.nextLine();
        if (userChoice.equalsIgnoreCase("y")) {
            return true;
        }
        return false;
    }

    /**
     * This method ends the program if the user does not want to play further.
     */
    @Override
    public void exit() {
        System.exit(0);
    }
}
