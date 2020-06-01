package skywolf46.PacketEntityManager.Util;

import net.minecraft.server.v1_12_R1.DataWatcherObject;
import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.World;

public class FakeWatcherEntity extends Entity {

    public FakeWatcherEntity(World world) {
        super(world);
    }


    @Override
    protected void i() {

    }

    @Override
    protected void a(NBTTagCompound nbtTagCompound) {

    }

    @Override
    protected void b(NBTTagCompound nbtTagCompound) {

    }

    @Override
    public void a(DataWatcherObject o){

    }
}
