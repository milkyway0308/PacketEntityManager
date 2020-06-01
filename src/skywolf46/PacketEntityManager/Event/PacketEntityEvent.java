package skywolf46.PacketEntityManager.Event;


import skywolf46.PacketEntityManager.Interface.PacketEntity;

public abstract class PacketEntityEvent {
    private PacketEntity entity;

    public PacketEntityEvent(PacketEntity target){
        this.entity = target;
    }
}
