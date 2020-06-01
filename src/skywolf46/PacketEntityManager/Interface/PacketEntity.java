package skywolf46.PacketEntityManager.Interface;

import net.minecraft.server.v1_12_R1.AxisAlignedBB;
import net.minecraft.server.v1_12_R1.DataWatcher;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import skywolf46.PacketEntityManager.Util.DataWatcherManager;

public interface PacketEntity {
    void showEntity(Player p);

    void hideEntity(Player p);

    void updateEntity(Player p);

    void startEventTrace();

    void stopEventTrace();

    int getEntityID();

    Location getLocation();

    void setLocation(Location l);

    AxisAlignedBB getBoundingBox();

    void setEntityTracker(EntityTracker tracker);

    EntityTracker getEntityTracker();

    void broadcastPacket();

    boolean isRemoved();

    void remove();

    <T extends DataWatcher> DataWatcherManager.EntityDataWatcher getDataWatcher();

}
