import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class MapWalkerTest {

    protected Room root;
    protected List<Room> rooms;

    @Before
    public void setup() throws ExitExistsException, NullRoomException{
        root = new Room("Root Room 1");

        rooms = new ArrayList<>();
        rooms.add(root);

        String[] directions = {"North", "East", "North", "North", "West",
                "West", "West", "South", "South"};
        rooms.addAll(buildPath(root, directions, 1));

        String[] directions2 = {"East", "South", "East", "East"};
        rooms.addAll(buildPath(rooms.get(2), directions2, 10));

        String[] directions3 = {"South", "South", "West"};
        rooms.addAll(buildPath(rooms.get(11), directions3, 14));

        String[] directions4 = {"West", "South"};
        rooms.addAll(buildPath(root, directions4, 17));
    }

    private List<Room> buildPath(Room root, String[] directions, int start)
            throws NullRoomException, ExitExistsException {
        List<Room> rooms = new ArrayList<>();

        Room current = root;
        String direction;
        Room next;
        for (int i = 0; i < directions.length; i++) {
            direction = directions[i];
            next = new Room(String.valueOf(i + start));
            rooms.add(next);
            current.addExit(direction, next);
            current = next;
        }

        return rooms;
    }

    @Test
    public void testWalk() {
        MapWalker mapper = new MapWalker(root);
        mapper.walk();
        for (Room room : rooms) {
            assertTrue(mapper.hasVisited(room));
        }

        assertFalse(mapper.hasVisited(new Room("")));
        assertFalse(mapper.hasVisited(new Room("Root Room 1")));
        assertFalse(mapper.hasVisited(new Room("4")));
    }

    @Test
    public void testCyclic() throws ExitExistsException, NullRoomException {
        MapWalker mapper = new MapWalker(root);
        rooms.get(3).addExit("South", rooms.get(2));
        mapper.walk();

        for (Room room : rooms) {
            assertTrue(mapper.hasVisited(room));
        }

        assertFalse(mapper.hasVisited(new Room("")));
        assertFalse(mapper.hasVisited(new Room("Root Room 1")));
        assertFalse(mapper.hasVisited(new Room("4")));
    }
}
