package ru.badver.jff.slotgame.util;

/**
 * Created by wish on 23.02.14.
 */
public enum States {
    START, // just started
    LOADING, // load assets
    LOADED, // finish loading
    PAUSED,
    DEFAULT,  // main game
    START_ROLLING, // before roll
    ROLLING, // rolling the reels
    STOPPING, // stopping reels
    RISK,  // risk game
    BONUS  // bonus game
}
