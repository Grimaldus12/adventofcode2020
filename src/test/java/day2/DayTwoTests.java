package day2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DayTwoTests {

    DayTwo dayTwo;

    @Before
    public void before() {
        dayTwo = new DayTwo("src/test/resources/daytwo_dummy.txt");
    }

    @Test
    public void testValidBounds() {
        assertArrayEquals(new int[]{1,2}, dayTwo.findBounds("1-2"));
    }

    @Test
    public void testValidPasswordOld() {
        assertTrue(dayTwo.isValidPasswordOldRule('a', "aaadd", new int[]{1,3}));
    }

    @Test
    public void testInvalidPasswordOld() {
        assertFalse(dayTwo.isValidPasswordOldRule('b', "aaadd", new int[]{1,3}));
    }

    @Test
    public void testCountPasswords() {
        assertEquals(dayTwo.findValidPasswordsOldRule(), 2);
    }

    @Test
    public void testValidPasswordNew() {
        assertTrue(dayTwo.isValidPasswordNewRule('b', "abaab", new int[]{1,2}));
    }

    @Test
    public void testInvalidPasswordNew() {
        assertFalse(dayTwo.isValidPasswordNewRule('b', "bbaab", new int[]{1,2}));
    }

    @Test
    public void testInvalidPasswordNonMatchingNew() {
        assertFalse(dayTwo.isValidPasswordNewRule('b', "aawiaijwl", new int[]{1,2}));
    }
}
