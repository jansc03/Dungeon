package room;

import java.util.LinkedHashSet;
import level.elements.tile.DoorTile;
import level.elements.tile.Tile;
import levelgraph.LevelNode;

public interface IRoom {
    /**
     * @return all DoorTiles in this room
     */
    LinkedHashSet<DoorTile> getDoors();

    Tile[][] getLayout();

    LevelNode getLevelNode();
}
