package ua.com.meraya;


public class Setting {

    public static final boolean DEBUG_GAME = false;
    public static final boolean DEBUG_WORLD = false;

    public static final float GRAVITY = 9;
    public static final float JUMP_IMPULSE = 80;
    public static final float WHEEL_TORQUE = 50;

    public static boolean isMusicPlay = MainEngine.data.getMusicMute();
    public static boolean isSoundPlay = MainEngine.data.getSoundMute();
}
