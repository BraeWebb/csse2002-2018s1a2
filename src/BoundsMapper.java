import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoundsMapper extends MapWalker {

    private Room root;

    public Map<Room, Pair> coords;
    public int xMin;
    public int xMax;
    public int yMin;
    public int yMax;

    public BoundsMapper(Room room) {
        super(room);
        root = room;
        reset();
    }

    public void reset() {
        super.reset();

        coords = new HashMap<>();

        xMin = 0;
        xMax = 0;
        yMin = 0;
        yMax = 0;
    }

    @Override
    protected void visit(Room room) {
        Pair pair = coords.get(room);
        int x = 0;
        int y = 0;

        for (Entry<String, Room> entry : room.getExits().entrySet()) {

            x = pair.x;
            y = pair.y;

            switch (entry.getKey()) {
                case "North":
                    y += 1;
                    break;
                case "South":
                    y -= 1;
                    break;
                case "East":
                    x -= 1;
                    break;
                case "West":
                    x += 1;
                    break;
            }
            coords.put(entry.getValue(), new Pair(x, y));

            if (x < xMin) {
                xMin = x;
            } else if (x > xMax) {
                xMax = x;
            }

            if (y < yMin) {
                yMin = y;
            } else if (y > yMax) {
                yMax = y;
            }
        }

        super.visit(room);
    }

    public static void main(String[] args) throws ExitExistsException, NullRoomException{

        Room room = new Room("Root Room");

        Room north = new Room("North Room");
        Room northNorth = new Room("North North Room");
        Room northNorthWest = new Room("North North West Room");

        room.addExit("North", north);
        north.addExit("North", northNorth);
        northNorth.addExit("North", northNorthWest);
        northNorth.addExit("West", northNorthWest);
        northNorthWest.addExit("West", new Room("North North West West Room"));
        room.addExit("South", new Room("South Room"));
        room.addExit("East", new Room("East Room"));
        room.addExit("West", new Room("West Room"));

        BoundsMapper mapper = new BoundsMapper(room);
        mapper.walk();

        System.out.println(mapper.coords);
        for (Entry<Room, Pair> entry : mapper.coords.entrySet()) {
            System.out.print(entry.getKey().getDescription() + ": ");
            System.out.println(entry.getValue().x + " " + entry.getValue().y);
        }

        System.out.println();
        System.out.println(mapper.xMin + " " + mapper.xMax);
        System.out.println(mapper.yMin + " " + mapper.yMax);
    }
}
