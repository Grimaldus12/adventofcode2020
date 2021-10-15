package day17;

import filereader.FileReader;

import java.util.ArrayList;
import java.util.List;

public class DaySeventeen {

    List<String> input;
    List<Points> currentCycleActive = new ArrayList<>();
    List<Points> nextCycleActive = new ArrayList<>();

    public DaySeventeen(String filename) {
        input = FileReader.readFile(filename);
    }

    private void translateInput3D() {
        for(int i = 0; i < input.size(); i++) {
            String curLine = input.get(i);
            for(int j = 0; j < curLine.length(); j++) {
                if (curLine.charAt(j) == '#') {
                    currentCycleActive.add(new Point3D(i, j, 0));
                }
            }
        }
    }

    private void translateInput4D() {
        for(int i = 0; i < input.size(); i++) {
            String curLine = input.get(i);
            for(int j = 0; j < curLine.length(); j++) {
                if (curLine.charAt(j) == '#') {
                    currentCycleActive.add(new Point4D(i, j, 0,0));
                }
            }
        }
    }

    public long playCycles3D() {
        currentCycleActive.clear();
        nextCycleActive.clear();
        translateInput3D();
        for (int i = 0; i < 6; i++) {
            generateNextCycle3D();
        }
        return currentCycleActive.size();
    }

    public long playCycles4D() {
        currentCycleActive.clear();
        nextCycleActive.clear();
        translateInput4D();
        for (int i = 0; i < 6; i++) {
            generateNextCycle4D();
        }
        return currentCycleActive.size();
    }

    private void generateNextCycle3D() {
        for (Points p : currentCycleActive) {
            determineStateChangeForActives(p, false);
            determineStateChangeForInactives(p, false);
        }
        currentCycleActive = new ArrayList<>(nextCycleActive);
        System.out.println("Active Nodes (after reassign) " + currentCycleActive.size());
        nextCycleActive.clear();
    }

    private void generateNextCycle4D() {
        for (Points p : currentCycleActive) {
            determineStateChangeForActives(p, true);
            determineStateChangeForInactives(p, true);
        }
        currentCycleActive = new ArrayList<>(nextCycleActive);
        System.out.println("Active Nodes (after reassign) " + currentCycleActive.size());
        nextCycleActive.clear();
    }

    private void determineStateChangeForInactives(Points p, boolean extraDimension) {
        int curX = p.getX();
        int curY = p.getY();
        int curZ = p.getZ();
        int curW = 0;
        if (extraDimension) {
            Point4D curPoint = (Point4D) p;
            curW = curPoint.getW();
        }
        for (int i = curX-1; i <= curX+1; i++) {
            for (int j = curY-1; j <= curY+1; j++) {
                for (int k = curZ-1; k <= curZ+1; k++) {
                    if (!extraDimension) {
                        if (curX == i && curY == j && curZ == k) continue;
                        Points curPoint = new Point3D(i, j, k);
                        int countActives = getCountActives(curPoint, false);
                        if (countActives == 3 && !nextCycleActive.contains(curPoint)) nextCycleActive.add(curPoint);
                    } else {
                        for (int l = curW-1; l <= curW+1 ; l++) {
                            if (curX == i && curY == j && curZ == k && curW == l) continue;
                            Points curPoint = new Point4D(i, j, k, l);
                            int countActives = getCountActives(curPoint, true);
                            if (countActives == 3 && !nextCycleActive.contains(curPoint)) nextCycleActive.add(curPoint);
                        }
                    }
                }
            }
        }
    }

    private void determineStateChangeForActives(Points p, boolean extraDimension) {
        int countActives = getCountActives(p, extraDimension);
        if ((countActives == 2 || countActives == 3) && !nextCycleActive.contains(p) ) nextCycleActive.add(p);
    }

    private int getCountActives(Points p, boolean extraDimension) {
        int countActives = 0;
        int curX = p.getX();
        int curY = p.getY();
        int curZ = p.getZ();
        int curW = 0;
        if (extraDimension) {
            Point4D curPoint = (Point4D) p;
            curW = curPoint.getW();
        }
        for (int i = curX -1; i <= curX +1; i++) {
            for (int j = curY-1; j <= curY +1; j++) {
                for (int k = curZ -1; k <= curZ +1; k++) {
                    if (!extraDimension) {
                        if (curX == i && curY == j && curZ == k) continue;
                        for (Points candidate : currentCycleActive) {
                            if (candidate.equals(new Point3D(i, j, k))) countActives++;
                        }
                    } else {
                        for (int l = curW-1; l <= curW+1; l++) {
                            if (curX == i && curY == j && curZ == k && curW == l) continue;
                            for (Points candidate : currentCycleActive) {
                                if (candidate.equals(new Point4D(i, j, k, l))) countActives++;
                            }
                        }
                    }
                }
            }
        }

        return countActives;
    }



    public static void main(String[] args) {
        DaySeventeen daySeventeen = new DaySeventeen("src/main/resources/dayseventeen.txt");
        System.out.println(daySeventeen.playCycles3D());
        System.out.println(daySeventeen.playCycles4D());
    }

}
