package skywolf46.PacketEntityManager.Entity;

import net.minecraft.server.v1_12_R1.AxisAlignedBB;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import skywolf46.PacketEntityManager.Interface.EntityTracker;
import skywolf46.PacketEntityManager.Interface.GravityAppliedEntity;
import skywolf46.PacketEntityManager.Interface.GravityEngine;
import skywolf46.PacketEntityManager.Interface.PacketEntity;
import skywolf46.PacketEntityManager.Util.DataWatcherManager;

import java.util.UUID;

public class PacketItemEntity implements PacketEntity, GravityAppliedEntity {
    private Location loc;
    private int entity_id;
    private UUID uid = UUID.randomUUID();

    @Override
    public void setGravityEngine(GravityEngine engine) {

    }

    @Override
    public GravityEngine getGravityEngine() {
        return null;
    }

    @Override
    public void showEntity(Player p) {

    }

    @Override
    public void hideEntity(Player p) {

    }

    @Override
    public void updateEntity(Player p) {

    }

    @Override
    public void startEventTrace() {

    }

    @Override
    public void stopEventTrace() {

    }

    @Override
    public int getEntityID() {
        return 0;
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public void setLocation(Location l) {

    }

    @Override
    public AxisAlignedBB getBoundingBox() {
        return null;
    }

    @Override
    public void setEntityTracker(EntityTracker tracker) {

    }

    @Override
    public EntityTracker getEntityTracker() {
        return null;
    }

    @Override
    public void broadcastPacket() {

    }

    @Override
    public boolean isRemoved() {
        return false;
    }

    @Override
    public void remove() {

    }

    @Override
    public DataWatcherManager.EntityDataWatcher getDataWatcher() {
        return null;
    }
}
