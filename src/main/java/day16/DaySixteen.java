package day16;

import filereader.FileReader;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DaySixteen {

    List<String> input;
    List<Rule> rules = new ArrayList<>();
    List<Ticket> tickets = new ArrayList<>();
    Ticket ownTicket = new Ticket();

    public DaySixteen(String filename) { input = FileReader.readFile(filename); }

    public long scanTickets() {
        int inputType = 0;
        long overallScore = 0;
        for (int i = 0; i < input.size(); i++) {
           if(input.get(i).isEmpty()) {
               inputType++;
               continue;
           } else if (input.get(i).equals("your ticket:") || input.get(i).equals("nearby tickets:")) {
               continue;
           }
           switch (inputType){
               case 0:
                   createRule(input.get(i));
                   break;
               case 1:
                   ownTicket = createTicket(input.get(i));
               case 2:
                   long curScore = checkTicket(input.get(i));
                   if (curScore == 0) tickets.add(createTicket(input.get(i)));
                   overallScore += curScore;
                   break;
           }
        }
        return overallScore;
    }

    private Ticket createTicket(String s) {
        Ticket curTicket = new Ticket();
        for(String value : s.split(",")) {
            curTicket.addParameter(Long.parseLong(value));
        }
        return curTicket;
    }

    private long checkTicket(String s) throws IllegalArgumentException {
        long score = 0;
        for (String value : s.split(",")) {
            boolean isValid = false;
            for (Rule r : rules) {
                if (r.isInRange(Long.parseLong(value))) isValid = true;
            }
            if (!isValid) score += Long.parseLong(value);
        }
        return score;
    }

    private void createRule(String s) {
        String[] splitString = s.split(":")[1].split(" ");
        String[] firstBound = splitString[1].split("-");
        String[] secondBound = splitString[3].split("-");
        rules.add(new Rule(s.split(":")[0], Long.parseLong(firstBound[0]), Long.parseLong(firstBound[1]),
                Long.parseLong(secondBound[0]), Long.parseLong(secondBound[1])));
    }

    public long assignParameters() {
        Map<Rule, List<Integer>> overallValidPosition = new HashMap<>();
        for (Rule r : rules) {
            List<Integer> validPositions = IntStream.range(0,tickets.get(0).getParameterSize()).boxed().collect(Collectors.toList());
            for (Ticket t : tickets) {
                for(int i = 0; i < t.getParameterSize(); i++) {
                    boolean isValid = r.isInRange(t.getParameter(i));
                    if(!isValid) validPositions.remove(Integer.valueOf(i));
                }
            }
            overallValidPosition.put(r, validPositions);
        }

        assignRules(overallValidPosition);
        long product = 1;
        for(Rule r : rules)  {
            if (r.getName().contains("departure")) {
                product *= ownTicket.getParameter(r.getAssignedIndex().intValue());
            }
        }

        return product;
    }

    private void assignRules(Map<Rule, List<Integer>> overallValidPosition) {
        int sortedRules = 0;
        while(sortedRules < rules.size()) {
            int lastSetPosition = -1;
            for(Rule rule : overallValidPosition.keySet()) {
                if (rule.getAssignedIndex() != null) continue;
                if (overallValidPosition.get(rule).size() == 1) {
                    sortedRules++;
                    rule.setAssignedIndex(overallValidPosition.get(rule).get(0));
                    lastSetPosition = overallValidPosition.get(rule).get(0);
                }
            }
            for(Rule rule : overallValidPosition.keySet()) {
                List<Integer> curList = overallValidPosition.get(rule);
                curList.remove(Integer.valueOf(lastSetPosition));
                overallValidPosition.put(rule, curList);
            }
        }
    }

    public static void main(String[] args) {
        DaySixteen ds = new DaySixteen("src/main/resources/daysixteen.txt");
        System.out.println(ds.scanTickets());
        System.out.println(ds.assignParameters());
    }
}
