package skywolf46.PacketEntityManager.Experimental.Util;

import skywolf46.PacketEntityManager.Experimental.Interface.DataWatcherListener;

import java.util.HashMap;
import java.util.Objects;

public class PacketDataWatcher {
    private HashMap<Integer, Object> watcher = new HashMap<>();
    private DataWatcherListener dwl;

    public PacketDataWatcher(DataWatcherListener dwl) {
        setListener(dwl);
    }

    public void setListener(DataWatcherListener listener) {
        this.dwl = listener;
    }

    public void updateDataWatcher(DataWatcherKey dk, Object t) {
        Object bef = watcher.get(dk.getBukkitDataWatcherOrdinal());
        if (!Objects.equals(bef, t)) {
            watcher.put(dk.getBukkitDataWatcherOrdinal(), t);
            if (dwl != null)
                dwl.listen(this, dk.getBukkitDataWatcherOrdinal(), dk);
        }
    }


    public <T> T getData(DataWatcherKey<T> key) {
        return watcher.containsKey(key.getBukkitDataWatcherOrdinal()) ? (T) watcher.get(key.getBukkitDataWatcherOrdinal()) : null;
    }
}
