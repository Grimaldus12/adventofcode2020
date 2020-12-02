import day1.DayOne;
import day2.DayTwo;

public class Main {

    public static void main(String[] args) {
        System.out.println("Day One a): " + DayOne.find2020SumOfTwo("src/main/resources/dayone.txt"));
        System.out.println("Day One b): " + DayOne.find2020SumOfThree("src/main/resources/dayone.txt"));

        DayTwo dayTwo = new DayTwo("src/main/resources/daytwo.txt");
        System.out.println("Day Two a): " + dayTwo.findValidPasswordsOldRule());
        System.out.println("Day Two b): " + dayTwo.findValidPasswordsNewRule());
    }
}
