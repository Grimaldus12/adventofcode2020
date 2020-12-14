package day12;

public class Position {

    int north;
    int west;

    public Position(int north, int west) {
        this.north = north;
        this.west = west;
    }

    int getNorth() {
        return Math.max(north, 0);
    }

    int getSouth() {
        return Math.min(north, 0);
    }

    int getWest() {
        return Math.max(west, 0);
    }

    int getEast() {
        return Math.min(west, 0);
    }

    int getTrueWest() {
        return west;
    }

    int getTrueNorth() {
        return north;
    }

    void setDirection(int step, DayTwelve.FacingDirection facing) {
        switch (facing) {
            case EAST:
                west -= step;
                break;
            case WEST:
                west += step;
                break;
            case NORTH:
                north += step;
                break;
            case SOUTH:
                north -= step;
                break;
        }
    }

    public void moveToWaypoint(int steps, Position waypoint) {

        for (int i = 1; i <= steps; i++) {
            this.north += waypoint.getTrueNorth();
            this.west += waypoint.getTrueWest();
        }
    }

    public void rotate(String s) {
        char direction = s.charAt(0);
        int turns = Integer.parseInt(s.substring(1))/90;
        for (int i = 0; i < turns; i++) {
            if (direction == 'R') {
                int temp = this.north;
                this.north = west;
                this.west = -temp;
            }
            else  {
                int temp = this.north;
                this.north = -west;
                this.west = temp;
            }
        }
    }
}
