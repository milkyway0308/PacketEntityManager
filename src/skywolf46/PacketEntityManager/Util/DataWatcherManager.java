package skywolf46.PacketEntityManager.Util;

import com.google.common.base.Optional;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Supplier;

import static skywolf46.PacketEntityManager.Util.DataWatcherObjectManager.*;
import static skywolf46.PacketEntityManager.Util.DataWatcherUtil.*;

public class DataWatcherManager {
    public static final FakeWatcherEntity FAKE_DATAWATCHER_ENTITY = new FakeWatcherEntity(null);
    private static HashMap<Class, Supplier<DataWatcherWrapper>> watcherSupplier = new HashMap<>();

    static {
        registerDataWatcherSupplier(Player.class, PlayerDataWatcher::new);
        registerDataWatcherSupplier(ArmorStand.class, ArmorStandDataWatcher::new);
    }

    public static DataWatcherWrapper createDataWatcher(Class key) {
        if (!watcherSupplier.containsKey(key))
            return null;
        DataWatcherWrapper wrapper = watcherSupplier.get(key).get();
        wrapper.initialize();
        return wrapper;
    }

    public static void registerDataWatcherSupplier(Class c, Supplier<DataWatcherWrapper> supplier) {
        watcherSupplier.put(c, supplier);
    }

    public static abstract class DataWatcherWrapper {
        private DataWatcher watcher = new DataWatcher(FAKE_DATAWATCHER_ENTITY);

        public DataWatcher getWatcher() {
            return watcher;
        }

        abstract void initialize();
    }

    public static abstract class EntityDataWatcher extends DataWatcherWrapper {
        private static DataWatcherObject<Boolean> isNoGravity;
        private static DataWatcherObject<String> customName;
        private static DataWatcherObject<Boolean> customNameVisibleWatcher;
        private static DataWatcherObject<Integer> airTicksWatcher;
        private static DataWatcherObject<Boolean> isSilientWatcher;
        private static DataWatcherObject<Byte> handRaisedWatcher;
        private static DataWatcherObject<Float> entityHealthWatcher;
        private static DataWatcherObject<Integer> displayPotionEffectWatcher;
        private static DataWatcherObject<Boolean> isPotionEffectAmbientWatcher;
        private static DataWatcherObject<Integer> arrowCountWatcher;


