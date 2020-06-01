package skywolf46.PacketEntityManager.Util;

import net.minecraft.server.v1_12_R1.DataWatcherObject;
import net.minecraft.server.v1_12_R1.DataWatcherRegistry;
import net.minecraft.server.v1_12_R1.NBTTagCompound;

public class DataWatcherObjectManager {
    public static DataWatcherObject<Integer> intObject(int slot) {
        return new DataWatcherObject<>(slot, DataWatcherRegistry.b);
    }

    public static DataWatcherObject<Boolean> booleanObject(int slot){
        return new DataWatcherObject<>(slot,DataWatcherRegistry.h);
    }

    public static DataWatcherObject<String> stringObject(int slot){
        return new DataWatcherObject<>(slot,DataWatcherRegistry.d);
    }

    public static DataWatcherObject<Float> floatObject(int slot){
        return new DataWatcherObject<>(slot,DataWatcherRegistry.c);
    }

    public static DataWatcherObject<Byte> byteObject(int slot){
        return new DataWatcherObject<>(slot,DataWatcherRegistry.a);
    }

    public static DataWatcherObject<NBTTagCompound> nbtCompoundObject(int slot){
        return new DataWatcherObject<>(slot,DataWatcherRegistry.n);
    }
}
