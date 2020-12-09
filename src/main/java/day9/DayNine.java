package day9;

import filereader.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class DayNine {

    ArrayList<Long> input;

    public DayNine(String file) {
        input = FileReader.readFile(file).stream().map(Long::parseLong).collect(Collectors.toCollection(ArrayList::new));
    }

    public long findNonMatchingValue() {
        int offset = 25;
        for (int i = offset; i < input.size(); i++) {
            boolean fundSum = false;
            for (int j = i-offset; j < i; j++) {
                fundSum = false;
                for (int k = i-offset; k < i; k++) {
                    if (k == j) continue;
                    if (input.get(k)+input.get(j) == input.get(i)) {
                        //System.out.println("TEST " + input.get(k) + " " + input.get(j) + " = " + input.get(i));
                        fundSum = true;
                        break;
                    }
                }
                if (fundSum) break;
            }
            if (!fundSum) return input.get(i);
        }
        return 0;
    }

    public long findAddingSequence() {
        long invalidNumber = findNonMatchingValue();
        for (int i = 0; i < input.size(); i++) {
            int j = i;
            long sum = 0;
            while (j < input.size()) {
                sum += input.get(j);
                if (sum == invalidNumber) return findSum(i,j);
                if (sum > invalidNumber) j = input.size();
                j++;
            }
        }
        return -1;
    }

    private long findSum(int i, int j) {
        return Collections.max(input.subList(i,j)) + Collections.min(input.subList(i,j));
    }
}
