package day15;

import filereader.FileReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayFifteen {

    List<String> input;
    Map<Long, Occurence> turn = new HashMap<>();
    long lastNumber;

    public DayFifteen(String file) {
        input = FileReader.readFile(file);
    }

    public long find2020(long stop) {
        long index = processPuzzleInput();
        keepPlaying(index, stop);

        return lastNumber;
    }

    private void keepPlaying(long start, long stop) {
        long index = start;
        long numberInQuestion = lastNumber;
        while (index <= stop) {
            if (turn.get(numberInQuestion).isNew()) {
                if (turn.get((long) 0) != null) {
                    turn.put((long) 0, turn.get((long) 0).setNewOccurence(index));
                    numberInQuestion = 0;
                }
            } else {
                long difference = turn.get(numberInQuestion).getDiff();
                if (turn.get(difference) == null) {
                    turn.put(difference, new Occurence(index, index));
                } else {
                    turn.put(difference, turn.get(difference).setNewOccurence(index));
                }
                numberInQuestion = difference;
            }
            index++;

            //turn.forEach((key, value) -> System.out.println("Number " + key + " Turn " + value.toString() ));
        }
        lastNumber = numberInQuestion;

    }

    private long processPuzzleInput() {
        String[] oneliner = input.get(0).split(",");
        long index = 1;
        for (String number : oneliner) {
            turn.put(Long.parseLong(number), new Occurence(index, index));
            lastNumber = Long.parseLong(number);
            index++;
        }
        return index;
    }
}
