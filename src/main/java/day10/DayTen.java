package day10;

import filereader.FileReader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DayTen {

    ArrayList<Long> input;

    public DayTen(String file) {
        input = FileReader.readFile(file).stream().map(Long::parseLong).collect(Collectors.toCollection(ArrayList::new));
    }

    public long findJoltChain() {
        long currentJolt = 0;
        long joltDifferenceOne = 0;
        long joltDifferenceThree = 0;
        ArrayList<Long> joltChain = new ArrayList<>();
        ArrayList<Long> adapters = new ArrayList<>(input);
        while (!adapters.isEmpty() && currentJolt < Collections.max(adapters)) {
            for (long i = currentJolt + 1; i <= currentJolt + 3; i++) {
                if (adapters.contains(i)) {
                    int index = adapters.indexOf(i);
                    joltChain.add(adapters.get(index));
                    adapters.remove(index);
                    if (i == currentJolt + 1) joltDifferenceOne++;
                    if (i == currentJolt + 3) joltDifferenceThree++;
                    currentJolt = i;
                    break;
                }
            }
        }
        return joltDifferenceOne*(joltDifferenceThree+1);
    }

    public long totalJoltChains() {
        return findAllJoltChains(input);
    }

    private long findAllJoltChains(List<Long> availableAdapter) {
        ArrayList<Long> adapters = new ArrayList<>(availableAdapter);
        adapters.add(0, (long) 0);
        adapters.add(Collections.max(adapters) + 3);
        Collections.sort(adapters);
        long[] ways = new long[adapters.size()];
        ways[0] = 1;
        for (int i = 1; i < adapters.size(); i++) {
            ways[i] = 0;
            for (int j = i-1; j >= 0; j--) {
                if (adapters.get(i) - adapters.get(j) <= 3) {
                    ways[i] += ways[j];
                } else {
                    break;
                }
            }
        }

        return ways[ways.length-1];
    }
}
