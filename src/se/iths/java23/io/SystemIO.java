//Depinder Kaur, 2024-01-23, depinder.kaur@iths.se

package se.iths.java23.io;

import java.util.Scanner;

public class SystemIO implements IO {
    private Scanner sc = new Scanner(System.in);
    @Override
    public String input() {
        return sc.nextLine();
    }

    @Override
    public void output(String s) {
        System.out.println(s);
    }

    @Override
    public void clear() {
        System.out.println("");
    }

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

    @Override
    public void exit() {
        System.exit(0);
    }
}
