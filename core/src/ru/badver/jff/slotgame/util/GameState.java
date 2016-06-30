package ru.badver.jff.slotgame.util;

import com.badlogic.gdx.Gdx;

public class GameState {
    public static final String    TAG      = "GAME STATE ";
    public static final GameState instance = new GameState();
    private States gameState;

    private GameState() {
        gameState = States.START;
    }

    public static GameState getInstance() {
        return instance;
    }

    public States getState() {
        return gameState;
    }

    public void setState(States gameState) {
        // TODO add states verifying
        this.gameState = gameState;
        Gdx.app.debug(TAG, "Game state changed to " + gameState);
    }
}
