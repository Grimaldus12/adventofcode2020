package day4;

import filereader.FileReader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayFour {

    List<String> passwords;
    List<String> requiredKeys;

    public DayFour(String file) {
        passwords = FileReader.readWithEmptyLine(file);
        requiredKeys = new ArrayList<>();
        requiredKeys.add("byr");
        requiredKeys.add("iyr");
        requiredKeys.add("eyr");
        requiredKeys.add("hgt");
        requiredKeys.add("hcl");
        requiredKeys.add("ecl");
        requiredKeys.add("pid");
    }

    public int validatePasswordsOldRule() {
        int countValidPasswords = 0;
        for(String s : passwords) {
            if (s.contains("cid") && s.split(" ").length == 8) {
                countValidPasswords++;
            } else if (!s.contains("cid") && s.split(" ").length == 7) {
                countValidPasswords++;
            }
        }
        return countValidPasswords;
    }

    public int validatePasswordsNewRule() {
        int countValidPasswords = 0;
        for(String s : passwords) {
            Map<String, String> fields = new HashMap<>();
            for (String k : s.split(" ")) {
                String[] temp = k.split(":");
                fields.put(temp[0], temp[1]);
            }
            if (fields.keySet().containsAll(requiredKeys) && validateValues(fields)) {
                countValidPasswords++;
            }
        }
        return countValidPasswords;
    }

    private boolean validateValues(Map<String, String> fields) {
        for (String key : requiredKeys) {
            String value = fields.get(key);
            switch (key) {
                case "byr":
                    int byr = Integer.parseInt(value);
                    if (byr < 1920 || byr > 2002) return false;
                    break;
                case "iyr":
                    int iyr = Integer.parseInt(value);
                    if (iyr < 2010 || iyr > 2020) return false;
                    break;
                case "eyr":
                    int eyr = Integer.parseInt(value);
                    if (eyr < 2020 || eyr > 2030) return false;
                    break;
                case "hgt":
                    if (value.contains("cm")) {
                        int temp = Integer.parseInt(value.replace("cm", ""));
                        if (temp < 150 || temp > 193) return false;
                    } else if (value.contains("in")) {
                        int temp = Integer.parseInt(value.replace("in", ""));
                        if (temp < 59 || temp > 76) return false;
                    } else {
                        return false;
                    }
                    break;
                case "hcl":
                    Pattern p = Pattern.compile("#[0-9a-f]*");
                    if (!(p.matcher(value).matches())) return false;
                    break;
                case "ecl":
                    int matches = 0;
                    for (String m : new ArrayList<>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"))) {
                        if (value.contains(m)) matches++;
                    }
                    if(matches == 0 || matches > 1) return false;
                    break;
                case "pid":
                    if (value.length() != 9) return false;
                    try {
                        Long.parseLong(value);
                    } catch (Exception e) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
}
