package skywolf46.PacketEntityManager.Interface;


import skywolf46.PacketEntityManager.Objects.GravityEngine.Data.GravityDirection;

public interface GravityEngine {
    void addEntity(PacketEntity e);

    void removeEntity(PacketEntity e);

    boolean isGravityApplied(PacketEntity e);

    GravityDirection getDirection(PacketEntity e);

    double getYAcceleration(PacketEntity e);

    void setYAccerleration(PacketEntity e, double d);

    void stopEngine();

    void startEngine();

    boolean isEngineStarted();

    boolean isFalling(PacketEntity e);
}
