package skywolf46.PacketEntityManager.BukkitEvent;

import org.bukkit.event.Event;
import skywolf46.PacketEntityManager.Interface.PacketEntity;

public abstract class PacketEntityEvent extends Event {
    private PacketEntity entity;

    public PacketEntityEvent(PacketEntity pe){
        this.entity = pe;
    }

    public PacketEntity getPacketEntity() {
        return entity;
    }

}
