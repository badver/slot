package ru.badver.jff.slotgame.game.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * animated black hair girl
 */
public class AssetSeaDay {
    public final TextureRegion image;
    public final Animation     animation;

    public AssetSeaDay(AssetManager am) {
        // picture
        TextureAtlas textureAtlas = am.get("images/sea_day.pack", TextureAtlas.class);
        image = textureAtlas.findRegion("sea_day");

        // Animation:
        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.findRegions("sea_day");

        animation = new Animation(1.0f / 20.0f, regions, Animation.PlayMode.LOOP_PINGPONG);
    }
}
