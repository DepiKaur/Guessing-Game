package se.iths.java23.io;

public interface IO {
    String input();
    void output(String s);
    void clear();
    boolean yesNo(String prompt);
    void exit();
}
