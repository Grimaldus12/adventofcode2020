package day13;

import filereader.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.stream;

public class DayThirteen {

    List<String> input;
    List<Long> buses;
    final long earliestDeparture;

    public DayThirteen(String file) {
        input = FileReader.readFile(file);
        this.earliestDeparture = Long.parseLong(input.get(0));
        getBuses();
    }

    private void getBuses() {
        buses = new ArrayList<>();
        for (String s : input.get(1).split(",")) {
            if (s.equals("x")) buses.add((long)-1);
            else buses.add(Long.parseLong(s));
        }
    }

    public long findEarliestBus() {
        long lowestWaitingTime = Long.MAX_VALUE;
        long bestBusID = 0;
        for (long bus : buses)  {
            if (bus == -1) continue;
            if (earliestDeparture % bus == 0) {
                return 0;
            }
            long multiplier = (earliestDeparture/bus) + 1;
            long waitingTime = Math.abs(earliestDeparture - (multiplier*bus));
            if (waitingTime < lowestWaitingTime) {
                lowestWaitingTime = waitingTime;
                bestBusID = bus;
            }
        }
        return lowestWaitingTime * bestBusID;
    }

    public long findEarliestTimestamp (){
        long smallestCommenDivider = buses.get(0), currentTimestamp = buses.get(0);
        int index = 1;
        while (true) {
            final long busID = buses.get(index);
            //skip "x" busses
            if (busID == -1) {
                index++;
                continue;
            }

            if ((currentTimestamp + index) % busID == 0) {
                // if all bus ids match the rules stop and return the timestamp
                if (++index >= buses.size()) {
                    return currentTimestamp;
                }
                // since all busIDs are primes, their smallest common divider c = a * b
                smallestCommenDivider *= busID;
                continue;
            }
            // if we haven't found a solution, skip to the next timestamp where at least the
            // first few buses are obeying the rules
            currentTimestamp += smallestCommenDivider;
        }
    }



}
