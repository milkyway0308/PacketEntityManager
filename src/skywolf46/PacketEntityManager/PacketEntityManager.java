package skywolf46.PacketEntityManager;

import org.bukkit.plugin.java.JavaPlugin;

public class PacketEntityManager extends JavaPlugin {
    private static PacketEntityManager inst;

    public static PacketEntityManager inst() {
        return inst;
    }

    @Override
    public void onEnable() {
        inst = this;
        LifeInABottle.INSTANCE.initialize();
    }
}
