import day1.DayOne;
import day2.DayTwo;
import day3.DayThree;
import day4.DayFour;
import day5.DayFive;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Day One a): " + DayOne.find2020SumOfTwo("src/main/resources/dayone.txt"));
        System.out.println("Day One b): " + DayOne.find2020SumOfThree("src/main/resources/dayone.txt"));

        DayTwo dayTwo = new DayTwo("src/main/resources/daytwo.txt");
        System.out.println("Day Two a): " + dayTwo.findValidPasswordsOldRule());
        System.out.println("Day Two b): " + dayTwo.findValidPasswordsNewRule());

        DayThree dayThree = new DayThree("src/main/resources/daythree.txt");
        System.out.println("Day Three a): " + dayThree.findFlightRouteThreeOne());
        System.out.println("Day Three b): " + dayThree.checkMultipleFlightRoutes(dayThreeScenarios()));

        DayFour dayFour = new DayFour("src/main/resources/dayfour.txt");
        System.out.println("Day Four a): " + dayFour.validatePasswordsOldRule());
        System.out.println("Day Four b): " + dayFour.validatePasswordsNewRule());

        DayFive dayFive = new DayFive("src/main/resources/dayfive.txt");
        System.out.println("Day Five a): " + dayFive.findHighestSeatId());
        System.out.println("Day Five b): " + dayFive.findPersonalSeat());
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
