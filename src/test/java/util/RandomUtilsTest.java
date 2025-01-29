package test.java.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import util.RandomUtils;

public class RandomUtilsTest {

    @Test
    public void testGetRandomNumber() {
        for (int i = 0; i < 100; i++) {
            int result = RandomUtils.getRandomNumber(1, 10);
            assertTrue(result >= 1 && result <= 10);
        }
    }

    @Test
    public void testRollD4() {
        for (int i = 0; i < 100; i++) {
            int result = RandomUtils.rollD4();
            assertTrue(result >= 1 && result <= 4);
        }
    }

    @Test
    public void testRollD6() {
        for (int i = 0; i < 100; i++) {
            int result = RandomUtils.rollD6();
            assertTrue(result >= 1 && result <= 6);
        }
    }

    @Test
    public void testRollD20() {
        for (int i = 0; i < 100; i++) {
            int result = RandomUtils.rollD20();
            assertTrue(result >= 1 && result <= 20);
        }
    }
}