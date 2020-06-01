package skywolf46.PacketEntityManager.Objects.GravityEngine.Data;

public class GravityEngineOption {
    private double defaultFallingAccelerate = 0.1d;
    private double acceleratePerTick = 0.01;
    private double maxAccelerate = 1.5;
    // 50ms = 1Tick
    private long tickDelay = 50;
    public GravityEngineOption setMaxAccelerate(double maxAccelerate) {
        this.maxAccelerate = maxAccelerate;
        return this;
    }

    public GravityEngineOption setAcceleratePerTick(double acceleratePerTick) {
        this.acceleratePerTick = acceleratePerTick;
        return this;
    }

    public double getAcceleratePerTick() {
        return acceleratePerTick;
    }

    public double getMaxAccelerate() {
        return maxAccelerate;
    }

    public double getDefaultFallingAccelerate() {
        return defaultFallingAccelerate;
    }

    public GravityEngineOption setDefaultFallingAccelerate(double defaultFallingAccelerate) {
        this.defaultFallingAccelerate = defaultFallingAccelerate;
        return this;
    }

    public long getTickDelay() {
        return tickDelay;
    }

    public GravityEngineOption setTickDelay(long tickDelay) {
        this.tickDelay = tickDelay;
        return this;
    }
}
