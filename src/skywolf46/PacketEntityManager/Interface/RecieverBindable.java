package skywolf46.PacketEntityManager.Interface;

import org.bukkit.entity.Player;

public interface RecieverBindable {
    void bind(Player p, int updateTick);
    void unbind(Player p);
}
