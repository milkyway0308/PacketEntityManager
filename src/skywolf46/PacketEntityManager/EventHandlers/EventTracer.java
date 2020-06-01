package skywolf46.PacketEntityManager.EventHandlers;


import skywolf46.PacketEntityManager.BukkitEvent.PacketEntityEvent;

import java.util.HashMap;

public class EventTracer {
    private static HashMap<Class<? extends PacketEntityEvent>,EventTracer> tracing = new HashMap<>();

}
