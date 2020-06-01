package skywolf46.PacketEntityManager.Entity;


import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import skywolf46.PacketEntityManager.Data.PacketEntityEquipment;
import skywolf46.PacketEntityManager.Enum.NPCAnimation;
import skywolf46.PacketEntityManager.Interface.*;
import skywolf46.PacketEntityManager.Interface.EntityTracker;
import skywolf46.PacketEntityManager.LifeInABottle;
import skywolf46.PacketEntityManager.Util.DataWatcherManager;
import skywolf46.PacketEntityManager.Util.EntityIDManager;
import skywolf46.PacketEntityManager.Util.ReflectUtil;

import java.util.UUID;

public class PacketArmorStandEntity implements PacketEntity, Animatable, HeadRotatable, ItemEquipable {
    private Location loc;
    private int entity_id;
    private UUID uid = UUID.randomUUID();
    private DataWatcherManager.ArmorStandDataWatcher watcher;
    private EntityTracker tracker;
    private PacketEntityEquipment equipment = new PacketEntityEquipment(this);
    private boolean removed = false;

    public PacketArmorStandEntity(Location loc, String name) {
        this.entity_id = EntityIDManager.getNextID();
        this.loc = loc;
        watcher = (DataWatcherManager.ArmorStandDataWatcher) DataWatcherManager.createDataWatcher(ArmorStand.class);
        watcher.setCustomName(name);
        watcher.setCustomNameVisible(true);
    }

    @Override
    public void animate(NPCAnimation animation) {

    }


    @Override
    public float getYaw() {
        return loc.getYaw();
    }

    @Override
    public float getPitch() {
        return loc.getPitch();
    }

    @Override
    public PacketArmorStandEntity setYaw(float yaw) {
        loc.setYaw(yaw);
        return this;
    }

    @Override
    public PacketArmorStandEntity setPitch(float pitch) {
        loc.setPitch(pitch);
        return this;
    }

    @Override
    public HeadRotatable look(Location next) {
        return null;
    }

    @Override
    public boolean supportDirectRotate() {
        return false;
    }

