package skywolf46.PacketEntityManager.Interface;

import org.bukkit.Location;

public interface HeadRotatable {
    float getYaw();
    float getPitch();
    HeadRotatable setYaw(float yaw);
    HeadRotatable setPitch(float pitch);
    HeadRotatable look(Location next);
    boolean supportDirectRotate();
}
