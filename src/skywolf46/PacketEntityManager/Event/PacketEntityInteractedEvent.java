package skywolf46.PacketEntityManager.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import skywolf46.PacketEntityManager.Interface.PacketEntity;

public class PacketEntityInteractedEvent extends PlayerEvent implements Cancellable {

    private static HandlerList handlerList = new HandlerList();
    private boolean isCancelled = false;
    private PacketEntity entity;
    private boolean isRightClick;

    public PacketEntityInteractedEvent(Player who,PacketEntity entity,boolean isRightClick) {
        super(who);
        this.entity = entity;
        this.isRightClick = isRightClick;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public PacketEntity getPacketEntity() {
        return entity;
    }

    public boolean isRightClick(){
        return isRightClick;
    }

    public boolean isLeftClick(){
        return !isRightClick;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled = b;
    }
}
