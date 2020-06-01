package skywolf46.PacketEntityManager;

import com.comphenix.packetwrapper.WrapperPlayClientUseEntity;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import net.minecraft.server.v1_12_R1.Vector3f;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import skywolf46.PacketEntityManager.Entity.PacketArmorStandEntity;
import skywolf46.PacketEntityManager.Event.PacketEntityInteractedEvent;
import skywolf46.PacketEntityManager.Interface.PacketEntity;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class LifeInABottle  {
    public static final LifeInABottle INSTANCE = new LifeInABottle();

    private LifeInABottle() {

    }

    private AtomicBoolean isInit = new AtomicBoolean(false);
    private HashMap<Integer, PacketEntity> npcList = new HashMap<>();


    public void initialize() {
        if (isInitialized())
            return;
        isInit.set(true);
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new PacketAdapter(PacketEntityManager.inst(), ListenerPriority.NORMAL, PacketType.Play.Client.USE_ENTITY) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                WrapperPlayClientUseEntity wrap = new WrapperPlayClientUseEntity(event.getPacket());
                if (npcList.containsKey(wrap.getTargetID())) {
                    PacketEntityInteractedEvent ev = new PacketEntityInteractedEvent(event.getPlayer(), npcList.get(wrap.getTargetID()), wrap.getMouse() == EnumWrappers.EntityUseAction.ATTACK);
                    Bukkit.getPluginManager().callEvent(ev);
                    if (ev.isCancelled())
                        event.setCancelled(true);
                }
            }
        });
