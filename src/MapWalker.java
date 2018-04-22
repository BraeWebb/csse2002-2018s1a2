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
        this.visited.add(this.start);
    }

    protected void visit(Room room) {
        for (Entry<String, Room> entry : room.getExits().entrySet()) {
            if (hasVisited(entry.getValue())) {
                break;
            }
            this.visit(entry.getValue());
            this.visited.add(entry.getValue());
        }

    }

    public void walk() {
        this.visit(start);
    }
}
