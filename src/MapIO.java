public class MapIO {

    public static Thing decodeThing(String encoded, Room room) {
        return null;
    }

    public static Room deserializeMap(String filename) {
        return null;
    }

    public static Object[] loadMap(String filename) {
        return null;
    }

    public static boolean saveMap(Room room, String filename) {
        return false;
    }

    public static boolean serializeMap(Room room, String filename) {
        return false;
    }

    public static void main(String[] args) {
        Room r1 = new Room("#1");
        Room r2 = new Room("#2");
        r2.enter(new Critter("frog", "a frog", 1, 5));
        r1.enter(new Explorer("doris", "a doris"));
        try {
            r1.addExit("North", r2);
            r2.addExit("South", r1);
        } catch (ExitExistsException ee) {
        } catch (NullRoomException nr) {
        }
        MapIO.saveMap(r1, "mymap");
    }

}
