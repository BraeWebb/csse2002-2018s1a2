import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReprTest {

    @Test
    public void testCritter() {
        Critter critter = new Critter("cat", "a cat", 14.5, 2);
        assertEquals("C;14.50000;2;cat;a cat", critter.repr());
    }

    @Test
    public void testBuilder() {
        Builder builder = new Builder("robert", "There were ;\n chars but they were replaced",
                new Room("my room"));
        assertEquals("B;robert;There were ** chars but they were replaced", builder.repr());
    }

    @Test
    public void testExplorer() {
        Explorer explorer = new Explorer("doris", "There were ** chars but they were replaced", 2);
        assertEquals("E;2;doris;There were ** chars but they were replaced", explorer.repr());
    }

    @Test
    public void testTreasure() {
        Treasure treasure = new Treasure("box", 14.5);
        assertEquals("$;14.50000;box", treasure.repr());
    }

}
