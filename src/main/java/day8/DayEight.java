package day8;

import filereader.FileReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DayEight {

    List<String> input;
    boolean successfulTermination;

    public DayEight(String file) {
        input = FileReader.readFile(file);
        successfulTermination = false;
    }

    public long findInfiniteLoop() {
        return findInfiniteLoopModifiedList(new ArrayList<>(input));
    }

    public long findTermination () {
        for (String i : input) {
            List<String> newInput = new ArrayList<>(input);
            int index = newInput.indexOf(i);
            if (i.contains("nop")) {
                newInput.set(index, i.replace("nop", "jmp"));
            } else if (i.contains("jmp")) {
                newInput.set(index, i.replace("jmp", "nop"));
            }
            long accumulator = findInfiniteLoopModifiedList(newInput);
            if (successfulTermination) return accumulator;
        }
        return -1;
    }

    private long findInfiniteLoopModifiedList(List<String> instructions) {
        Set<Integer> visitedIndices = new HashSet<>();
        long acc = 0;
        int instructionPointer = 0;
        while (true) {
            // termination for part b -> reaching instructionpointer > instruction size
            if (instructionPointer >= instructions.size()) {
                successfulTermination = true;
                return acc;
            }
            String currentInstruction = instructions.get(instructionPointer);
            // termination for part a -> reaching a repeated instruction
            if (checkRepeat(instructionPointer, visitedIndices)) return acc;
            addIndex(instructionPointer, visitedIndices);
            switch (currentInstruction.split(" ")[0]) {
                case "nop":
                    instructionPointer++;
                    break;
                case "acc":
                    acc += Integer.parseInt(currentInstruction.split(" ")[1]);
                    instructionPointer++;
                    break;
                case "jmp":
                    instructionPointer += Integer.parseInt(currentInstruction.split(" ")[1]);
                    break;
            }
        }
    }

    private void addIndex(int instructionPointer, Set<Integer> visitedIndices) {
        visitedIndices.add(instructionPointer);
    }

    private boolean checkRepeat(int instructionPointer, Set<Integer> visitedIndices) {
        return visitedIndices.contains(instructionPointer);
    }
}
