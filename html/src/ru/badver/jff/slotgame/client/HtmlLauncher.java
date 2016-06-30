package ru.badver.jff.slotgame.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.badlogic.gdx.backends.gwt.GwtGraphics;
import ru.badver.jff.slotgame.SlotGame;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                GwtApplicationConfiguration config = new GwtApplicationConfiguration(800, 600);
                config.fullscreenOrientation = GwtGraphics.OrientationLockType.LANDSCAPE;
                return config;
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new SlotGame();
        }
}