        static {
            try {
                Class c = Entity.class;
                isNoGravity = ReflectUtil.getField(c.getDeclaredField("aE"));
                customName = ReflectUtil.getField(c.getDeclaredField("aB"));
                customNameVisibleWatcher = ReflectUtil.getField(c.getDeclaredField("aC"));
                airTicksWatcher = ReflectUtil.getField(c.getDeclaredField("aA"));
                isSilientWatcher = ReflectUtil.getField(c.getDeclaredField("aD"));
                c = EntityLiving.class;
                handRaisedWatcher = ReflectUtil.getField(c.getDeclaredField("at"));
                entityHealthWatcher = ReflectUtil.getField(c.getDeclaredField("HEALTH"));
                displayPotionEffectWatcher = ReflectUtil.getField(c.getDeclaredField("g"));
                isPotionEffectAmbientWatcher = ReflectUtil.getField(c.getDeclaredField("h"));
                arrowCountWatcher = ReflectUtil.getField(c.getDeclaredField("br"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        final void initialize() {
            getWatcher().register(getFlagWatcher(), (byte) 0);
            getWatcher().register(customNameWatcher(), "Entity");
            getWatcher().register(isNoGravityWatcher(), true);
            getWatcher().register(customNameVisibleWatcher(), false);
            getWatcher().register(airTicksWatcher(), 300);
            getWatcher().register(isSilientWatcher(), false);
            getWatcher().register(handRaisedWatcher(), (byte) 0);
            getWatcher().register(entityHealthWatcher(), 20f);
            getWatcher().register(displayPotionEffectWatcher(), 0);
            getWatcher().register(isPotionEffectAmbientWatcher(), false);
            getWatcher().register(arrowCountWatcher(), 0);
            setOnFire(false);
            initWatcher();
        }

        abstract void initWatcher();

        protected DataWatcherObject<Boolean> isNoGravityWatcher() {
            return isNoGravity;
        }

        protected DataWatcherObject<String> customNameWatcher() {
            return customName;
        }

        protected DataWatcherObject<Boolean> customNameVisibleWatcher() {
            return customNameVisibleWatcher;
        }

        protected DataWatcherObject<Integer> airTicksWatcher() {
            return airTicksWatcher;
        }

        protected DataWatcherObject<Boolean> isSilientWatcher() {
            return isSilientWatcher;
        }

        protected DataWatcherObject<Byte> handRaisedWatcher() {
            return handRaisedWatcher;
        }

        protected DataWatcherObject<Float> entityHealthWatcher() {
            return entityHealthWatcher;
        }

        protected DataWatcherObject<Integer> displayPotionEffectWatcher() {
            return displayPotionEffectWatcher;
        }

        protected DataWatcherObject<Boolean> isPotionEffectAmbientWatcher() {
            return isPotionEffectAmbientWatcher;
        }

        protected DataWatcherObject<Integer> arrowCountWatcher() {
            return arrowCountWatcher;
        }

        public void setOnFire(boolean b) {
            setFlag(getWatcher(), 0, b);
        }

        public boolean isOnFire() {
            return getFlag(getWatcher(), 0);
        }

        public void setArrowCount(int c){
            getWatcher().set(arrowCountWatcher(),c);
        }

        public boolean isGlowing(){
            return getFlag(getWatcher(),6);
        }

        public void setGlowing(boolean b){
            setFlag(getWatcher(),6,b);
        }
    }

    public static class ItemDataWatcher extends EntityDataWatcher {
        private static DataWatcherObject<ItemStack> itemWatcher;

        static {
            try {
                itemWatcher = (DataWatcherObject<ItemStack>) ReflectUtil.getField(EntityItem.class, "c").get(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        private ItemStack item = CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.STONE));

        @Override
        void initWatcher() {
            getWatcher().register(itemWatcher, item);
        }

        public org.bukkit.inventory.ItemStack getItemStack() {
            return CraftItemStack.asBukkitCopy(getWatcher().get(itemWatcher));
        }

        public void setItemStack(org.bukkit.inventory.ItemStack item) {
            getWatcher().set(itemWatcher, CraftItemStack.asNMSCopy(item));
        }
    }

    public static class TameableAnimalWatcher extends EntityDataWatcher {
        private static DataWatcherObject<Byte> current_flag;
        private static DataWatcherObject<Optional<UUID>> ownerID;

        static {
            try {
                current_flag = (DataWatcherObject<Byte>) ReflectUtil.getField(EntityTameableAnimal.class, "bx").get(null);
                ownerID = (DataWatcherObject<Optional<UUID>>) ReflectUtil.getField(EntityTameableAnimal.class, "by").get(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        public boolean hasOwner() {
            return getOwner() != null;
        }

        public UUID getOwner() {
            return getWatcher().get(ownerID).orNull();
        }

        public void setOwner(UUID uid) {
            getWatcher().set(ownerID, Optional.fromNullable(uid));
        }

        public boolean isTamed() {
            return (getWatcher().get(current_flag) & 4) != 0;
        }

        public void setTamed(boolean isTamed) {
            if (isTamed)
                getWatcher().set(current_flag, (byte) (getWatcher().get(current_flag) | 4));
            else
                getWatcher().set(current_flag, (byte) (getWatcher().get(current_flag) & -5));
            // next = dm()
        }

        public boolean isSitting() {
            return (getWatcher().get(current_flag) & 1) != 0;
        }

        public void setSitting(boolean isSitting) {
            if (isSitting)
                getWatcher().set(current_flag, (byte) (getWatcher().get(current_flag) | 1));
            else
                getWatcher().set(current_flag, (byte) (getWatcher().get(current_flag) & -2));
        }

        @Override
        void initWatcher() {
            getWatcher().register(current_flag, 0);
            getWatcher().register(ownerID, Optional.absent());
        }
    }

    public static class PlayerDataWatcher extends DataWatcherWrapper {
        private DataWatcherObject<Byte> entityState;
        private DataWatcherObject<String> name;
        private DataWatcherObject<Boolean> isSilent;
        private DataWatcherObject<Boolean> hasGravity;
        private DataWatcherObject<Boolean> isNameVisible;
        private GameProfile profile = new GameProfile(UUID.randomUUID(), "Player");

        @Override
        void initialize() {
            getWatcher().register(entityState = new DataWatcherObject<>(0, DataWatcherRegistry.a), (byte) 0);
            // Ping?
            getWatcher().register(intObject(1), 300);
            getWatcher().register(stringObject(2), "");
            getWatcher().register(isNameVisible = booleanObject(3), true);
            getWatcher().register(isSilent = booleanObject(4), false);
            getWatcher().register(hasGravity = booleanObject(5), false);
            getWatcher().register(byteObject(6), (byte) 0);
            getWatcher().register(floatObject(7), 20.0f);
            getWatcher().register(intObject(8), 0);
            getWatcher().register(booleanObject(9), false);
            getWatcher().register(intObject(10), 0);
            getWatcher().register(floatObject(11), 0.0f);
            getWatcher().register(intObject(12), 20);
            getWatcher().register(byteObject(13), (byte) 127);
            getWatcher().register(byteObject(14), (byte) 1);
            getWatcher().register(nbtCompoundObject(15), new NBTTagCompound());
            getWatcher().register(nbtCompoundObject(16), new NBTTagCompound());
        }

        public Property getSkin() {
            if (profile.getProperties().isEmpty())
                return null;
            return (Property) profile.getProperties().get("textures").toArray()[0];
        }

        public void setName(String name) {
            if (name.length() > 16)
                throw new IllegalArgumentException("Name cannot longer than 16 characters");
            getWatcher().register(this.name, name);
        }

    }


    public static class ArmorStandDataWatcher extends EntityDataWatcher {
        private static DataWatcherObject<Byte> flagWatcher = null;
        private static DataWatcherObject<Vector3f> headPose = null; // b
        private static DataWatcherObject<Vector3f> bodyPose = null; //c
        private static DataWatcherObject<Vector3f> leftArmPose = null; //d
        private static DataWatcherObject<Vector3f> rightArmPose = null; //e
        private static DataWatcherObject<Vector3f> leftLegPose = null; //f
        private static DataWatcherObject<Vector3f> rightLegPose = null; //g

        private static final Vector3f defaultHeadPose = new Vector3f(0.0f, 0.0f, 0.0f);
        private static final Vector3f defaultBodyPose = new Vector3f(0.0f, 0.0f, 0.0f);
        private static final Vector3f defaultLeftArmPose = new Vector3f(-10.0f, 0.0f, -10.0f);
        private static final Vector3f defaultRightArmPose = new Vector3f(-15.0f, 0.0f, 10.0f);
        private static final Vector3f defaultLeftLegPose = new Vector3f(-1.0f, 0.0f, -1.0f);
        private static final Vector3f defaultRightLegPose = new Vector3f(1.0f, 0.0f, 1.0f);

        static {
            try {
                Class c = EntityArmorStand.class;
                flagWatcher = ReflectUtil.getField(c.getDeclaredField("a"));
                headPose = ReflectUtil.getField(c.getDeclaredField("b"));
                bodyPose = ReflectUtil.getField(c.getDeclaredField("c"));
                leftArmPose = ReflectUtil.getField(c.getDeclaredField("d"));
                rightArmPose = ReflectUtil.getField(c.getDeclaredField("e"));
                leftLegPose = ReflectUtil.getField(c.getDeclaredField("f"));
                rightLegPose = ReflectUtil.getField(c.getDeclaredField("g"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        void initWatcher() {
            getWatcher().register(flagWatcher, (byte) 0);
            getWatcher().register(headPose, defaultHeadPose);
            getWatcher().register(bodyPose, defaultBodyPose);
            getWatcher().register(leftArmPose, defaultLeftArmPose);
            getWatcher().register(rightArmPose, defaultRightArmPose);
            getWatcher().register(leftLegPose, defaultLeftLegPose);
            getWatcher().register(rightLegPose, defaultRightLegPose);
            setInvisible(false);
        }

        public void setHeadPose(Vector3f vector) {
            getWatcher().set(headPose, vector);
        }

        public Vector3f getHeadPose() {
            return getWatcher().get(headPose);
        }

        public String getCustomName() {
            return getWatcher().get(customNameWatcher());
        }

        public boolean isInvisible() {
            return getFlag(getWatcher(), 5);
        }

        public boolean hasBasePlate() {
            return !getFlag(getWatcher(), flagWatcher, 8);
        }

        public boolean hasArms() {
            return getFlag(getWatcher(), flagWatcher, 4);
        }

        public boolean isNoClip() {
            return getWatcher().get(isNoGravityWatcher());
        }

        public boolean isSmall() {
            return getFlag(getWatcher(), flagWatcher, 1);
        }

        public boolean isMarker() {
            return getFlag(getWatcher(), flagWatcher, 16);
        }

        public void setInvisible(boolean b) {
            setFlag(getWatcher(), 5, b);
        }

        public void setBasePlace(boolean exist) {
            setFlag(getWatcher(), flagWatcher, 8, !exist);
        }

        public void setArms(boolean exists) {
            setFlag(getWatcher(), flagWatcher, 4, exists);
        }

        public void setMarker(boolean isMarker) {
            setFlag(getWatcher(), flagWatcher, 16, isMarker);
        }

        public void setNoClip(boolean noClip) {
            getWatcher().set(isNoGravityWatcher(), noClip);
        }

        public void setSmall(boolean b) {
            setFlag(getWatcher(), flagWatcher, 1, b);
        }

        public void setCustomName(String name) {
            getWatcher().set(customNameWatcher(), name);
        }

        public void setCustomNameVisible(boolean b) {
            getWatcher().set(customNameVisibleWatcher(), b);
        }


    }


}
