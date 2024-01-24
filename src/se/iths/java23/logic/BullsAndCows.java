package se.iths.java23.logic;

import se.iths.java23.dao.ResultDAO;

public class BullsAndCows implements Playable{

    private ResultDAO resultDao;     //dependency injection

    public void setResultDao(ResultDAO resultDao) {
        this.resultDao = resultDao;
    }

    public String generateNumberOrWord(){
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
    public String showResult(String number, String guess) {
        guess += "    ";
        int cows = 0, bulls = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++ ) {
                if (number.charAt(i) == guess.charAt(j)){
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
    public boolean gameHasNotStarted() {
        return generateNumberOrWord().isEmpty();
    }
}
