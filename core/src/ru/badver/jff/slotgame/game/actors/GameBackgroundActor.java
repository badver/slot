package ru.badver.jff.slotgame.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.badver.jff.slotgame.game.assets.Assets;

/**
 * Created by user on 03.03.14.
 */
public class GameBackgroundActor extends Actor {
    private TextureRegion image;

    public GameBackgroundActor() {
        super();
        image = new TextureRegion(Assets.instance.gameBackgroung.image);
        setSize(image.getRegionWidth(), image.getRegionHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(image, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
                getScaleY(), getRotation());
    }
}
