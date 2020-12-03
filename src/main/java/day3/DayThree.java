package day3;

import filereader.FileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DayThree {


    public static class TraversePath  {
        final int right;
        final int down;

        public TraversePath(int right, int down) {
            this.right = right;
            this.down = down;
        }
    }

    List<String> profile;
    int[][] map;

    public DayThree(String file) {
        profile = FileReader.readFile(file);
        map = new int[profile.size()][profile.get(0).length()];
    }

    public int findFlightRouteThreeOne() {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (profile.get(i).charAt(j) == '#') {
                    map[i][j] = 1;
                } else {
                    map[i][j] = 0;
                }
            }
        }

        return checkFlightRouteEncounters(3,1);
    }


    public long checkMultipleFlightRoutes(List<TraversePath> paths) {
        List<Integer> encounters = new ArrayList<>();
        for (TraversePath p : paths) {
            encounters.add(checkFlightRouteEncounters(p.right, p.down));
        }
        long productEncounters = 1;
        for (int i : encounters) {
            productEncounters *= i;
        }
        return productEncounters;
    }

    private Integer checkFlightRouteEncounters(int right, int down) {
        //System.out.println("NEW PATH");
        int encounteredTrees = 0;
        int j = 0;
        for (int i = 0; i < map.length-down; i += down) {
            j += right;
            if (j >= map[0].length) {
                j = j - map[0].length;
            }
            //System.out.println("i: " + (i+down) + ", j: " + j);
            if(map[i+down][j] == 1) {
                encounteredTrees++;
            }
        }
        //System.out.println("Encounters: " + encounteredTrees);
        return encounteredTrees;

    }
}
