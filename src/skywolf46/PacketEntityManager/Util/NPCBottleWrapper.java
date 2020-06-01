package skywolf46.PacketEntityManager.Util;

import org.bukkit.entity.Player;

public class NPCBottleWrapper {
    private NPCBottle bottle;
    private Player player;

    public NPCBottleWrapper(NPCBottle bottle, Player target) {
        this.bottle = bottle;
        this.player = target;
    }

    public NPCBottleWrapper respawnPacket(){
        bottle.sendRespawnPacket(player,true);
        return this;
    }

    public NPCBottleWrapper destroyPacket(){
        bottle.sendDestroyPacket(player);
        return this;
    }
//
//    public NPCBottleWrapper addTabList(){
//        bottle.addToTabList(player);
//        return this;
//    }
//
//    public NPCBottleWrapper removeTabList(){
//        bottle.removeFromTabList(player);
//        return this;
//    }

    public NPCBottle getBottle(){
        return bottle;
    }
}
