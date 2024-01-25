package se.iths.java23.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    private Player player1, player2;

    @BeforeEach
    public void setup() {
        player1 = new Player("Marie", 3.75);
        player2 = new Player("Charlie", 4.1);
    }

    @Test
    public void getNameTest() {
        assertEquals("Marie", player1.getName());
        assertEquals("Charlie", player2.getName());
    }

    @Test
    public void getAverageTest() {
        assertEquals(3.75, player1.getAverage());
        assertEquals(4.1, player2.getAverage());
    }
}
