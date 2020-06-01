package skywolf46.PacketEntityManager.Util;

import java.util.concurrent.atomic.AtomicInteger;

public class EntityIDManager {
    private static AtomicInteger CURRENT_ID = new AtomicInteger(50000000);
    private static final Object lock = new Object();

    public static int getNextID() {
        return CURRENT_ID.getAndIncrement();
    }
}
