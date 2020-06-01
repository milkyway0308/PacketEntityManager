package skywolf46.PacketEntityManager.Data;

public class DefaultEntitySightType {
    private float yaw = 0f;
    private float pitch = 0f;

    public float getYaw(){
        return yaw;
    }

    public float getPitch(){
        return pitch;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public boolean supportDirectSetting(){
        return true;
    }
}
