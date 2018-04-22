import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BoundsMapperTest extends MapWalkerTest {

    @Test
    public void testWalk() {
        BoundsMapper mapper = new BoundsMapper(root);
        mapper.walk();

        for (Room room : rooms) {
            assertTrue(mapper.hasVisited(room));
        }

        assertFalse(mapper.hasVisited(new Room("")));
        assertFalse(mapper.hasVisited(new Room("Root Room 1")));
        assertFalse(mapper.hasVisited(new Room("4")));
    }

    @Test
    public void testBounds() {
        BoundsMapper mapper = new BoundsMapper(root);
        mapper.walk();

        assertEquals(2, mapper.xMax);
        assertEquals(3, mapper.yMax);
        assertEquals(-4, mapper.xMin);
        assertEquals(-2, mapper.yMin);
    }

    @Test
    public void testCoords() {
        BoundsMapper mapper = new BoundsMapper(root);
        mapper.walk();

        assertEquals(new Pair(0, 0), mapper.coords.get(root));
        assertEquals(new Pair(0, 1), mapper.coords.get(rooms.get(1)));
        assertEquals(new Pair(-1, 1), mapper.coords.get(rooms.get(2)));
        assertEquals(new Pair(-1, 2), mapper.coords.get(rooms.get(3)));
        assertEquals(new Pair(-1, 3), mapper.coords.get(rooms.get(4)));
        assertEquals(new Pair(0, 3), mapper.coords.get(rooms.get(5)));
        assertEquals(new Pair(1, 3), mapper.coords.get(rooms.get(6)));
        assertEquals(new Pair(2, 3), mapper.coords.get(rooms.get(7)));
        assertEquals(new Pair(2, 2), mapper.coords.get(rooms.get(8)));
        assertEquals(new Pair(2, 1), mapper.coords.get(rooms.get(9)));
        assertEquals(new Pair(-2, 1), mapper.coords.get(rooms.get(10)));
        assertEquals(new Pair(-2, 0), mapper.coords.get(rooms.get(11)));
        assertEquals(new Pair(-3, 0), mapper.coords.get(rooms.get(12)));
        assertEquals(new Pair(-4, 0), mapper.coords.get(rooms.get(13)));
        assertEquals(new Pair(-2, -1), mapper.coords.get(rooms.get(14)));
        assertEquals(new Pair(-2, -2), mapper.coords.get(rooms.get(15)));
        assertEquals(new Pair(-1, -2), mapper.coords.get(rooms.get(16)));
        assertEquals(new Pair(1, 0), mapper.coords.get(rooms.get(17)));
        assertEquals(new Pair(1, -1), mapper.coords.get(rooms.get(18)));
    }
}
