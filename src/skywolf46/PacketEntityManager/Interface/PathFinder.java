package skywolf46.PacketEntityManager.Interface;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public interface PathFinder {
    void setTarget(Location loc);

    Vector nextPath();

    boolean isPathfindingCompleted();

    void visualizePathFinding(boolean b);

    void cancelPathfinding();

    boolean isPathfinding();
}
