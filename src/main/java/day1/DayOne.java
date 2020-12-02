package day1;

import filereader.FileReader;

import java.util.List;
import java.util.stream.Collectors;

public class DayOne {

    public static int find2020SumOfTwo(String file) {
        List<String> input = FileReader.readFile(file);

        List<Integer> inputInt = input.stream().map((Integer::parseInt)).collect(Collectors.toList());
        for (int i : inputInt) {
            int diff = 2020 - i;
            if (inputInt.contains(diff)) return i*diff;
        }
        return -1;
    }

    public static int find2020SumOfThree(String file) {
        List<String> input = FileReader.readFile(file);

        List<Integer> inputInt = input.stream().map((Integer::parseInt)).collect(Collectors.toList());
        for (int i : inputInt) {
            for (int j : inputInt) {
                if (!(inputInt.indexOf(i) == inputInt.indexOf(j))) {
                    int leftDiff = 2020-i-j;
                    if (inputInt.contains(leftDiff)) {
                        return i*j*leftDiff;
                    }
                }
            }
        }
        return -1;
    }
}
