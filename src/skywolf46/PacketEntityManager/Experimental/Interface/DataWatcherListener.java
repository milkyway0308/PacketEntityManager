package skywolf46.PacketEntityManager.Experimental.Interface;

import skywolf46.PacketEntityManager.Experimental.Util.DataWatcherKey;
import skywolf46.PacketEntityManager.Experimental.Util.PacketDataWatcher;

public interface DataWatcherListener {
    void listen(PacketDataWatcher dw, int bukkitOrdinal, DataWatcherKey dk);
}
