package skywolf46.PacketEntityManager.Enum;

public enum NPCAnimation {
    SWING_MAINHAND(0),SWING_OFFHAND(3),DAMAGED(1),LEAVE_BED(2),
    CRITICAL_DAMAGED(4),MAGIC_CRITIAL_DAMAGED(5);
    NPCAnimation(int numb){
        this.animation = numb;
    }
    private int animation;

    public int getAnimation(){
        return animation;
    }
}
