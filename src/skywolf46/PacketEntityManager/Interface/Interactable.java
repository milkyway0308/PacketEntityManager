package skywolf46.PacketEntityManager.Interface;

import org.bukkit.entity.Entity;

public interface Interactable {
    void onEntityInteract(Entity e);
    void onPacketEntityInteract(PacketEntity entity);

}
