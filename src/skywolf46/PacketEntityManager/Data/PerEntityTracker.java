package skywolf46.PacketEntityManager.Data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import skywolf46.PacketEntityManager.Interface.PacketEntity;
import skywolf46.PacketEntityManager.Interface.PlayerRelatedEntityTracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class PerEntityTracker extends Thread implements PlayerRelatedEntityTracker {
    private AtomicBoolean isEnabled = new AtomicBoolean(false);
    private boolean threadEnable = false;
    private HashMap<Integer, PacketEntity> entity = new HashMap<>();

    private HashMap<Player, List<PacketEntity>[]> displayable = new HashMap<>();
    private HashMap<Integer, List<Player>> visibilityToPlayer = new HashMap<>();
    private double displayDistance = 3000;
    private long sleepTicks = 50;

    public PerEntityTracker(double displayDistance) {
        this.displayDistance = displayDistance;
    }

    @Override
    public void run() {
        threadEnable = true;
        while (isEnabled.get()) {
            synchronized (lock) {
                displayable.entrySet().removeIf(pl -> {
                    if (!pl.getKey().isOnline()) {
                        pl.getValue()[0].forEach(
                                e -> visibilityToPlayer.computeIfAbsent(e.getEntityID(), a -> new ArrayList<>()).remove(pl.getKey())
                        );
                        pl.getValue()[0].clear();
                        pl.getValue()[1].clear();
                        return true;
                    }
                    List<PacketEntity> displayable = pl.getValue()[0];
                    List<PacketEntity> currentVisible = pl.getValue()[1];
                    displayable.removeIf(e -> {
                        if (e.isRemoved()) {
                            currentVisible.remove(e);
                            visibilityToPlayer.remove(e.getEntityID());
                            return true;
                        }
                        Location loc = e.getLocation();
                        if (!loc.getWorld().equals(pl.getKey().getWorld())) {
                            if (currentVisible.contains(e)) {
                                e.hideEntity(pl.getKey());
                                currentVisible.remove(e);
//                                visibilityToPlayer.computeIfAbsent(e.getEntityID(),
//                                        el -> new ArrayList<>()
//                                ).remove(pl.getKey());
                            }
                            return false;
                        }
                        if (loc.distanceSquared(pl.getKey().getLocation()) >= displayDistance) {
                            e.hideEntity(pl.getKey());
                            currentVisible.remove(e);
//                            visibilityToPlayer.computeIfAbsent(e.getEntityID(),
//                                    el -> new ArrayList<>()
//                            ).remove(pl.getKey());
                        } else {
                            if (!currentVisible.contains(e)) {
                                currentVisible.add(e);
                                e.showEntity(pl.getKey());
//                                visibilityToPlayer.computeIfAbsent(e.getEntityID(),
//                                        el -> new ArrayList<>()
//                                ).add(pl.getKey());
                            }
//                            else {
//                                e.updateEntity(pl.getKey());
//                            }
                        }
                        return false;
                    });
                    return false;
                });
            }
            try {
                Thread.sleep(sleepTicks);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadEnable = false;
    }

    private final Object lock = new Object();


    @Override
    public void startTrace() {
        if (threadEnable)
            return;
        isEnabled.set(true);
        Bukkit.getConsoleSender().sendMessage("§cSkywolfCoreNavigator §7| §7Starting entity tracer...");

        start();
        Bukkit.getConsoleSender().sendMessage("§cSkywolfCoreNavigator §7| §7Default entity tracer started");

    }

    @Override
    public void stopTrace() {
        isEnabled.set(false);
    }

    @Override
    public void addTrace(Player p, PacketEntity e) {
        synchronized (lock) {
            entity.put(e.getEntityID(), e);
            List<PacketEntity> pe = displayable.computeIfAbsent(p, lp -> new List[]{
                    new ArrayList<>(),
                    new ArrayList<>()
            })[0];
            if (pe.contains(e))
                return;
            pe.add(e);
            visibilityToPlayer.computeIfAbsent(e.getEntityID(), n -> new ArrayList<>()).add(p);
        }
    }

    @Override
    public void removeTrace(Player p, PacketEntity e) {
        synchronized (lock) {
            entity.put(e.getEntityID(), e);
            List<PacketEntity> pe = displayable.computeIfAbsent(p, lp -> new List[]{
                    new ArrayList<>(),
                    new ArrayList<>()
            })[0];
            pe.remove(e);
            e.hideEntity(p);
        }
    }

    @Override
    public void broadcastUpdate(PacketEntity e) {
        synchronized (lock) {
            if (e.isRemoved()) {
                visibilityToPlayer.remove(e.getEntityID());
                return;
            }
            visibilityToPlayer.computeIfAbsent(e.getEntityID(), el -> new ArrayList<>()).forEach(e::updateEntity);
        }
    }

    @Override
    public void removeTrace(PacketEntity e) {

    }

    @Override
    public List<Player> getTargetPlayers(PacketEntity e) {
//        System.out.println("Targeting player : " + visibilityToPlayer.get(e.getEntityID()));
      synchronized (lock){
          return visibilityToPlayer.getOrDefault(e.getEntityID(), new ArrayList<>());
      }
    }
}
