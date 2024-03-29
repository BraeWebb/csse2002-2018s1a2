/**
 * A Player who doesn't modify the map.
 *
 * @author JF
 * @serial exclude
 */
public class Explorer extends Player {
    /**
     * Copy details (but not inventory) from another Player.
     *
     * @param player Player to copy from
     */
    public Explorer(Player player) {
        super(player.getShort(), player.getLong(), player.getHealth());
    }

    /**
     * Create an Explorer with default health.
     * @param shortDescription A short name or description for the Explorer
     * @param longDescription A more detailed description for the Explorer
     */
    public Explorer(String shortDescription, String longDescription) {
        super(shortDescription, longDescription);
    }

    /**
     * Create an Explorer with a set health.
     * @param shortDescription A short name or description for the Explorer
     * @param longDescription A more detailed description for the Explorer
     * @param health Initial and Max health for the Explorer.
     */
    public Explorer(String shortDescription, String longDescription, int health) {
        super(shortDescription, longDescription, health);
    }

    @Override
    public String getDescription() {
        return getLong() + (isAlive()
                ? " with " + getHealth() + " health" : "(fainted)");
    }

    /**
     * @return 1
     * @inheritDoc
     */
    @Override
    public int getDamage() {
        return 1;
    }

    public String repr() {
        return String.format("E;%d;%s;%s", getHealth(), getShort(), getLong());
    }

    public static Explorer decode(String encoded) {
        if (encoded == null || encoded.isEmpty()) {
            return null;
        }

        String[] parts = encoded.split(";");

        if (parts.length != 4 || !parts[0].equals("E")) {
            return null;
        }

        int health;
        try {
            health = Integer.decode(parts[1]);
        } catch (NumberFormatException e) {
            return null;
        }
        return new Explorer(parts[2], parts[3], health);
    }
}
