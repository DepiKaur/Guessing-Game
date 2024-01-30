//Depinder Kaur, 2024-01-23, depinder.kaur@iths.se

package se.iths.java23.io;

import se.iths.java23.logic.BullsAndCows;
import se.iths.java23.logic.GuessingGame;
import se.iths.java23.logic.GuessTheWord;

// IO Adapter
public class WindowIO implements IO {

    SimpleWindow sw;

    public WindowIO(GuessingGame guessingGame) {
        if (guessingGame instanceof BullsAndCows) {
            sw = new SimpleWindow("Bulls & Cows");
        } else if (guessingGame instanceof GuessTheWord) {
            sw = new SimpleWindow("Guess the Word");
        }
    }

    @Override
    public String input() {
        return sw.getString();
    }

    @Override
    public void output(String s) {
        sw.addString(s+"\n");
    }

    @Override
    public void clear() {
        sw.clear();
    }

    public boolean yesNo(String prompt) {
        return sw.yesNo(prompt);
    }

    @Override
    public void exit() {
       sw.exit();
    }
}
