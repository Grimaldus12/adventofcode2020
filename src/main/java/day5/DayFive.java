package day5;

import filereader.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DayFive {

    private List<String> input;

    public DayFive(String file) {
        input = FileReader.readFile(file);
    }

    public long findHighestSeatId() {
        long maxID = 0;
        for (String s : input) {
            String row = s.substring(0,7);
            String column = s.substring(7);
            long rowID = findRowID(row);
            long columnID = findColumnID(column);
            long id = (rowID*8) + columnID;
            if (id > maxID) maxID = id;
        }
        return maxID;
    }

    public long findPersonalSeat() {
        List<Long> seats = getAllSeatIds();
        Collections.sort(seats);
        for (int i = 0; i < seats.size()-1; i++) {
            if (seats.get(i) < (seats.get(i+1)-1)) return seats.get(i)+1;
        }
        return 0;
    }

    private List<Long> getAllSeatIds() {
        List<Long> seats = new ArrayList<>();
        for (String s : input) {
            String row = s.substring(0,7);
            String column = s.substring(7);
            long rowID = findRowID(row);
            long columnID = findColumnID(column);
            seats.add((rowID*8) + columnID);
        }
        return seats;
    }

    private long findRowID(String row) {
        long lowerBound = 0;
        long upperBound = 127;
        for (char c : row.toCharArray()) {
            if (c == 'F') {
                upperBound -= (long) Math.ceil((double) (upperBound-lowerBound)/2);
            } else {
                lowerBound += (long) Math.ceil((double) (upperBound-lowerBound)/2);
            }
        }
        if (row.substring(6).equals("F")) return lowerBound;
        else return upperBound;
    }

    private long findColumnID(String column) {
        long lowerBound = 0;
        long upperBound = 7;
        for (char c : column.toCharArray()) {
            if (c == 'L') {
                upperBound -= (long) Math.ceil((double) (upperBound-lowerBound)/2);
            } else {
                lowerBound += (long) Math.ceil((double) (upperBound-lowerBound)/2);
            }
        }
        if (column.substring(2).equals("L")) return lowerBound;
        else return upperBound;
    }
}
