//Depinder Kaur, 2024-01-25, depinder.kaur@iths.se

package se.iths.java23.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GuessTheWord implements GuessingGame {

    private int numOfGuesses;

    @Override
    public String generateNumberOrWord() {
        List<String> allWords = new ArrayList<>();
        Collections.addAll(allWords, "sharp","shelf","shine","slice","solid","space",
                "stand","stone","earth","ebony","eight","entry","extra","towel","cloth","clown",
                "owlet","fairy","faith","lunch","other","their","those","about","jumpy","quick",
                "quack","flirt","jumbo","juicy","melon","crazy","field","glaze","joked","jinks",
                "quake","quark","unzip","blaze","equip","fjord","freak","jerks","judge","mucky",
                "squab","waltz","cloth","zincs","grain","bumpy","champ","craze","float","fishy",
                "picky","unbox","froze","topaz","wacky","zebra","black","brown","white","chunk",
                "choke","comfy","dozen","equal","mixed","fixed","gazes","graze","shirt","inbox",
                "lucky","lymph","pixel","quint","quids","squad","squid","waxed","zesty","spain",
                "fruit","glare","zoned","zonal","aside","brick","boxes","boxer","curvy","dozes",
                "frock","frame","flaky","exams","exact","light","might","fight","right","mixer",
                "lumpy","bumpy","munch","packs","pecks","pinky","prick","mocks","ducks","frown",
                "grate","notes","sport","mayor","flare","dirty","timer","tired","tried","fried");

        Random random = new Random();
        int randomNum = random.nextInt(allWords.size());
        return allWords.get(randomNum);
    }

    @Override
    public String showResult(GuessEvaluation guessEvaluation) {
        return "Correct Position: " + guessEvaluation.valueCountAtCorrectPlace() + "\nIncorrect Position: " + guessEvaluation.valueCountAtIncorrectPlace();
    }


    public GuessEvaluation checkResult(String numberOrWord, String guess) {
        guess += "     ";
        int letterAtIncorrectPosition = 0, letterAtCorrectPosition = 0;
        for (int i = 0; i < numberOrWord.length(); i++) {
            for (int j = 0; j < guess.length(); j++) {
                if (numberOrWord.charAt(i) == guess.charAt(j)) {
                    if (i == j) {
                        letterAtCorrectPosition++;
                    } else {
                        letterAtIncorrectPosition++;
                    }
                }
            }
        }

        return new GuessEvaluation(letterAtCorrectPosition, letterAtIncorrectPosition);
        //"Correct Position: " + letterAtCorrectPosition +"\nIncorrect Position: " + letterAtIncorrectPosition;
    }

    @Override
    public boolean isFinished(String result) {
        if (result.equals("Correct Position: 5\nIncorrect Position: 0")) {
            return true;
        }
        numOfGuesses++;
        return false;
    }

    @Override
    public int getNumOfGuesses() {
        return numOfGuesses;
    }
}
