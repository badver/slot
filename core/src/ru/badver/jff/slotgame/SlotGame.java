
package ru.badver.jff.slotgame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import ru.badver.jff.slotgame.screens.DirectedGame;
import ru.badver.jff.slotgame.screens.LoadScreen;
import ru.badver.jff.slotgame.screens.transitions.ScreenTransitionSlice;
import ru.badver.jff.slotgame.util.Constants;
import ru.badver.jff.slotgame.util.GamePreferences;

/**
 *
 */
public class SlotGame extends DirectedGame {
    private final String TAG = "PRELOADER ";
    private AssetManager loadManager;

    @Override
    public void create() {
        // Set log level
//        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.setLogLevel(Application.LOG_NONE);

        // load preferences
        GamePreferences.instance.load();

        // Load LoadScreen assets
        loadManager = new AssetManager();
        loadManager.load(Constants.LOADSCREEN, TextureAtlas.class);
        loadManager.load("music/music.ogg", Music.class);
        loadManager.finishLoading();

        Gdx.app.debug(TAG, "----------------------");
        Gdx.app.debug(TAG, "# of assets loaded: " + loadManager.getAssetNames().size);
        for (String a : loadManager.getAssetNames()) {
            Gdx.app.debug(TAG, "asset: " + a);
        }
        Gdx.app.debug(TAG, "----------------------\n");

        ScreenTransitionSlice transit = ScreenTransitionSlice.init(1f, ScreenTransitionSlice.UP_DOWN, 20,
                Interpolation.pow5Out);
        setScreen(new LoadScreen(this, loadManager), transit);
    }
}
