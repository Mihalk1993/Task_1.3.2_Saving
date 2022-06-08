import java.io.Serializable;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(String name, int health, int weapons, int lvl, double distance) {
        this.name = name;
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    public String getName() {
        return name +  ".dat";
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "name=" + name +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                "}";
    }
}
