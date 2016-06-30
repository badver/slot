package ru.badver.jff.slotgame.game.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * animated black hair girl
 */
public class AssetGirlRed {
    public final TextureRegion girlRed;
    public final Animation     animGirlRed;

    public AssetGirlRed(AssetManager am) {
        // picture
        TextureAtlas textureAtlas = am.get("images/red.pack", TextureAtlas.class);
        girlRed = textureAtlas.findRegion("red");

        // Animation:
        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.findRegions("red");
        TextureAtlas.AtlasRegion region = regions.first();

        animGirlRed = new Animation(1.0f / 20.0f, regions, Animation.PlayMode.LOOP_PINGPONG);
    }
}
