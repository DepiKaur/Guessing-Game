//Depinder Kaur, 2024-01-23, depinder.kaur@iths.se

package se.iths.java23.io;

// IO Adapter
public class WindowIO implements IO {
    SimpleWindow sw = new SimpleWindow("Bulls & Cows");
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
