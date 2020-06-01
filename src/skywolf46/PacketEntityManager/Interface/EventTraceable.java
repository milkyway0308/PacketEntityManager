package skywolf46.PacketEntityManager.Interface;


import skywolf46.PacketEntityManager.Annotation.TraceableEventListener;
import skywolf46.PacketEntityManager.BukkitEvent.PacketEntityEvent;

@TraceableEventListener
public interface EventTraceable {
    <T extends PacketEntityEvent> void listenEventDirectly(T e);

}
