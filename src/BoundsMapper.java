import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoundsMapper extends MapWalker {

    public Map<Room, Pair> coords;
    public int xMin;
    public int xMax;
    public int yMin;
    public int yMax;

    public BoundsMapper(Room room) {
        super(room);
    }

    public void reset() {
        super.reset();

        coords = new HashMap<>();

        xMin = 0;
        xMax = 0;
        yMin = 0;
        yMax = 0;
    }

    /**
     * Assign room coordinates relative to a neighbour.
     * If room has no known neighbours, give it coordinate (0,0).
     * If your "North" neighbour has coordinates (x,y), then your coodinates should be (x, y-1).
     * If your "East" neighbour has coordinates (x,y), then
     * your coordinates should be (x+1, y).
     * (Similar for South and West).
     * Check for known coordinates in order: North, South, East, West.
     *
     * @require All exits are labelled one of {"North", "South", "East", "West"}
     * @param room Room to assign coordinates to
     */
    @Override
    protected void visit(Room room) {
        super.visit(room);

        Pair location;

        int x = 0;
        int y = 0;

        for (Entry<String, Room> entry : room.getExits().entrySet()) {

            if (!coords.containsKey(entry.getValue())) {
                continue;
            }

            location = coords.get(entry.getValue());
            x = location.x;
            y = location.y;

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
        }

        xMax = Math.max(xMax, x);
        yMax = Math.max(yMax, y);
        xMin = Math.min(xMin, x);
        yMin = Math.min(yMin, y);

        coords.put(room, new Pair(x, y));
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
