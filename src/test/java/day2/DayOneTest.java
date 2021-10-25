package day2;

import day1.DayOne;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DayOneTest {

    @Test
    public void testPartOne() {
        int result = DayOne.find2020SumOfTwo("src/test/resources/dayone_test.txt");
        assertEquals(result, 514579);
    }

    @Test
    public void testPartTwo() {
        int result = DayOne.find2020SumOfThree("src/test/resources/dayone_test.txt");
        assertEquals(result, 241861950);
    }
}
