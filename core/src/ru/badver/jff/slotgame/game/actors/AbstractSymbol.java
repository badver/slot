package ru.badver.jff.slotgame.game.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

// TODO finish base Symbol Class
public abstract class AbstractSymbol extends Actor {
    private final TextureRegion image;
    private final Animation     animation;
    private final int           width;
    private final int           height;
    private       float         stateTime;
    private boolean isLast     = false;
    private boolean isAnimated = false;
    private boolean isLooping  = true;

    /**
     * @param image     static image
     * @param animation animation (may be null)
     * @param width     width of actor
     * @param height    height of actor
     */
    protected AbstractSymbol(TextureRegion image, Animation animation, int width, int height) {
        this.image = image;
        this.animation = animation;
        this.width = width;
        this.height = height;
        setSize(width, height);
        setOrigin(width / 2, height / 2);
    }

    /**
     * Return true if animation of actor must be looped
     *
     * @return
     */
    public boolean isLooping() {
        return isLooping;
    }

    /**
     * TRUE for loop
     *
     * @param isLooping
     */
    public void setLooping(boolean isLooping) {
        this.isLooping = isLooping;
    }

    /**
     * Return true if actor must be animated
     *
     * @return
     */
    public boolean isAnimated() {
        return isAnimated;
    }

    /**
     * Set animation fo actor (if no animation's setted, nothing's change)
     *
     * @param isAnimated
     */
    public void setAnimated(boolean isAnimated) {
        if (animation != null) {
            this.isAnimated = isAnimated;
        } else {
            isAnimated = false;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        TextureRegion drawRegion;
        if (isAnimated) {
            drawRegion = animation.getKeyFrame(stateTime, isLooping);
        } else {
            drawRegion = image;
        }

        batch.draw(drawRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
                getScaleY(), getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (isAnimated) {
            stateTime += delta;
        } else {
            stateTime = 0;
        }
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean isLast) {
        this.isLast = isLast;
    }
}
