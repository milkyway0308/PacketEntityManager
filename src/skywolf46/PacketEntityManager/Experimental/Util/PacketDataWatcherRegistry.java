package skywolf46.PacketEntityManager.Experimental.Util;

public class PacketDataWatcherRegistry {
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
    }
}
