package skywolf46.PacketEntityManager.Interface;

import org.bukkit.entity.Player;

import java.util.List;

public interface EntityTracker {
    void startTrace();

    void stopTrace();

    void addTrace(PacketEntity e);

    void broadcastUpdate(PacketEntity e);

    void removeTrace(PacketEntity e);

    List<Player> getTargetPlayers(PacketEntity e);
}
