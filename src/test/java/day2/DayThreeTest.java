package day2;

import day3.DayThree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DayThreeTest {

    @Test
    public void dayThreeTest() {
        DayThree dayThree = new DayThree("src/test/resources/daythree_test.txt");
        int partOne = dayThree.findFlightRouteThreeOne();
        long partTwo = dayThree.checkMultipleFlightRoutes(dayThreeScenarios());
        assertEquals(partOne, 7);
        assertEquals(partTwo, 336);
    }

    private static List<DayThree.TraversePath> dayThreeScenarios() {
        List<DayThree.TraversePath> paths = new ArrayList<>();
        paths.add(new DayThree.TraversePath(1,1));
        paths.add(new DayThree.TraversePath(3,1));
        paths.add(new DayThree.TraversePath(5,1));
        paths.add(new DayThree.TraversePath(7,1));
        paths.add(new DayThree.TraversePath(1,2));
        return paths;
    }
}
