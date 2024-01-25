//Depinder Kaur, 2024-01-25, depinder.kaur@iths.se

package se.iths.java23.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Scrabble implements Game {
    @Override
    public String generateGoal() {
        List<String> allWords = new ArrayList<>();
        Collections.addAll(allWords, "sharp","shelf","shine","slice","solid","space",
                "stand","stone","earth","ebony","eight","entry","extra","towel","cloth","clown",
                "owlet","fairy","faith","lunch","other","their","those","about","jumpy","quick",
                "quack","flirt","jumbo","juicy","melon","crazy","field","glaze","joked","jinks",
                "quake","quark","unzip","blaze","equip","fjord","freak","jerks","judge","mucky",
                "squab","waltz","xylem","zincs","grain","bumpy","champ","craze","float","fishy",
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
    public String showResult(String goal, String guess) {
        guess += "     ";
        int letterAtIncorrectPosition = 0, letterAtCorrectPosition = 0;
        for (int i = 0; i < goal.length(); i++) {
            for (int j = 0; j < guess.length(); j++) {
                if (goal.charAt(i) == guess.charAt(j)) {
                    if (i == j) {
                        letterAtCorrectPosition++;
                    } else {
                        letterAtIncorrectPosition++;
                    }
                }
            }
        }

        String result = "Correct: " + letterAtCorrectPosition + ",    Incorrect: " + letterAtIncorrectPosition;
        return result;
    }
}
