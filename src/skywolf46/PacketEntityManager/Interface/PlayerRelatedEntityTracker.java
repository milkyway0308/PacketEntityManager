package skywolf46.PacketEntityManager.Interface;

import org.bukkit.entity.Player;

public interface PlayerRelatedEntityTracker extends EntityTracker{

    void addTrace(Player p, PacketEntity e);

    void removeTrace(Player p, PacketEntity e);

    default void addTrace(PacketEntity e){
        throw new IllegalStateException("PlayerRelatedEntityTracker not supports automatic entity trace");
    }
}
