package skywolf46.PacketEntityManager.Experimental.Util.PacketDataWatcherRegistry;

import net.minecraft.server.v1_12_R1.Entity;
import skywolf46.PacketEntityManager.Experimental.Util.DataWatcherKey;
import skywolf46.PacketEntityManager.Experimental.Util.PacketDataWatcher;

public class MC112Registry {
    private static class EntityFlagWatcher extends DataWatcherKey<Byte> {

        @Override
        public void updateWatcherObject(PacketDataWatcher pdw, Byte aByte) {
            pdw.updateDataWatcher(this, aByte);
        }

        @Override
        public Byte getWatcherObject(PacketDataWatcher pdw) {
            return pdw.getData(this);
        }

        @Override
        public int getBukkitDataWatcherOrdinal() {
            return 0;
        }

        @Override
        public Byte getDefaultValue() {
            return (byte) 0;
        }

        @Override
        public Class getAppliedClass() {
            return Entity.class;
        }
    }

    private static class NoGravityWatcher extends DataWatcherKey<Boolean> {

        @Override
        public void updateWatcherObject(PacketDataWatcher pdw, Boolean aBoolean) {
            pdw.updateDataWatcher(this, aBoolean);
        }

        @Override
        public Boolean getWatcherObject(PacketDataWatcher pdw) {
            return pdw.getData(this);
        }

        @Override
        public int getBukkitDataWatcherOrdinal() {
            return 5;
        }

        @Override
        public Boolean getDefaultValue() {
            return false;
        }

        @Override
        public Class getAppliedClass() {
            return Entity.class;
        }
    }

    private static class CustomNameWatcher extends DataWatcherKey<String> {

        @Override
        public void updateWatcherObject(PacketDataWatcher pdw, String s) {
            pdw.updateDataWatcher(this, s);
        }

        @Override
        public String getWatcherObject(PacketDataWatcher pdw) {
            return pdw.getData(this);
        }

        @Override
        public int getBukkitDataWatcherOrdinal() {
            return 2;
        }

        @Override
        public String getDefaultValue() {
            return "";
        }

        @Override
        public Class getAppliedClass() {
            return Entity.class;
        }
    }

    private static class CustomNameVisibleWatcher extends DataWatcherKey<Boolean> {

        @Override
        public void updateWatcherObject(PacketDataWatcher pdw, Boolean aBoolean) {
            pdw.updateDataWatcher(this, aBoolean);
        }

        @Override
        public Boolean getWatcherObject(PacketDataWatcher pdw) {
            return pdw.getData(this);
        }

        @Override
        public int getBukkitDataWatcherOrdinal() {
            return 3;
        }

        @Override
        public Boolean getDefaultValue() {
            return false;
        }

        @Override
        public Class getAppliedClass() {
            return Entity.class;
        }
    }

    public static class AirTicksWatcher extends DataWatcherKey<Integer> {

        @Override
        public void updateWatcherObject(PacketDataWatcher pdw, Integer integer) {
            pdw.updateDataWatcher(this, integer);
        }

        @Override
        public Integer getWatcherObject(PacketDataWatcher pdw) {
            return pdw.getData(this);
        }

        @Override
        public int getBukkitDataWatcherOrdinal() {
            return 1;
        }

        @Override
        public Integer getDefaultValue() {
            return 0;
        }

        @Override
        public Class getAppliedClass() {
            return Entity.class;
        }
    }

    private static class IsSilientWatcher extends DataWatcherKey<Boolean> {

        @Override
        public void updateWatcherObject(PacketDataWatcher pdw, Boolean aBoolean) {
            pdw.updateDataWatcher(this, aBoolean);
        }

        @Override
        public Boolean getWatcherObject(PacketDataWatcher pdw) {
            return pdw.getData(this);
        }

        @Override
        public int getBukkitDataWatcherOrdinal() {
            return 4;
        }

        @Override
        public Boolean getDefaultValue() {
            return false;
        }

        @Override
        public Class getAppliedClass() {
            return Entity.class;
        }
    }

}
