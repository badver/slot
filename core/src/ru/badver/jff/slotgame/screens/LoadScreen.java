package ru.badver.jff.slotgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.badver.jff.slotgame.game.assets.Assets;
import ru.badver.jff.slotgame.screens.transitions.ScreenTransition;
import ru.badver.jff.slotgame.screens.transitions.ScreenTransitionFade;
import ru.badver.jff.slotgame.util.AudioManager;
import ru.badver.jff.slotgame.util.Constants;
import ru.badver.jff.slotgame.util.GameState;
import ru.badver.jff.slotgame.util.States;

public class LoadScreen extends AbstractGameScreen {

    private static final String TAG = "LOAD SCREEN ";
    private TextureAtlas textureAtlas;
    private AssetManager loadScreenAssetManager;
    private float        loadPercent;

    public LoadScreen(DirectedGame game, AssetManager loadScreenAssetManager) {
        super(game);
        this.loadScreenAssetManager = loadScreenAssetManager;
        textureAtlas = loadScreenAssetManager.get("images/loadscreen/loadscreen.atlas", TextureAtlas.class);
    }

    private void loadGameAssets() {
        Assets.instance.init(new AssetManager());
    }

    @Override
    public InputProcessor getInputProcessor() {
        return null;
    }

    @Override
    public void render(float deltaTime) {
        // clear screen
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // get progress of loading asserts
        if (loadPercent != 1) {
            loadPercent = Assets.instance.getProgress();
            Gdx.app.debug(TAG, "Game assets loaded, % " + loadPercent * 100);
        }

        stage.act(deltaTime);
        stage.draw();

        // if finish loading assets, set new screen
        if (loadPercent == 1 && GameState.getInstance().getState() == States.LOADING) {
            GameState.instance.setState(States.LOADED);
            Assets.instance.finishInit();
            ScreenTransition transition = ScreenTransitionFade.init(5.0f);
            game.setScreen(new TestScreen(game), transition);
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportHeight = height; //set the viewport
        camera.viewportWidth = width;

        if (Constants.VIEWPORT_WIDTH / camera.viewportWidth < Constants.VIEWPORT_HEIGHT / camera.viewportHeight) {
            //set the right zoom direct
            camera.zoom = Constants.VIEWPORT_HEIGHT / camera.viewportHeight;
        } else {
            //set the right zoom direct
            camera.zoom = Constants.VIEWPORT_WIDTH / camera.viewportWidth;
        }
        camera.update();
    }

    @Override
    public void show() {

        // place layers
        buildStage();

        // background music on Load Screen
        Music loadMusic = loadScreenAssetManager.get("music/music.ogg", Music.class);
        AudioManager.instance.play(loadMusic);

        // first time loading
        if (GameState.instance.getState() == States.START) {
            loadGameAssets();
        }
        GameState.instance.setState(States.LOADING);
    }

    @Override
    public void hide() {
        stage.dispose();
        textureAtlas.dispose();
        loadScreenAssetManager.dispose();
    }

    @Override
    public void pause() {
        // TODO what to do if pause?
    }

    @Override
    public void resume() {
        Gdx.app.debug(TAG, "resume");
        if (GameState.instance.getState() != States.LOADING) {
            super.resume();
        }
    }

    private void buildStage() {
        // Layers
        LoadBackground backgroundImage = new LoadBackground();

        LoadBar loadBarImage = new LoadBar();
        loadBarImage.setPosition(stage.getWidth() / 2 - loadBarImage.getWidth() / 2, 3);

        LoadBarFill loadBarFill = new LoadBarFill(loadBarImage.getWidth() - 30);
        loadBarFill.setPosition(loadBarImage.getX() + 15, loadBarImage.getY() + 10);

        stage.clear();

        // add layers
        stage.addActor(backgroundImage);
        stage.addActor(loadBarImage);
        stage.addActor(loadBarFill);
    }


    private class LoadBackground extends Actor {
        private TextureRegion loadBg;

        public LoadBackground() {
            super();
            loadBg = new TextureRegion(textureAtlas.findRegion("loadscreen"));
            setSize(loadBg.getRegionWidth(), loadBg.getRegionHeight());
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);
            batch.draw(loadBg, 0, 0);
        }
    }

    private class LoadBar extends Actor {
        private TextureRegion imgLoadbar;

        public LoadBar() {
            super();
            imgLoadbar = new TextureRegion(textureAtlas.findRegion("loadbar"));
            setSize(imgLoadbar.getRegionWidth(), imgLoadbar.getRegionHeight());
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);
            batch.draw(imgLoadbar, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
                    getScaleY(), getRotation());
        }
    }

    private class LoadBarFill extends Actor {
        float width;
        private TextureRegion imgLoadbarFill;

        public LoadBarFill(float width) {
            super();
            this.width = width;
            imgLoadbarFill = new TextureRegion(textureAtlas.findRegion("loadelement"));
            setSize(imgLoadbarFill.getRegionWidth(), imgLoadbarFill.getRegionHeight());
        }

        @Override
        public void act(float delta) {
            super.act(delta);
            setWidth(width * loadPercent);
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);
            batch.draw(imgLoadbarFill, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
                    getScaleY(), getRotation());
        }
    }
}
