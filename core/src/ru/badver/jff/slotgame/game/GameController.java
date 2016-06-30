package ru.badver.jff.slotgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import ru.badver.jff.slotgame.screens.DirectedGame;
import ru.badver.jff.slotgame.util.Constants;
import ru.badver.jff.slotgame.util.GameState;
import ru.badver.jff.slotgame.util.States;

/**
 *
 */
public class GameController implements InputProcessor {
    public static final String TAG = "GAME CONTROLLER";
    private final DirectedGame game;

    public GameController(DirectedGame game) {
        this.game = game;
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        switch (i) {
            case Input.Keys.ESCAPE:
                Gdx.app.debug(TAG, "Exit application.");
                Gdx.app.exit();
                break;
            case Input.Keys.SPACE:
                if (GameState.instance.getState() == States.ROLLING) {
                    GameState.instance.setState(States.STOPPING);
                } else {
                    GameState.instance.setState(States.START_ROLLING);
                }
                break;
            case Input.Keys.ENTER:
//                if (GameState.instance.getState() == States.ROLLING) {
//                    GameState.instance.setState(States.STOPPING);
//                } else {
//                    GameState.instance.setState(States.ROLLING);
//                }
                if (!Gdx.graphics.isFullscreen()) {
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayModes()[0]);
                }
                else {
                    Gdx.graphics.setWindowedMode(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i2, int i3, int i4) {
        if (GameState.instance.getState() == States.ROLLING) {
            GameState.instance.setState(States.STOPPING);
        } else {
            GameState.instance.setState(States.START_ROLLING);
        }
        return true;
    }

    @Override
    public boolean touchUp(int i, int i2, int i3, int i4) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i2) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
