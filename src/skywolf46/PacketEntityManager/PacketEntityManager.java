package skywolf46.PacketEntityManager;

import org.bukkit.plugin.java.JavaPlugin;
import skywolf46.PacketEntityManager.Util.DataWatcherManager;
import skywolf46.PacketEntityManager.Util.DataWatcherObjectManager;
import skywolf46.PacketEntityManager.Util.DataWatcherUtil;

public class PacketEntityManager extends JavaPlugin {
    private static PacketEntityManager inst;

    public static PacketEntityManager inst() {
        return inst;
    }

    @Override
    public void onEnable() {
        inst = this;
        LifeInABottle.INSTANCE.initialize();
//        System.out.println();
        DataWatcherManager.EntityDataWatcher.a();
    }
}
