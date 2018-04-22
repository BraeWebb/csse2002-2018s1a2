import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class DecodeTest {

    @Test
    public void testBuilder() {
        Room root = new Room("root");

        Builder encoded = Builder.decode("B;hello;hey world", root);
        Builder expected = new Builder("hello", "hey world", root);

        assertEquals(expected.getShort(), encoded.getShort());
        assertEquals(expected.getLong(), encoded.getLong());

        assertNull(Builder.decode("E;hello;hey world", root));
        assertNull(Builder.decode("B;hellohey world", root));
        assertNull(Builder.decode("B;hello;hey world;hiya", root));
        assertNull(Builder.decode("B;hello;hey world", null));
        assertNull(Builder.decode(null, root));
    }

    @Test
    public void testExplorer() {
        Explorer encoded = Explorer.decode("E;102;michael;john jay");
        Explorer expected = new Explorer("michael", "john jay", 102);

        assertEquals(expected.getShort(), encoded.getShort());
        assertEquals(expected.getLong(), encoded.getLong());
        assertEquals(expected.getHealth(), encoded.getHealth());

        assertNull(Explorer.decode("Q;102;michael;john jay"));
        assertNull(Explorer.decode("E;102d;michael;john jay"));
        assertNull(Explorer.decode("E;102;michaeljohn jay"));
        assertNull(Explorer.decode("E;102;michael;john jay;smith"));
        assertNull(Explorer.decode("E;102.8;michael;john jay"));
        assertNull(Explorer.decode(null));
    }


    @Test
    public void testCritter() {
        Critter encoded = Critter.decode("C;4.21;78;michael;john jay");
        Critter expected = new Critter("michael", "john jay", 4.21, 78);

        assertEquals(expected.getShort(), encoded.getShort());
        assertEquals(expected.getLong(), encoded.getLong());
        assertEquals(expected.getValue(), encoded.getValue(), .01);
        assertEquals(expected.getHealth(), encoded.getHealth());

        assertNull(Explorer.decode("Y;4.21;78;michael;john jay"));
        assertNull(Explorer.decode("C;41;78;michael;john jay"));
        assertNull(Explorer.decode("C;4.21e;78;michael;john jay"));
        assertNull(Explorer.decode("C;4.21;l78;michael;john jay"));
        assertNull(Explorer.decode("C;4.2178;michael;john jay"));
        assertNull(Explorer.decode("C;4.21;78;michael;john jay;james"));
        assertNull(Explorer.decode(null));
    }

    @Test
    public void testTreasure() {
        Treasure encoded = Treasure.decode("$;13.42;pirates gold");
        Treasure expected = new Treasure("pirates gold", 13.42);

        assertEquals(expected.getShort(), encoded.getShort());
        assertEquals(expected.getValue(), encoded.getValue(), .01);

        assertNull(Explorer.decode("L;13.42;pirates gold"));
        assertNull(Explorer.decode("L;142;pirates gold"));
        assertNull(Explorer.decode("L;13.42s;pirates gold"));
        assertNull(Explorer.decode("L;13.42;pirates gold"));
        assertNull(Explorer.decode("L13.42;pirates gold"));
        assertNull(Explorer.decode("L;13.42;pirates gold;aye aye captain"));
        assertNull(Explorer.decode(null));
    }

}
