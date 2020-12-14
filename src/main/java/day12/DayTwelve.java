package day12;

import filereader.FileReader;

import java.io.InvalidObjectException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayTwelve {

    public enum FacingDirection {
        EAST, SOUTH, WEST, NORTH;

        private static final FacingDirection[] vals = values();

        public FacingDirection next() {
            return vals[(this.ordinal()+1)%vals.length];
        }

        public FacingDirection previous() {
            if (this.ordinal()-1 < 0) return vals[vals.length-1];
            else return vals[this.ordinal()-1];
        }
    }

    List<String> input;
    Position position;
    Position waypoint;

    public DayTwelve(String file) {
        input = FileReader.readFile(file);
    }

    public long findCourse() {
        position = new Position(0,0);
        FacingDirection facing = FacingDirection.EAST;

        for (String s : input) {
            char direction = s.charAt(0);
            int steps = Integer.parseInt(s.substring(1));
            if (direction == 'L' || direction == 'R') facing = getNewFacing(s, facing);
            else if (direction == 'F') position.setDirection(steps, facing);
            else {
                try {
                    position.setDirection(steps, getFacing(direction));
                } catch (InvalidObjectException e) {
                    e.printStackTrace();
                }
            }
        }
        return (Math.max(Math.abs(position.getEast()), position.getWest()) + Math.max(position.getNorth(), Math.abs(position.getSouth())));
    }

    public long findCourseNewRule() {
        position = new Position(0,0);
        waypoint = new Position(1,-10);

        for (String s : input) {
            char direction = s.charAt(0);
            int steps = Integer.parseInt(s.substring(1));
            if (direction == 'L' || direction == 'R') waypoint.rotate(s);
            else if (direction == 'F') position.moveToWaypoint(steps, waypoint);
            else {
                try {
                    waypoint.setDirection(steps, getFacing(direction));
                } catch (InvalidObjectException e) {
                    e.printStackTrace();
                }
            }
            //System.out.println("Position and Waypoint");
            //System.out.println("N:" + position.getNorth()+ " S:" + Math.abs(position.getSouth()) + " W:" + position.getWest() + " E:" + Math.abs(position.getEast()));
            //System.out.println("N:" + waypoint.getNorth()+ " S:" + Math.abs(waypoint.getSouth()) + " W:" + waypoint.getWest() + " E:" + Math.abs(waypoint.getEast()));
        }
        return (Math.max(Math.abs(position.getEast()), position.getWest()) + Math.max(position.getNorth(), Math.abs(position.getSouth())));
    }

    private FacingDirection getFacing(char direction) throws InvalidObjectException {
        switch (direction) {
            case 'N': return FacingDirection.NORTH;
            case 'W': return FacingDirection.WEST;
            case 'S': return FacingDirection.SOUTH;
            case 'E': return FacingDirection.EAST;
            default: throw new InvalidObjectException("Wrong facing");
        }

    }

    private FacingDirection getNewFacing(String s, FacingDirection facing) {
        FacingDirection newFacing = facing;
        char direction = s.charAt(0);
        int turns = Integer.parseInt(s.substring(1))/90;
        for (int i = 0; i < turns; i++) {
            if (direction == 'L') newFacing = newFacing.previous();
            else newFacing = newFacing.next();
        }
        return newFacing;
    }
}
