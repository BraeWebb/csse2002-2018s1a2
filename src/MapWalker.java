import java.util.ArrayList;
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
// TODO --> this supposed to happen or nah?
//        this.visited.add(this.start);
    }

    protected void visit(Room room) {
// TODO --> should be empty
//        for (Entry<String, Room> entry : room.getExits().entrySet()) {
//            if (hasVisited(entry.getValue())) {
//                break;
//            }
//            this.visit(entry.getValue());
//            this.visited.add(entry.getValue());
//        }

    }

    public void walk() {
//        this.visit(start);
// TODO: this method calls reset(), according to Joel's slides
        reset();
        ArrayList<Room> toVisit = new ArrayList<>();
        toVisit.add(start);

        while (toVisit.size() > 0) {
            Room current = toVisit.remove(0);
            visit(current);

            // add all rooms which are reachable from the current room and
            // which have not yet been visited to toVisit
            for (Entry<String, Room> entry : current.getExits().entrySet()) {
                if (!hasVisited(entry.getValue())) {
                    toVisit.add(entry.getValue());
                }
            }

            this.visited.add(current);
        }
    }
}
