package day7;

import filereader.FileReader;

import java.util.*;

public class DaySeven {

    List<String> input;

    public DaySeven(String file) {
        input = FileReader.readFile(file);
    }

    public int findBagsColourAmount() {
        Set<String> bagColours = new HashSet<>();
        bagColours.add("shiny gold");
        findBags(bagColours);
        bagColours.remove("shiny gold");
        int bagColourCounter = 0;
        for (String s : bagColours) bagColourCounter++;
        return bagColourCounter;
    }

    public int findInsideBagsColourAmount() {
        Map<String, Integer> bagColours = new HashMap<>();
        bagColours.put("shinygold", 1);
        findInsideBags(bagColours);
        bagColours.remove("shinygold");
        int sum = 0;
        for (int i : bagColours.values()) sum += i;
        return sum;
    }

    private Set<String> findBags(Set<String> bagColours) {
        Set<String> newBags = new HashSet<>();
        for (String s : input) {
            String[] rule = s.replaceAll("contain", ";").split(";");
            String content = rule[1];
            for (String bag : bagColours) {
                if (content.contains(bag)) {
                    newBags.add(rule[0].replace("bags", "").strip());
                }
            }
            bagColours.addAll(newBags);
        }
        if (newBags.size() == 0) return new HashSet<>();
        bagColours.addAll(findBags(newBags));
        return bagColours;
    }

    private Map<String, Integer> findInsideBags(Map<String, Integer> bagColours) {
        Map<String, Integer> newBags = new HashMap<>();
        for (String s : input) {
            String[] rule = s.replaceAll("contain", ";").split(";");
            String outsideBag = rule[0].replace("bags", "").replaceAll(" ", "").strip();
            for (String bag : bagColours.keySet()) {
                if (outsideBag.contains(bag) && !rule[1].contains("no other bags")) {
                    String[] insideBags = rule[1].split(",");
                    for (String inBag : insideBags) {
                        String temp = inBag.replaceAll(" ", "").replaceAll("\\.", "").replaceAll("(bags)?" , "").replaceAll("(bag)?" , "");
                        int amount = Integer.parseInt(temp.substring(0, 1));
                        String color = temp.substring(1);
                        if (newBags.containsKey(color)) newBags.put(color, (bagColours.get(bag) * amount) + newBags.get(color));
                        else newBags.put(color, (bagColours.get(bag) * amount));
                    }
                }
            }
        }
        if (newBags.keySet().size() == 0) return new HashMap<>();
        findInsideBags(newBags);
        for (String key : newBags.keySet()) {
            if (bagColours.containsKey(key)) bagColours.put(key, (bagColours.get(key)+newBags.get(key)));
            else bagColours.put(key, newBags.get(key));
        }
        return bagColours;
    }
}
