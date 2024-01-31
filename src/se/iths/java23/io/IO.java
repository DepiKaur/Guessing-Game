package se.iths.java23.io;

/**
 * @author Depinder Kaur
 * @date 2024-01-24
 * @version 1.0
 * <p>
 * <h2>IO</h2>
 * IO is the interface that allows interaction between the user and
 * the game through input and output.
 */

public interface IO {
    String input();
    void output(String s);
    void clear();
    boolean yesNo(String prompt);
    void exit();
}
