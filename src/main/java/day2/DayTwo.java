package day2;

import filereader.FileReader;

import java.util.List;

public class DayTwo {

    List<String> input;

    public DayTwo(String file) {
        this.input = FileReader.readFile(file);
    }

    public int findValidPasswordsOldRule() {
        int countValidPasswords = 0;

        for (String s: input) {
            String[] elements = s.split(" ");
            int[] bounds = findBounds(elements[0]);
            char neededChar = elements[1].charAt(0);
            String password = elements[2];

            if (isValidPasswordOldRule(neededChar, password, bounds)) countValidPasswords++;
        }
        return countValidPasswords;
    }

    public int findValidPasswordsNewRule() {
        int countValidPasswords = 0;

        for (String s: input) {
            String[] elements = s.split(" ");
            int[] positions = findBounds(elements[0]);
            char neededChar = elements[1].charAt(0);
            String password = elements[2];

            if (isValidPasswordNewRule(neededChar, password, positions)) countValidPasswords++;
        }
        return countValidPasswords;
    }

    public boolean isValidPasswordNewRule(char neededChar, String password, int[] positions) {
        int countNeeededCharOccurence = 0;

        for (int i = 0; i < positions.length; i++) {
            if (password.charAt(positions[i]-1) == neededChar) countNeeededCharOccurence++;
            if (countNeeededCharOccurence > 1) return false;
        }
        return countNeeededCharOccurence == 1;
    }

    public boolean isValidPasswordOldRule(char neededChar, String password, int[] bounds) {
        int countNeededChar = (int) password.chars().filter(s -> (char) s == neededChar).count();
        return countNeededChar >= bounds[0] && countNeededChar <= bounds[1];
    }

    public int[] findBounds(String bounds) {
        int[] boundsArray = new int[2];
        String[] boundElements = bounds.split("-");
        boundsArray[0] = Integer.parseInt(boundElements[0]);
        boundsArray[1] = Integer.parseInt(boundElements[1]);
        return boundsArray;
    }
}
