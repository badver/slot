package ru.badver.jff.slotgame.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.badver.jff.slotgame.game.assets.Assets;
import ru.badver.jff.slotgame.util.Constants;

public class GameFrameActor extends Actor {
    private TextureRegion image;

    public GameFrameActor() {
        super();
        image = new TextureRegion(Assets.instance.gameBackgroung.frame);
        //        setSize(image.getRegionWidth(), image.getRegionHeight());
        setSize(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(image, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
                getScaleY(), getRotation());
    }
}
