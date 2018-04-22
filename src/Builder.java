/**
 * Player type which can dig new rooms and avoid fights.
 * Note: instances of this class are linked to a particular start room.
 *
 * @author JF
 * @serial exclude
 */
public class Builder extends Player {

    // Start room for this map
    private Room root;

    /**
     * Base constructor for Builder.
     *
     * @param shortDescription Short name for this builder
     * @param longDescription  Longer description for this builder
     * @param root      Start room for this map
     */
    public Builder(String shortDescription, String longDescription, Room root) {
        super(shortDescription, longDescription);
        this.root = root;
    }

    /**
    * @return 1000
    * @inheritDoc
    */
    @Override
    public int getDamage() {
        return 1000;
    }

    /**
     * Attempt to damage this Mob. Note: for this type, it will be ignored
     *
     * @param amount amount of damage 
     */
    @Override
    public void takeDamage(int amount) {
    }

    /**
     * Create a new exit to the specified Room.
     *
     * @param destination   Room to connect to
     * @param direction     North/South/East/West
     * @throws DigException if there is already a Room in that position or the
     *         direction is unavailable.
     * @throws NullPointerException if either argument is null
     */
    public void digExit(Room destination, String direction) 
            throws DigException {
        if (destination == null || direction == null) {
            throw new NullPointerException();
        }
        try {
            root.addExit(direction, destination);
        } catch (ExitExistsException | NullRoomException e) {
            throw new DigException();
        }
    }

    public String repr() {
        return String.format("B;%s;%s", getShort(), getLong());
    }

    public static Builder decode(String encoded, Room root) {
        if (encoded == null || root == null || encoded.isEmpty()) {
            return null;
        }

        String[] parts = encoded.split(";");

        if (parts.length != 3) {
            return null;
        }

        if (!parts[0].equals("B")) {
            return null;
        }

        return new Builder(parts[1], parts[2], root);
    }

}
