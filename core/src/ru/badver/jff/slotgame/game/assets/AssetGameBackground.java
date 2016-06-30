package ru.badver.jff.slotgame.game.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * animated black hair girl
 */
public class AssetGameBackground {
    public final TextureRegion image;
    public final TextureRegion frame;

    public AssetGameBackground(AssetManager am) {
        // picture
        TextureAtlas textureAtlas = am.get("images/main/mainfon_0.atlas", TextureAtlas.class);
        image = textureAtlas.findRegion("mainfon");

        // picture
        frame = am.get("images/main/mainfon_1.atlas", TextureAtlas.class).findRegion("frame_mainfon");
    }
}
