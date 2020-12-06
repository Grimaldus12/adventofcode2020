package day6;

import filereader.FileReader;

import java.util.*;

public class DaySix {

    List<String> answersPerGroup;

    public DaySix(String file) {
        answersPerGroup = FileReader.readWithEmptyLine(file);
    }

    public int findTotalYesAnswer() {
        int countAnswers = 0;
        for (String group : answersPerGroup) {
            countAnswers += findGroupYesAnswer(group);
        }

        return countAnswers;
    }

    public int findTotalYesAnswersNewRule() {
        int countAnswers = 0;
        for (String group : answersPerGroup) {
            countAnswers += findGroupYesAnswersNewRule(group);
        }
        return countAnswers;
    }

    private int findGroupYesAnswersNewRule(String group) {
        int numberOfPeople = group.split("\\s").length;
        char[] givenAnswers = group.replace(" ", "").toCharArray();
        Map<Character, Integer> countAnswerFrequency = new HashMap<>();
        for (char c : givenAnswers) {
            if (countAnswerFrequency.containsKey(c)) {
                countAnswerFrequency.put(c, (countAnswerFrequency.get(c)+1));
            } else {
                countAnswerFrequency.put(c, 1);
            }
        }

        int everyoneYes = 0;
        for (char key : countAnswerFrequency.keySet()) {
            if (countAnswerFrequency.get(key) == numberOfPeople) everyoneYes++;
        }
        return everyoneYes;
    }

    private int findGroupYesAnswer(String group) {
        char[] givenAnswers = group.replace(" ", "").toCharArray();
        Set<Character> uniqueAnswers = new HashSet<>();
        for (char c : givenAnswers) {
            uniqueAnswers.add(c);
        }
        return uniqueAnswers.size();
    }
}
