package skywolf46.PacketEntityManager.Util;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.util.CraftChatMessage;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.introspector.Property;
import skywolf46.PacketEntityManager.BukkitEvent.PacketEntityEvent;
import skywolf46.PacketEntityManager.Interface.*;
import skywolf46.PacketEntityManager.Interface.EntityTracker;
import skywolf46.PacketEntityManager.LifeInABottle;
import skywolf46.PacketEntityManager.Objects.GravityEngine.Data.GravityDirection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NPCBottle implements PacketEntity, BukkitEntityCompatible<Player>, EventTraceable, GravityAppliedEntity, HeadRotatable, Interactable,PathFindable,RecieverBindable {
    private int id;
    private Location loc;
    private String displayName;
    private String tabListName;
    private GameProfile profile;
    private DataWatcher watcher;
    private DataWatcherObject<Byte> entityState;
    private DataWatcherObject<String> name;
    private DataWatcherObject<Boolean> isSilent;
    private DataWatcherObject<Boolean> gravity;
    private DataWatcherObject<Boolean> nameVisible;
    private static int id_starting = 1000000;
    private GravityEngine engine;
    private List<Player> displayer = new ArrayList<>();
    public NPCBottle(String name, Location loc) {
        this.displayName = name;
        this.profile = new GameProfile(UUID.randomUUID(), displayName);
        this.loc = loc;
        this.id = id_starting++;
        this.watcher = new DataWatcher(null);
        this.watcher.register(this.entityState = new DataWatcherObject<>(0, DataWatcherRegistry.a), (byte) 0);
        this.watcher.register(new DataWatcherObject<>(1, DataWatcherRegistry.b), 300);
        this.watcher.register(this.name = new DataWatcherObject<>(2, DataWatcherRegistry.d), "");
        this.watcher.register(nameVisible = new DataWatcherObject<>(3, DataWatcherRegistry.h), true);
        this.watcher.register(this.isSilent = new DataWatcherObject<>(4, DataWatcherRegistry.h), false);
        this.watcher.register(this.gravity = new DataWatcherObject<>(5, DataWatcherRegistry.h), false);
        this.watcher.register(new DataWatcherObject<>(6, DataWatcherRegistry.a), (byte) 0);
        this.watcher.register(new DataWatcherObject<>(7, DataWatcherRegistry.c), 20.0F);
        this.watcher.register(new DataWatcherObject<>(8, DataWatcherRegistry.b), 0);
        this.watcher.register(new DataWatcherObject<>(9, DataWatcherRegistry.h), false);
        this.watcher.register(new DataWatcherObject<>(10, DataWatcherRegistry.b), 0);
        this.watcher.register(new DataWatcherObject<>(11, DataWatcherRegistry.c), 0.0F);
        this.watcher.register(new DataWatcherObject<>(12, DataWatcherRegistry.b), 20);
        this.watcher.register(new DataWatcherObject<>(13, DataWatcherRegistry.a), (byte) 127);
        this.watcher.register(new DataWatcherObject<>(14, DataWatcherRegistry.a), (byte) 1);
        this.watcher.register(new DataWatcherObject<>(15, DataWatcherRegistry.n), new NBTTagCompound());
        this.watcher.register(new DataWatcherObject<>(16, DataWatcherRegistry.n), new NBTTagCompound());
        LifeInABottle.INSTANCE.attachNPC(this);
    }

    public Property getSkin() {
        if (getProfile().getProperties().isEmpty())
            return null;
        return (Property) getProfile().getProperties().get("textures").toArray()[0];
    }

    public GameProfile getProfile() {
        return profile;
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

    public int getEntityID() {
        return id;
    }

    public Location getLocation() {
        return loc.clone();
    }

    public void setLocation(Location l) {
        this.loc = l.clone();
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
    public <T extends DataWatcher> DataWatcherManager.ArmorStandDataWatcher getDataWatcher() {
        return null;
    }

    public void addDisplayer(Player p){
        displayer.add(p);
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String name) {
        this.displayName = name;
    }

    public void setTabListName(String tabListName) {
        this.tabListName = tabListName;
    }

    public String getTabListName() {
        return tabListName;
    }

    public NPCBottle sendRespawnPacket(Player p, boolean headRotation) {
        addToTabList(p);
        PacketPlayOutNamedEntitySpawn packet = new PacketPlayOutNamedEntitySpawn();
        this.setField(packet, "a", getEntityID());
        this.setField(packet, "b", getProfile().getId());
        this.setField(packet, "c", loc.getX());
        this.setField(packet, "d", loc.getY());
        this.setField(packet, "e", loc.getZ());
        this.setField(packet, "f", headRotation ? (byte) ((int) loc.getYaw() * 256.0F / 360.0F) : 0);
        this.setField(packet, "g", headRotation ? (byte) ((int) loc.getPitch() * 256.0F / 360.0F) : 0);
        this.setField(packet, "h", this.watcher);

        sendPacket(p, packet);
        PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport();
        this.setField(teleport,"a",getEntityID());
        this.setField(teleport, "b", loc.getX());
        this.setField(teleport, "c", loc.getY());
        this.setField(teleport, "d", loc.getZ());
        this.setField(teleport,"e", headRotation ? (byte) ((int) loc.getYaw() * 256.0F / 360.0F) : 0);
        this.setField(teleport,"f",headRotation ? (byte) ((int) loc.getPitch() * 256.0F / 360.0F) : 0);
        this.setField(teleport,"g",false);
        sendPacket(p,teleport);
        removeFromTabList(p);
        return this;
    }

    public void sleep(boolean sleep){
        if(sleep){
            PacketPlayOutBed bed = new PacketPlayOutBed();
        }else{
            PacketPlayOutAnimation animation = new PacketPlayOutAnimation();
        }

    }


    public void relativeMove(GravityDirection dir){

//        PacketPlayOutEntity.PacketPlayOutRelEntityMove rel = new PacketPlayOutEntity.PacketPlayOutRelEntityMove(
//                getEntityID(),dir
//        )
    }

    public void reposition(Player p){
        addToTabList(p);
        PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport();
        this.setField(teleport,"a",getEntityID());
        this.setField(teleport, "b", loc.getX());
        this.setField(teleport, "c", loc.getY());
        this.setField(teleport, "d", loc.getZ());
        this.setField(teleport,"e", (byte) ((int) loc.getYaw() * 256.0F / 360.0F));
        this.setField(teleport,"f",(byte) ((int) loc.getPitch() * 256.0F / 360.0F));
        this.setField(teleport,"g",false);
        sendPacket(p,teleport);
        removeFromTabList(p);
    }

    @Override
    public float getYaw() {
        return 0;
    }

    @Override
    public float getPitch() {
        return 0;
    }

    @Override
    public HeadRotatable setYaw(float yaw) {
        return null;
    }

    @Override
    public HeadRotatable setPitch(float pitch) {
        return null;
    }

    public HeadRotatable look(Location l){

        return this;
    }

    @Override
    public boolean supportDirectRotate() {
        return false;
    }

    public NPCBottle sendDestroyPacket(Player p) {
        PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(getEntityID());
        sendPacket(p, destroy);
        return this;
    }

    public NPCBottle addToTabList(Player p){
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
        PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(getProfile(), 0,
                EnumGamemode.NOT_SET, CraftChatMessage.fromString(displayName)[0]);
        List<PacketPlayOutPlayerInfo.PlayerInfoData> players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getField(packet, "b");
        players.add(data);
        this.setField(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER);
        this.setField(packet, "b", players);
        this.sendPacket(p,packet);
        return this;
    }

    public NPCBottle removeFromTabList(Player p) {
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
        PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(getProfile(), 0,
                EnumGamemode.NOT_SET, CraftChatMessage.fromString(displayName)[0]);
        List<PacketPlayOutPlayerInfo.PlayerInfoData> players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getField(packet, "b");
        players.add(data);
        this.setField(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER);
        this.setField(packet, "b", players);
        this.sendPacket(p,packet);
        return this;
    }

    public void destroy() {
        LifeInABottle.INSTANCE.unattachNPC(this);
    }

    private void sendPacket(Player entity, Packet packet) {
        ((CraftPlayer) entity).getHandle().playerConnection.sendPacket(packet);
    }

    private void setField(Object obj, String field_name, Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(field_name);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object getField(Object obj, String field_name) {
        try {
            Field field = obj.getClass().getDeclaredField(field_name);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object createInstance(Class c, Object[] args, Class<?>[] param) {
        try {
            return c.getConstructor(param).newInstance(args);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateFromBukkitEntity(Player entity) {

    }

    @Override
    public void updateToBukkitEntity(Player entity) {

    }

    @Override
    public <T extends PacketEntityEvent> void listenEventDirectly(T e) {

    }

    @Override
    public void setGravityEngine(GravityEngine engine) {
        if(this.engine != null)
            this.engine.removeEntity(this);
        this.engine = engine;
        engine.addEntity(this);
    }

    @Override
    public GravityEngine getGravityEngine() {
        return engine;
    }

    @Override
    public void onEntityInteract(Entity e) {

    }

    @Override
    public void onPacketEntityInteract(PacketEntity entity) {

    }

    @Override
    public void setPathfinder(PathFinder pathfinder) {

    }

    @Override
    public PathFinder getPathfinder() {
        return null;
    }

    @Override
    public void bind(Player p, int updateTick) {

    }

    @Override
    public void unbind(Player p) {

    }
}
