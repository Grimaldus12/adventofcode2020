package day11;

import filereader.FileReader;

import java.util.List;

public class DayEleven {

    List<String> input;

    public DayEleven(String file) {
        input = FileReader.readFile(file);
    }

    private int[][] getInitialSeats() {
        int[][] seats = new int[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            char[] charArray = s.toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                if (charArray[j] == 'L') seats[i][j] = 0;
                else seats[i][j] = -1;
            }
        }
        return seats;
    }

    public long findStableSeatSet() {
        int[][] seats = getInitialSeats();
        boolean steadyState = false;
        while (!steadyState) {
            steadyState = nextGeneration(seats, false, 4);
        }
        return getOccupiedSeats(seats);
    }

    public long findStableSeatSetNewRule(int adjacentAllowence) {
        int[][] seats = getInitialSeats();
        boolean steadyState = false;
        while(!steadyState) {
            steadyState = nextGeneration(seats, true, adjacentAllowence);
        }
        return getOccupiedSeats(seats);
    }

    private boolean nextGeneration(int[][] currSeats, boolean newRule, int adjacentMax) {
        int[][] newSeats = new int[currSeats.length][currSeats[0].length];
        copyArray(currSeats, newSeats);
        int countChanges = 0;
        for (int i = 0; i < currSeats.length; i++) {
            for (int j = 0; j < currSeats[0].length; j++) {
                if (currSeats[i][j] == -1) {
                    newSeats[i][j] = -1;
                    continue;
                }
                int adjacentSeats = countAdjacent(currSeats, i , j, newRule);
                if (currSeats[i][j] == 0 && adjacentSeats == 0) {
                    newSeats[i][j] = 1;
                    countChanges++;
                } else if (currSeats[i][j] == 1 && adjacentSeats >= adjacentMax) {
                    newSeats[i][j] = 0;
                    countChanges++;
                }
            }
        }
        copyArray(newSeats, currSeats);
        return countChanges == 0;
    }

    private int countAdjacent(int[][] seats, int row, int column, boolean newRule) {
        int countAdjacentSeats = 0;
        if (!newRule) {
            for (int i = row - 1; i <= row + 1; i++) {
                if (i < 0 || i >= seats.length) continue;
                for (int j = column - 1; j <= column + 1; j++) {
                    if (j < 0 || j >= seats[0].length || (j == column && i == row)) continue;
                    if (seats[i][j] == 1) countAdjacentSeats++;
                }
            }
        } else {
            //check Left
            for (int i = column-1; i >= 0; i--) {
                if (seats[row][i] != -1) {
                    if (seats[row][i] == 1) countAdjacentSeats++;
                    break;
                }

            }
            //check Right
            for (int i = column+1; i < seats[0].length; i++) {
                if (seats[row][i] != -1) {
                    if (seats[row][i] == 1) countAdjacentSeats++;
                    break;
                }
            }
            //check Top
            for (int i = row-1; i >= 0; i--) {
                if (seats[i][column] != -1) {
                    if (seats[i][column] == 1) countAdjacentSeats++;
                    break;
                }
            }
            //check Bottom
            for (int i = row+1; i < seats.length; i++) {
                if (seats[i][column] != -1) {
                    if (seats[i][column] == 1) countAdjacentSeats++;
                    break;
                }
            }
            //check Top Left
            int offset = 1;
            for (int i = row-1; i >= 0; i--) {
                int j = column - offset;
                if (j < 0) break;
                if (seats[i][j] != -1) {
                    if (seats[i][j] == 1) countAdjacentSeats++;
                    break;
                }
                offset++;
            }
            //check Lower Left
            offset = 1;
            for (int i = row+1; i <seats.length; i++) {
                int j = column - offset;
                if (j < 0) break;
                if (seats[i][j] != -1) {
                    if (seats[i][j] == 1) countAdjacentSeats++;
                    break;
                }
                offset++;
            }
            //check Top Right
            offset = 1;
            for (int i = row-1; i >= 0; i--) {
                int j = column + offset;
                if (j >= seats[0].length) break;
                if (seats[i][j] != -1) {
                    if (seats[i][j] == 1) countAdjacentSeats++;
                    break;
                }
                offset++;
            }
            //check Lower Right
            offset = 1;
            for (int i = row+1; i <seats.length; i++) {
                int j = column + offset;
                if (j >= seats[0].length) break;
                if (seats[i][j] != -1) {
                    if (seats[i][j] == 1) countAdjacentSeats++;
                    break;
                }
                offset++;
            }
        }
        return countAdjacentSeats;
    }

    private int getOccupiedSeats(int[][] seats) {
        int occupiedSeats = 0;
        for (int[] seat : seats) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seat[j] == 1) occupiedSeats++;
            }
        }
        return occupiedSeats;
    }

    private void copyArray(int[][] currSeats, int[][] newSeats) {
        for (int i = 0; i < currSeats.length; i++) {
            System.arraycopy(currSeats[i], 0, newSeats[i], 0, currSeats[0].length);
        }
    }

    private void printSeats(int[][] seats) {
        System.out.println("NEW GEN");
        for (int[] seat : seats) {
            for (int j = 0; j < seats[0].length; j++) {
                System.out.print(seat[j]);
            }
            System.out.println();
        }
    }
}
