package skywolf46.PacketEntityManager.Objects.GravityEngine;


public class DefaultGravityEngine {
//    private HashMap<Integer, GravityDirection> fallingSpeed = new HashMap<>();
//    private HashMap<Integer, Double> fallingDistance = new HashMap<>();
//    private GravityEngineOption option;
//    private List<PacketEntity> tracing = new ArrayList<>();
//    private AtomicBoolean started = new AtomicBoolean(false);
//    private final Object lock = new Object();
//    private Thread engineThread;
//    private static HashMap<World, AsyncWorld> worlds = new HashMap<>();
//
//
//    public DefaultGravityEngine(GravityEngineOption option) {
//        this.option = option;
//        startEngine();
//    }
//
//    @Override
//    public void addEntity(PacketEntity e) {
//        if (tracing.contains(e))
//            return;
//        tracing.add(e);
//    }
//
//    @Override
//    public void removeEntity(PacketEntity e) {
//        fallingSpeed.remove(e.getEntityID());
//        tracing.remove(e.getEntityID());
//    }
//
//    @Override
//    public boolean isGravityApplied(PacketEntity e) {
//        return tracing.contains(e);
//    }
//
//    @Override
//    public GravityDirection getDirection(PacketEntity e) {
//        return fallingSpeed.computeIfAbsent(e.getEntityID(),el -> new GravityDirection());
//    }
//
//    @Override
//    public double getYAcceleration(PacketEntity e) {
//        return getDirection(e).getY();
//    }
//
//    @Override
//    public void setYAccerleration(PacketEntity e, double d) {
//        getDirection(e).setY(d);
//    }
//
//    @Override
//    public void stopEngine() {
//        if(!isEngineStarted())
//            return;
//        synchronized (lock){
//            engineThread.interrupt();
//            engineThread = null;
//            started.set(false);
//        }
//    }
//
//    @Override
//    public void startEngine() {
//        if(isEngineStarted())
//            return;
//        synchronized (lock){
//            started.set(true);
//            engineThread = new Thread(()->{
//               while (true){
//                   try {
////                       System.out.println("Test");
//                       List<PacketEntity> entity;
//                       synchronized (lock){
//                           entity = new ArrayList<>(tracing);
//                       }
//                       entity.forEach(e -> {
//                           GravityDirection gd = getDirection(e);
//                           if(isFalling(e)){
//
//                               gd.setY(Math.min(option.getMaxAccelerate(),gd.getY() - option.getAcceleratePerTick()));
//                           }else if(gd.getY() <= 0)
//                               gd.setY(0);
//                           Location l = e.getLocation();
//                           l.add(gd.getX(),gd.getY(),gd.getZ());
////                           System.out.println("Gravity Y:" + gd.getY());
//                           AsyncWorld world = worlds.computeIfAbsent(l.getWorld(),wld -> new AsyncWorld(wld.getName(),true));
//                           Block b = world.getBlockAt(l);
//                           while (b.getType() != Material.AIR && b.getType().isSolid()){
//                               l.add(0,0.1,0);
//                               b = world.getBlockAt(l);
//                               System.out.println("Type: " + b.getType());
//                           }
//                           e.setLocation(l);
//                       });
//                       Thread.sleep(option.getTickDelay());
//                   } catch (InterruptedException e) {
//                       Bukkit.getConsoleSender().sendMessage("§cSkywolfCoreNavigator §7| §fDefault gravity engine stopped");
//                   }
//               }
//            });
//            engineThread.start();
//            Bukkit.getConsoleSender().sendMessage("§cSkywolfCoreNavigator §7| §aDefault gravity engine started");
//
//        }
//    }
//
//    @Override
//    public boolean isEngineStarted() {
//        return started.get();
//    }
//
//    @Override
//    public boolean isFalling(PacketEntity e) {
//        Location loc = e.getLocation();
//        AsyncWorld world = worlds.computeIfAbsent(loc.getWorld(),wld -> new AsyncWorld(wld.getName(),true));
//        loc.subtract(0,1,0);
//        return !world.getBlockAt(loc).getType().isSolid();
//    }
}
