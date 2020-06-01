package skywolf46.PacketEntityManager.Data;

import net.minecraft.server.v1_12_R1.EnumItemSlot;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityEquipment;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import skywolf46.PacketEntityManager.Interface.PacketEntity;

public class PacketEntityEquipment {
    private ItemStack MAIN_HAND = new ItemStack(Material.AIR);
    private ItemStack SUB_HAND = new ItemStack(Material.AIR);
    private ItemStack HELMET = new ItemStack(Material.AIR);
    private ItemStack CHESTPLATE = new ItemStack(Material.AIR);
    private ItemStack LEGGINGS = new ItemStack(Material.AIR);
    private ItemStack BOOTS = new ItemStack(Material.AIR);
    private PacketEntity inst;

    public PacketEntityEquipment(PacketEntity et) {
        this.inst = et;
    }

    public ItemStack getMainHand() {
        return MAIN_HAND;
    }

    public ItemStack getSubHand() {
        return SUB_HAND;
    }

    public ItemStack getHelmet() {
        return HELMET;
    }

    public ItemStack getChestPlate() {
        return CHESTPLATE;
    }

    public ItemStack getLeggings() {
        return LEGGINGS;
    }

    public ItemStack getBoots() {
        return BOOTS;
    }

    public PacketEntityEquipment setMainHand(ItemStack item) {
        if (item == null)
            item = new ItemStack(Material.AIR);
        MAIN_HAND = item;
        if(inst.getEntityTracker() != null)
            inst.getEntityTracker().broadcastUpdate(inst);
        return this;
    }

    public PacketEntityEquipment setSubHand(ItemStack item) {
        if (item == null)
            item = new ItemStack(Material.AIR);
        SUB_HAND = item;
        if(inst.getEntityTracker() != null)
            inst.getEntityTracker().broadcastUpdate(inst);
        return this;
    }


    public PacketEntityEquipment setHelmet(ItemStack item) {
        if (item == null)
            item = new ItemStack(Material.AIR);
        HELMET = item;
        if(inst.getEntityTracker() != null)
            inst.getEntityTracker().broadcastUpdate(inst);
        return this;
    }


    public PacketEntityEquipment setChestPlate(ItemStack item) {
        if (item == null)
            item = new ItemStack(Material.AIR);
        CHESTPLATE = item;
        if(inst.getEntityTracker() != null)
            inst.getEntityTracker().broadcastUpdate(inst);
        return this;
    }


    public PacketEntityEquipment setLeggings(ItemStack item) {
        if (item == null)
            item = new ItemStack(Material.AIR);
        LEGGINGS = item;
        if(inst.getEntityTracker() != null)
            inst.getEntityTracker().broadcastUpdate(inst);
        return this;
    }


    public PacketEntityEquipment setBoots(ItemStack item) {
        if (item == null)
            item = new ItemStack(Material.AIR);
        BOOTS = item;
        if(inst.getEntityTracker() != null)
            inst.getEntityTracker().broadcastUpdate(inst);
        return this;
    }


    public PacketPlayOutEntityEquipment[] createEquipmentPacket(PacketEntity e) {
        PacketPlayOutEntityEquipment[] packets = new PacketPlayOutEntityEquipment[]{
                new PacketPlayOutEntityEquipment(
                        e.getEntityID(), EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(getMainHand())),
                new PacketPlayOutEntityEquipment(
                        e.getEntityID(), EnumItemSlot.OFFHAND, CraftItemStack.asNMSCopy(getSubHand())),
                new PacketPlayOutEntityEquipment(
                        e.getEntityID(), EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(getHelmet())),
                new PacketPlayOutEntityEquipment(
                        e.getEntityID(), EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(getChestPlate())),
                new PacketPlayOutEntityEquipment(
                        e.getEntityID(), EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(getLeggings())),
                new PacketPlayOutEntityEquipment(
                        e.getEntityID(), EnumItemSlot.FEET, CraftItemStack.asNMSCopy(getBoots()))
        };
        return packets;
    }

    public PacketPlayOutEntityEquipment createEquipment(PacketEntity e, EnumItemSlot slot) {
        switch (slot) {
            case MAINHAND:
                return new PacketPlayOutEntityEquipment(
                        e.getEntityID(), EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(getMainHand()));
            case OFFHAND:
                return new PacketPlayOutEntityEquipment(
                        e.getEntityID(), EnumItemSlot.OFFHAND, CraftItemStack.asNMSCopy(getSubHand()));
            case FEET:
                return new PacketPlayOutEntityEquipment(
                        e.getEntityID(), EnumItemSlot.FEET, CraftItemStack.asNMSCopy(getBoots()));
            case LEGS:
                return new PacketPlayOutEntityEquipment(
                        e.getEntityID(), EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(getLeggings()));
            case CHEST:
                return new PacketPlayOutEntityEquipment(
                        e.getEntityID(), EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(getChestPlate()));
            case HEAD:
                return new PacketPlayOutEntityEquipment(
                        e.getEntityID(), EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(getHelmet()));
        }
        return null;
    }


}
