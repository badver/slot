package ru.badver.jff.slotgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import ru.badver.jff.slotgame.game.GameController;
import ru.badver.jff.slotgame.game.actors.Drum;
import ru.badver.jff.slotgame.game.actors.GameBackgroundActor;
import ru.badver.jff.slotgame.game.actors.GameFrameActor;
import ru.badver.jff.slotgame.util.GameState;
import ru.badver.jff.slotgame.util.States;

public class TestScreen extends AbstractGameScreen {
    private static final String TAG = "TEST SCREEN ";
    private final GameController inputProcessor;

    public TestScreen(DirectedGame game) {
        super(game);
        inputProcessor = new GameController(game);
    }

    @Override
    public InputProcessor getInputProcessor() {
        return inputProcessor;
    }

    @Override
    public void render(float deltaTime) {
        // clear screen
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(deltaTime);
        stage.draw();
    }

    @Override
    public void show() {
        Gdx.app.debug(TAG, "show");
        Gdx.input.setCatchBackKey(true);

        buildStage();

        GameState.instance.setState(States.DEFAULT);
    }

    private void buildStage() {
        GameBackgroundActor backgroundImage = new GameBackgroundActor();
        backgroundImage.setSize(stage.getWidth(), stage.getHeight());

        GameFrameActor gameFrame = new GameFrameActor();
        gameFrame.setSize(stage.getWidth(), stage.getHeight());

        Drum drum = new Drum();

        stage.clear();

        // add background
        stage.addActor(backgroundImage);

        // add drum (contains reel)
        stage.addActor(drum);

        // add frame on top to cover all
        stage.addActor(gameFrame);
    }

    @Override
    public void hide() {
        Gdx.input.setCatchBackKey(false);
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        super.resume();
        Gdx.app.debug(TAG, "resume");
        // Only called on Android!
        paused = false;
    }

    @Override
    public void dispose() {
        super.dispose();
    }


}
