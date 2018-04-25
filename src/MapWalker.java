import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;

public class MapWalker {

    private Room start;
    private ArrayList<Room> visited;

    public MapWalker(Room start) {
        this.start = start;
        this.visited = new ArrayList<>();
        this.reset();
    }

    public boolean hasVisited(Room room) {
        return visited.contains(room);
    }

    protected void reset() {
        this.visited.clear();
    }

    protected void visit(Room room) {

    }

    public void walk() {
        reset();
        ArrayList<Room> toVisit = new ArrayList<>();
        toVisit.add(start);

        while (toVisit.size() > 0) {
            Room current = toVisit.remove(0);
            visit(current);

            // add all rooms which are reachable from the current room and
            // which have not yet been visited to toVisit
            for (Room room : current.getExits().values()) {
                if (!hasVisited(room)) {
                    toVisit.add(room);
                }
            }

            this.visited.add(current);
        }
    }
}