//        GravityEngine engine = new DefaultGravityEngine(new GravityEngineOption().setTickDelay(1).setAcceleratePerTick(0.0001));
//        MinecraftAbstractCommand.builder()
//                .command("/test1")
//                .add(new MinecraftAbstractCommand() {
//                    @Override
//                    public boolean onCommand(CommandArgument commandArgument) {
//                        CommandData cd = commandArgument.get(CommandData.class);
//                        NPCBottle bottle = new NPCBottle(cd.getCommandArgument(0), commandArgument.get(Player.class).getLocation());
//                        NPCBottleWrapper wrap = new NPCBottleWrapper(bottle, commandArgument.get(Player.class));
//                        Bukkit.getScheduler().scheduleSyncRepeatingTask(CoreNavigator.getInstance(), () -> {
//                            bottle.sendRespawnPacket(commandArgument.get(Player.class), true);
//                        }, 1L, 1L);
//                        bottle.setGravityEngine(engine);
//                        return false;
//                    }
//
//                    @Override
//                    public int getCommandPriority() {
//                        return 0;
//                    }
//                }).complete();
//
//        MinecraftAbstractCommand.builder()
//                .command("/tes2")
//                .add(new MinecraftAbstractCommand() {
//                    @Override
//                    public boolean onCommand(CommandArgument commandArgument) {
//                        CommandData cd = commandArgument.get(CommandData.class);
//                        Player p = commandArgument.get(Player.class);
//                        NPC npc = new NPC(cd.getCommandArgument(0), p.getLocation(), CoreNavigator.getInstance());
//                        npc.addRecipient(p);
//                        npc.spawn(true, true);
//                        return false;
//                    }
//
//                    @Override
//                    public int getCommandPriority() {
//                        return 0;
//                    }
//                }).complete();
//
//
//        MinecraftAbstractCommand.builder()
//                .command("/test3")
//                .add(new MinecraftAbstractCommand() {
//                    @Override
//                    public boolean onCommand(CommandArgument commandArgument) {
//                        CommandData cd = commandArgument.get(CommandData.class);
//                        Player p = commandArgument.get(Player.class);
//                        WorldServer sv = ((CraftWorld) p.getWorld()).getHandle();
//                        System.out.println(sv.getClass().getName());
//                        EntityHuman test = new EntityPlayer(((CraftServer) Bukkit.getServer()).getServer(),
//                                ((CraftWorld) p.getWorld()).getHandle(),
//                                new GameProfile(p.getUniqueId(), p.getName()),
//                                new PlayerInteractManager(((CraftWorld) p.getWorld()).getHandle()));
//                        PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(test);
//                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(spawn);
//                        return false;
//                    }
//
//                    @Override
//                    public int getCommandPriority() {
//                        return 0;
//                    }
//                }).complete();
//        MinecraftAbstractCommand.builder()
//                .command("/아머스탠드")
//                .add(new MinecraftAbstractCommand() {
//                    @Override
//                    public boolean onCommand(CommandArgument commandArgument) {
//                        CommandData cd = commandArgument.get(CommandData.class);
//                        Player p = commandArgument.get(Player.class);
//                        PacketArmorStandEntity armor;
//                        if (cd.length() == 0) {
//                            armor = new PacketArmorStandEntity(p.getLocation(), "§a테스트 아머스탠드");
//                        } else {
//                            armor = new PacketArmorStandEntity(p.getLocation(), ChatColor.translateAlternateColorCodes('&', cd.getCommandArgument(0, cd.length())));
//                        }
//
//                        armor.setSmall(true);
//                        armor.setBasePlate(false);
//                        armor.setInvisible(true);
//                        armor.startEventTrace();
//                        armor.showEntity(p);
//
//                        return false;
//                    }
//
//                    @Override
//                    public int getCommandPriority() {
//                        return 0;
//                    }
//                }).complete();
//        PerEntityTracker tracker = new PerEntityTracker(600);
//        tracker.startTrace();
//        List<PacketArmorStandEntity> rotater = new ArrayList<>();
//        List<PacketArmorStandEntity> test = new ArrayList<>();
//
//        Bukkit.getScheduler().scheduleSyncRepeatingTask(CoreNavigator.getInstance(), new Runnable() {
//            @Override
//            public void run() {
//                for (PacketArmorStandEntity el : rotater) {
//                    Vector3f vsf = el.getHeadPose();
//                    float x = vsf.getX();
//                    float y = vsf.getY() + 3f;
//                    float z = vsf.getZ();
//                    if (y >= 360.0f)
//                        y = 0f;
//                    el.setHeadPose(new Vector3f(
//                            x, y, z
//                    ));
//                }
//            }
//        }, 1L, 1L);
//        MinecraftAbstractCommand.builder()
//                .command("/테스트")
//                .add(new MinecraftAbstractCommand() {
//                    @Override
//                    public boolean onCommand(CommandArgument commandArgument) {
//                        CommandData cd = commandArgument.get(CommandData.class);
//                        Player p = commandArgument.get(Player.class);
//                        testTracingKnife test = new testTracingKnife();
//                        test.entity = new PacketArmorStandEntity(p.getLocation(),"");
//                        test.target = p;
//                        test.entity.setEntityTracker(tracker);
//                        test.entity.setInvisible(true);
//                        test.entity.getEquipment().setHelmet(new ItemStack(Material.WOOD_HOE));
//                        tracker.addTrace(p,test.entity);
//                        Bukkit.getScheduler().scheduleSyncRepeatingTask(CoreNavigator.getInstance(), test::retarget,1L,1L);
//
//                        return false;
//                    }
//
//                    @Override
//                    public int getCommandPriority() {
//                        return 0;
//                    }
//                }).complete();
    }

    class testTracingKnife {
        private PacketArmorStandEntity entity;
        private Player target;
        private Vector lastVector;
        private int leftTick = 0;
        private int waitTick = 0;

        public void retarget() {
            System.out.println(waitTick);
            System.out.println(leftTick);
            if (waitTick-- > 0)
                return;
            if (leftTick-- > 0 && lastVector != null) {
                entity.setLocation(entity.getLocation().add(entity.getLocation().getDirection().multiply(1.2)));
                if (entity.getLocation().distanceSquared(target.getLocation()) < 0.3)
                    target.damage(2);
                return;
            }
            leftTick = 15;
            waitTick = 5;
            lastVector = target.getLocation().toVector().subtract(entity.getLocation().toVector());
            Location l = entity.getLocation();
            l.setDirection(lastVector);
            entity.setLocation(l);
            Vector3f vec = new Vector3f((float) Math.toRadians(l.getYaw()), (float) Math.toRadians(l.getPitch()), 0);
            entity.setHeadPose(vec);
        }

    }

//    @Override
    public boolean isInitialized() {
        return isInit.get();
    }

//    @Override
    public void load() {

    }

    public void attachNPC(PacketEntity npc) {
        npcList.put(npc.getEntityID(), npc);
    }

    public void unattachNPC(PacketEntity npc) {
        npcList.remove(npc.getEntityID());
    }
}
