//Depinder Kaur, 2024-01-23, depinder.kaur@iths.se

package se.iths.java23.logic;

public class BullsAndCows implements Game {

    private int numOfGuesses = 1;

    public int getNumOfGuesses() {
        return numOfGuesses;
    }

    public String generateGoal(){
        String goal = "";
        for (int i = 0; i < 4; i++){
            int random = (int) (Math.random() * 10);
            String randomDigit = "" + random;
            while (goal.contains(randomDigit)){
                random = (int) (Math.random() * 10);
                randomDigit = "" + random;
            }
            goal = goal + randomDigit;
        }
        return goal;
    }

    @Override
    public String getResult(String secretNum, String guess) {
        guess += "    ";      //to avoid IndexOutOfBoundsException when guess is empty
        int cows = 0, bulls = 0;
        for (int i = 0; i < 4; i++){     //should've used goal.length() instead of hard code 4
            for (int j = 0; j < 4; j++ ) {   //should've used guess.length() instead of hard code 4, code checks only first 4 digits
                if (secretNum.charAt(i) == guess.charAt(j)){
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }
        String result = "";
        for (int i = 0; i < bulls; i++){
            result = result + "B";
        }
        result = result + ",";
        for (int i = 0; i < cows; i++){
            result = result + "C";
        }
        return result;
    }

    @Override
    public boolean isFinished(String result) {
        if (!result.equals("BBBB,")) {
            numOfGuesses++;
            return false;
        }
        return true;
    }
}
