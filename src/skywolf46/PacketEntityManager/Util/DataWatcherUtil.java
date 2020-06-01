package skywolf46.PacketEntityManager.Util;

import net.minecraft.server.v1_12_R1.DataWatcher;
import net.minecraft.server.v1_12_R1.DataWatcherObject;
import net.minecraft.server.v1_12_R1.Entity;

public class DataWatcherUtil {
    private static DataWatcherObject<Byte> flagWatcher;
    public static boolean getFlag(DataWatcher watcher,int flagNum){
        initWatcher();
        return (watcher.get(flagWatcher) & 1 << flagNum) != 0;
    }

    public static boolean getFlag(DataWatcher watcher,DataWatcherObject<Byte> flagWatcher,int flagNum){
        return (watcher.get(flagWatcher) & flagNum) != 0;
    }

    public static void setFlag(DataWatcher watcher,int flagNum,boolean flag){
        byte lastFlag = watcher.get(flagWatcher);
        if(flag){
            watcher.set(flagWatcher,(byte)(lastFlag | 1 << flagNum));
        }else{
            watcher.set(flagWatcher,(byte)(lastFlag & ~(1 << flagNum)));
        }
    }


    public static void setFlag(DataWatcher watcher,DataWatcherObject<Byte> flagWatcher,int flagNum,boolean flag){
        byte lastFlag = watcher.get(flagWatcher);
        if(flag){
            watcher.set(flagWatcher,(byte)(lastFlag | flagNum));
        }else{
            watcher.set(flagWatcher,(byte)(lastFlag & ~(flagNum)));
        }
    }

    public static DataWatcherObject<Byte> getFlagWatcher() {
        initWatcher();
        return flagWatcher;
    }

    private static void initWatcher() {
        if(flagWatcher == null){
            try {
                Class c = Entity.class;
                flagWatcher = ReflectUtil.getField(c.getDeclaredField("Z"));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
