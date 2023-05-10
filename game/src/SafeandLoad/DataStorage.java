package SafeandLoad;

import java.io.Serializable;
import java.util.ArrayList;
public class DataStorage implements Serializable {
    private int levelCount;

    ArrayList<String> entities;

    /**
     * Setzt die Anzahl der Level.
     *
     * @param count die Anzahl der Level
     */
    public void setLevelCount(int count) {
        this.levelCount = count;
    }

    /**
     * Setzt die Liste der Entity-Klassen.
     *
     * @param entities eine Liste der Entity-Klassen
     */
    public void setEntities(ArrayList<String> entities) {
        this.entities = entities;
    }

    /**
     * Gibt die Anzahl der Level zurück.
     *
     * @return die Anzahl der Level
     */
    public int getLevelCount() {
        return levelCount;
    }

    /**
     * Gibt die Liste der Entity-Klassen zurück.
     *
     * @return eine Liste der Entity-Klassen
     */
    public ArrayList<String> getEntities() {
        return entities;
    }
}


