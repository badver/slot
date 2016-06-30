package ru.badver.jff.slotgame.util;

/**
 *
 */
public enum ReelState {
    STOP, // no move
    ROLL, // rolling
    BEGIN_ROLL, // just before rolling, prepare Reel
    BEGIN_STOP, // just before stopping, prepare Stop
    STOPPING // finish rolling (do time delay, effects, etc.)
}
