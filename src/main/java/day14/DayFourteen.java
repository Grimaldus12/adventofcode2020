package day14;

import filereader.FileReader;

import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayFourteen {

    List<String> input;
    Map<Long, Long> memory;
    Mask mask;

    public DayFourteen(String file) {
        input = FileReader.readFile(file);
        mask = new Mask();
    }

    public long sumOfMemory() {
        memory = new HashMap<>();
        for (String cmd : input) {
            if (cmd.contains("mask")) processMasking(cmd);
            else processMemWrite(cmd);
        }
        return sumValues();
    }

    public long decoder2 () {
        memory = new HashMap<>();
        for (String cmd : input) {
            if (cmd.contains("mask")) processMaskingVer2(cmd);
            else processMemWriteVer2(cmd);
        }
        return sumValues();
    }

    private long sumValues() {
        long sumOfMemory = 0;
        for (long key : memory.keySet()) sumOfMemory += memory.get(key);
        return sumOfMemory;
    }

    private void processMaskingVer2(String cmd) {
        String pureMask = cmd.split("=")[1].replace(" ", "");
        mask.resetMask();
        for (int i = 0; i < pureMask.length(); i++) {
            if (pureMask.charAt(i) == 'X') mask.setIndexFloating(35-i);
            else if (pureMask.charAt(i) == '1') mask.setIndexOne(35-i);
        }
    }

    private void processMemWriteVer2(String cmd) {
        long address = Long.parseLong(cmd.split("\\[")[1].split("]")[0]);
        long value = Long.parseLong(cmd.split("=")[1].replace(" ", ""));
        List<Long> addresses = mask.applyAddress(address);
        for (long addressFloating : addresses) {
            memory.put(addressFloating, value);
        }
    }

    private void processMemWrite(String cmd) {
        long address = Long.parseLong(cmd.split("\\[")[1].split("]")[0]);
        long value = Long.parseLong(cmd.split("=")[1].replace(" ", ""));
        value = mask.applyValue(value);
        memory.put(address, value);
    }

    private void processMasking(String cmd) {
        String pureMask = cmd.split("=")[1].replace(" ", "");
        mask.resetMask();
        for (int i = 0; i < pureMask.length(); i++) {
            if (pureMask.charAt(i) == '0') mask.setIndexZero(35-i);
            else if (pureMask.charAt(i) == '1') mask.setIndexOne(35-i);
        }
    }
}
