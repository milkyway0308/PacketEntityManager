package skywolf46.PacketEntityManager.Interface;

import org.bukkit.entity.Entity;

public interface BukkitEntityCompatible<T extends Entity> {
    void updateFromBukkitEntity(T entity);
    void updateToBukkitEntity(T entity);
}
