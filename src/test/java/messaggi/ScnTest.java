package test.java.messaggi;

import org.junit.jupiter.api.Test;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class ScnTest {
    private static Scanner instance;

    @Test
    public void testGetInstance() {
        Scanner instance1 = ScnTest.getInstance();
        assertNotNull(instance1);

        Scanner instance2 = ScnTest.getInstance();
        assertSame(instance1, instance2);
    }
    public static Scanner getInstance() {
        if (instance == null) {
            instance = new Scanner(System.in);
        }

        return instance;
    }
}