    @Override
    public void showEntity(Player p) {
        PacketPlayOutSpawnEntityLiving spawnPacket = new PacketPlayOutSpawnEntityLiving();
        try {
            Class c = spawnPacket.getClass();
            ReflectUtil.getField(c, "a").set(spawnPacket, entity_id);
            ReflectUtil.getField(c, "b").set(spawnPacket, uid);
            ReflectUtil.getField(c, "c").set(spawnPacket, EntityTypes.b.a(EntityArmorStand.class));

            // X/Y/Z
//            ReflectUtil.getField(c, "d").set(spawnPacket, MathHelper.a(loc.getX(), -3.0E7D, -3.0E7D));
//            ReflectUtil.getField(c, "e").set(spawnPacket, loc.getY());
//            ReflectUtil.getField(c, "f").set(spawnPacket, MathHelper.a(loc.getZ(), -3.0E7D, -3.0E7D));
            ReflectUtil.getField(c, "d").set(spawnPacket, loc.getX());
            ReflectUtil.getField(c, "e").set(spawnPacket, loc.getY());
            ReflectUtil.getField(c, "f").set(spawnPacket, loc.getZ());

            // Motion X/Y/Z
            ReflectUtil.getField(c, "g").set(spawnPacket, (int) 0);
            ReflectUtil.getField(c, "h").set(spawnPacket, (int) 0);
            ReflectUtil.getField(c, "i").set(spawnPacket, (int) 0);
            // Yaw/Pitch
            ReflectUtil.getField(c, "j").set(spawnPacket, (byte) ((int) (loc.getYaw() * 256.0f / 360.0f)));
            ReflectUtil.getField(c, "k").set(spawnPacket, (byte) ((int) (loc.getPitch() * 256.0f / 360.0f)));

            // Head Rotation!
            ReflectUtil.getField(c, "l").set(spawnPacket, (byte) ((int) (loc.getYaw() * 256.0f / 360.0f)));
            // Data Watcher
            ReflectUtil.getField(c, "m").set(spawnPacket, watcher.getWatcher());
            PlayerConnection pc = ((CraftPlayer) p).getHandle().playerConnection;
            pc.sendPacket(spawnPacket);
            for (Packet pac : equipment.createEquipmentPacket(this))
                pc.sendPacket(pac);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // https://mitny.tistory.com/208
    // SQL 감사.. 압도적 감사
    @Override
    public void hideEntity(Player p) {
        PacketPlayOutEntityDestroy destPacket = new PacketPlayOutEntityDestroy(entity_id);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(destPacket);
    }

    @Override
    public void updateEntity(Player p) {
//        System.out.println("Update : " + p.getName());
        PacketPlayOutEntityMetadata meta = new PacketPlayOutEntityMetadata(getEntityID(), watcher.getWatcher(), true);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(meta);
        PlayerConnection pc = ((CraftPlayer) p).getHandle().playerConnection;
        for (Packet pac : equipment.createEquipmentPacket(this))
            pc.sendPacket(pac);
    }

    public void teleportPacket(Player p) {
        PacketPlayOutEntityTeleport tp = new PacketPlayOutEntityTeleport();
        try {
            Class c = tp.getClass();
            ReflectUtil.getField(c,"a").set(tp,getEntityID());
            ReflectUtil.getField(c,"b").set(tp,loc.getX());
            ReflectUtil.getField(c,"c").set(tp,loc.getY());
            ReflectUtil.getField(c,"d").set(tp,loc.getZ());
            ReflectUtil.getField(c,"e").set(tp,(byte)((int)(loc.getYaw() * 256f / 360f)));
            ReflectUtil.getField(c,"f").set(tp,(byte)((int)(loc.getPitch() * 256f / 360f)));
            ReflectUtil.getField(c,"g").set(tp,false);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(tp);
        } catch (Exception ex) {

        }
    }

    @Override
    public void startEventTrace() {
        LifeInABottle.INSTANCE.attachNPC(this);
    }

    @Override
    public void stopEventTrace() {
        LifeInABottle.INSTANCE.unattachNPC(this);

    }

    @Override
    public int getEntityID() {
        return entity_id;
    }

    @Override
    public Location getLocation() {
        return new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
    }

    @Override
    public void setLocation(Location loc) {
        this.loc = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
//        broadcastPacket();
        tracker.getTargetPlayers(this)
                .forEach(this::teleportPacket);
    }

    @Override
    public AxisAlignedBB getBoundingBox() {
        return null;
    }

    @Override
    public void setEntityTracker(EntityTracker tracker) {
        this.tracker = tracker;
    }

    @Override
    public EntityTracker getEntityTracker() {
        return tracker;
    }

    @Override
    public void broadcastPacket() {
        if (tracker != null)
            tracker.broadcastUpdate(this);
    }

    @Override
    public boolean isRemoved() {
        return removed;
    }

    @Override
    public void remove() {
        removed = true;
    }


    public void setSmall(boolean b) {
        watcher.setSmall(b);
        broadcastPacket();
    }

    public boolean isSmall() {
        return watcher.isSmall();
    }

    public void setBasePlate(boolean b) {
        watcher.setBasePlace(b);
        broadcastPacket();
    }

    public void setInvisible(boolean b) {
        watcher.setInvisible(b);
        broadcastPacket();
    }


    @Override
    public PacketEntityEquipment getEquipment() {
        return equipment;
    }

    public Vector3f getHeadPose() {
        return watcher.getHeadPose();
    }

    public void setHeadPose(Vector3f vsf) {
        watcher.setHeadPose(vsf);
        broadcastPacket();
    }

    public void test() {
        watcher.setArrowCount(400);
    }

    @Override
    public <T extends DataWatcher> DataWatcherManager.ArmorStandDataWatcher getDataWatcher() {
        return watcher;
    }
}
