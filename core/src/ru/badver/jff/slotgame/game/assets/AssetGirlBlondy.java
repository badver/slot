package ru.badver.jff.slotgame.game.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * animated black hair girl
 */
public class AssetGirlBlondy {
    public final TextureRegion girlBlondy;
    public final Animation     animGirlBlondy;

    public AssetGirlBlondy(AssetManager am) {
        // picture
        TextureAtlas textureAtlas = am.get("images/blondy.pack", TextureAtlas.class);
        girlBlondy = textureAtlas.findRegion("blondy");

        // Animation:
        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.findRegions("blondy");
        TextureAtlas.AtlasRegion region = regions.first();

        animGirlBlondy = new Animation(1.0f / 20.0f, regions, Animation.PlayMode.LOOP_PINGPONG);
    }
}
