package ru.badver.jff.slotgame.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import ru.badver.jff.slotgame.util.Constants;
import ru.badver.jff.slotgame.util.ReelState;

import java.util.Random;

public class Reel extends Group {
    private final float duration;
    private final float timeOffset;
    private final float height     = 588; // 588
    private final float clipHeight = 500;
    private final Rectangle scissors;
    private final Rectangle clipBounds;
    private       Camera    camera;
    private       ReelState state;
    private       float     time;

    public Reel(float posX, float posY) {
        this(posX, posY, 0.1f, 0);
    }

    public Reel(float posX, float posY, float duration, float timeOffset) {
        super();
        this.duration = duration;
        this.timeOffset = timeOffset;
        this.state = ReelState.STOP;

        // set position and size of reel
        this.setPosition(posX, posY);
        this.setSize(Constants.SYMBOL_WIDTH, height);

        //Create a scissor rectangle that covers the Reel
        scissors = new Rectangle();
        clipBounds = new Rectangle(getX(), getY(), getWidth(), clipHeight);
        //        clipBounds = new Rectangle(0, 0, getWidth(), clipHeight);

        addSymbols();
    }

    private void addSymbols() {
        Random random = new Random();
        // fill with symbols
        for (int i = 0; i < 5; i++) {
            AbstractSymbol newActor;

            switch (random.nextInt(3)) {
                case 0:
                    newActor = new GirlRedActor();
                    break;
                case 1:
                    newActor = new GirlBlackActor();
                    break;
                case 2:
                    newActor = new GirlBlondyActor();
                    break;
                default:
                    newActor = new GirlRedActor();
                    break;
            }
            newActor.setPosition(0, Constants.SYMBOL_LINE[i]);
//            newActor.setAnimated(true);
            this.addActor(newActor);
        }
    }

    public Reel(float posX, float posY, float duration) {
        this(posX, posY, duration, 0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // decrease time delay
        time -= delta;

        // check Actors (move out of reel)
        Actor[] actors = this.getChildren().begin();

        // add move to actors
        for (int i = 0, n = this.getChildren().size; i < n; i++) {

            // if out of reel box move actor to top
            if (actors[i].getTop() < -Constants.SYMBOL_SHIFT) {
                actors[i].setY(findMaxTop(actors));
            }

            switch (getState()) {
                case STOP:
                    break;
                case ROLL:
                    time = timeOffset; // reset time
                    moveActorDown(actors[i]);
                    break;
                case BEGIN_ROLL:
                    break;
                case BEGIN_STOP:
                    if (time > 0) {
                        moveActorDown(actors[i]);
                    } else {
                        moveLast(actors[i]);
                        if (i == n - 1) { // just move last element
                            setState(ReelState.STOPPING);
                        }
                    }
                    break;
                case STOPPING:
                    setState(ReelState.STOP);
                    break;
            }
        }
        this.getChildren().end();
    }

    private float findMaxTop(Actor[] actors) {
        // current highest Y
        float top = 0;

        // find max Top coordinate of actors (top of reel)
        for (int i = 0, n = this.getChildren().size; i < n; i++) {
            float actTop = actors[i].getTop();
            if (actTop > top) {
                top = actTop;
            }
        }
        return top;
    }

    public ReelState getState() {
        return state;
    }

    public void setState(ReelState state) {
        this.state = state;
        Gdx.app.debug(this.getName(), "Reel state changed to " + state);
    }

    private void moveActorDown(Actor actor) {
        // set move down
        if (actor.getActions().size < 1) {
            actor.addAction(
                    Actions.moveBy(0, -1 * Constants.SYMBOL_SHIFT, duration, Interpolation.linear));
        }
    }

    private void moveLast(Actor actor) {
        actor.addAction(Actions.moveBy(0, -1 * Constants.SYMBOL_SHIFT, duration*4, Interpolation.bounceOut));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // TODO how to use clipBegin/clipEnd to clip instead of ScissorStack ???

        // calculate scissors
        ScissorStack.calculateScissors(camera, batch.getTransformMatrix(), clipBounds, scissors);

        //Make sure nothing is clipped before we want it to.
        batch.flush();

        // push clipping
        ScissorStack.pushScissors(scissors);
        //        clipBegin(getX(), getY(), getWidth(), getHeight());

        // draw as usual
        super.draw(batch, parentAlpha);

        //        clipEnd();

        //Perform the actual clipping
        ScissorStack.popScissors();
    }

    /**
     * Set stage and camera for the reel (used for clipping)
     *
     * @param stage
     */
    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        Stage st = getStage();
        if (st != null) {
            camera = st.getCamera();
        }
    }
}
