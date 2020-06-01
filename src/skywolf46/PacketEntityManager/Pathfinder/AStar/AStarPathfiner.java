package skywolf46.PacketEntityManager.Pathfinder.AStar;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import skywolf46.PacketEntityManager.Interface.PathFinder;

public class  AStarPathfiner implements PathFinder {
    @Override
    public void setTarget(Location loc) {

    }

    @Override
    public Vector nextPath() {
        return null;
    }

    @Override
    public boolean isPathfindingCompleted() {
        return false;
    }

    @Override
    public void visualizePathFinding(boolean b) {

    }

    @Override
    public void cancelPathfinding() {

    }

    @Override
    public boolean isPathfinding() {
        return false;
    }
}
