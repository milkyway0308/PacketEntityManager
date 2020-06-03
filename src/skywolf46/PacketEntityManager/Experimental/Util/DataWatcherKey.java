package skywolf46.PacketEntityManager.Experimental.Util;

public abstract class DataWatcherKey<T> {
    public abstract void updateWatcherObject(PacketDataWatcher pdw, T t);

    public abstract T getWatcherObject(PacketDataWatcher pdw);

    public abstract int getBukkitDataWatcherOrdinal();

    public abstract T getDefaultValue();

    public abstract Class getAppliedClass();
}
