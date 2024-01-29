//Depinder Kaur, 2024-01-23, depinder.kaur@iths.se

package se.iths.java23.io;

import se.iths.java23.logic.BullsAndCows;
import se.iths.java23.logic.Game;
import se.iths.java23.logic.GuessTheWord;

// IO Adapter
public class WindowIO implements IO {

    SimpleWindow sw;

    public WindowIO(Game game) {
        if (game instanceof BullsAndCows) {
            sw = new SimpleWindow("Bulls & Cows");
        } else if (game instanceof GuessTheWord) {
            sw = new SimpleWindow("Scrabble");
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
