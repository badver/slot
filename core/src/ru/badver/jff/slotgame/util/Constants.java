package ru.badver.jff.slotgame.util;

public class Constants {
    /**
     *
     */
    public static final String LOADSCREEN  = "images/loadscreen/loadscreen.atlas";
    public static final String PREFERENCES = "slot.pref";
    public static final String TITLE       = "Tropical Party";

    /**
     * virtual screen size
     */
    public static final int VIEWPORT_WIDTH  = 800;
    public static final int VIEWPORT_HEIGHT = 600;

    /**
     * physical screen size
     */
    public static final int SCREEN_WIDTH  = 800;
    public static final int SCREEN_HEIGHT = 600;

    public static final int SYMBOL_WIDTH  = 158; // width of symbols
    public static final int SYMBOL_HEIGHT = 166; // height of symbols

    /**
     * distance between symbols
     */
    public static final int SYMBOL_SHIFT = 166; // shift of symbols

    /**
     * Y coords of symbol lines
     */
    public static final int[] SYMBOL_LINE = {
            SYMBOL_SHIFT * 0,
            SYMBOL_SHIFT * 1,
            SYMBOL_SHIFT * 2,
            SYMBOL_SHIFT * 3,
            SYMBOL_SHIFT * 4};

    /**
     * X coords of symbol columns
     */
    public static final int[] SYMBOL_COLUMN = {9, 165, 322, 478, 634};

    /**
     * time offset between reels to stop
     */
    public static final float REEL_TIME_OFFSET = 0.3f;
}
