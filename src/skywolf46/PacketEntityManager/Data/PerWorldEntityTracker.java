package skywolf46.PacketEntityManager.Data;

import org.bukkit.World;
import org.bukkit.entity.Player;
import skywolf46.PacketEntityManager.Interface.EntityTracker;
import skywolf46.PacketEntityManager.Interface.PacketEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class PerWorldEntityTracker extends Thread implements EntityTracker {
    private World w;
    private AtomicBoolean isEnabled = new AtomicBoolean(false);
    private boolean threadEnable = false;
    private long sleepTick = 50;
    private int displayRange = 3000;

    public PerWorldEntityTracker(World w, int range) {
        this.w = w;
        this.displayRange = range;
    }

    private ConcurrentHashMap<PacketEntity, EntityTrackingLocker> displayer = new ConcurrentHashMap<>();

    private List<PacketEntity> tracer = new ArrayList<>();

    private final Object LOCK = new Object();

    @Override
    public void run() {
        while (isEnabled.get()) {
            List<Player> target = w.getPlayers();
            List<PacketEntity> tr;
            synchronized (LOCK) {
                tr = new ArrayList<>(tracer);
            }
            for (PacketEntity pe : tr) {
                if (pe.isRemoved()) {
                    synchronized (LOCK) {
                        tracer.remove(pe);
                    }
                    if(displayer.contains(pe)){
                        EntityTrackingLocker lock = displayer.remove(pe);
                        synchronized (lock.LOCK){
                            lock.getListPlayers().forEach(pe::hideEntity);
                        }

                    }
                }
                EntityTrackingLocker lock = displayer.computeIfAbsent(pe, a -> new EntityTrackingLocker());
                synchronized (lock.LOCK) {
                    lock.getListPlayers().removeIf(a -> !a.isOnline() || !a.getWorld().equals(w));
                    for (Player p : target) {
                        if (p.getLocation().distanceSquared(pe.getLocation()) < displayRange) {
                            if (!lock.getListPlayers().contains(p)) {
//                                System.out.println("Wa, sans!");
                                pe.showEntity(p);
                                pe.updateEntity(p);
                                lock.getListPlayers().add(p);
                            }
                        } else lock.getListPlayers().remove(p);
                    }
                }
            }
            try {
                Thread.sleep(sleepTick);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void startTrace() {
        if (isEnabled.get())
            return;
        isEnabled.set(true);
        start();
    }

    @Override
    public void stopTrace() {
//        tracer.remove(p);
        isEnabled.set(false);
    }

    @Override
    public void addTrace(PacketEntity e) {
        synchronized (LOCK) {
            if (!tracer.contains(e))
                tracer.add(e);
        }
    }

    @Override
    public void broadcastUpdate(PacketEntity e) {
        EntityTrackingLocker locker = displayer.computeIfAbsent(e, a -> new EntityTrackingLocker());
        synchronized (locker.LOCK) {
            locker.getListPlayers().forEach(e::updateEntity);
        }
    }

    @Override
    public void removeTrace(PacketEntity e) {
        synchronized (LOCK) {
            tracer.remove(e);
        }
        EntityTrackingLocker locker = displayer.remove(e);
        if (locker != null) {
            synchronized (locker.LOCK) {
                locker.getListPlayers().forEach(e::hideEntity);
            }
        }
    }

    @Override
    public List<Player> getTargetPlayers(PacketEntity e) {
        return null;
    }

    class EntityTrackingLocker {
        private List<Player> lp = new ArrayList<>();
        public final Object LOCK = new Object();

        public List<Player> getListPlayers() {
            return lp;
        }
    }
}
