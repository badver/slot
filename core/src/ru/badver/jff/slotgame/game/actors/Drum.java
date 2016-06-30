package ru.badver.jff.slotgame.game.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import ru.badver.jff.slotgame.util.Constants;
import ru.badver.jff.slotgame.util.GameState;
import ru.badver.jff.slotgame.util.ReelState;
import ru.badver.jff.slotgame.util.States;

public class Drum extends Group {
    private final float duration = 0.08f; // time to move 1 symbol

    public Drum() {
        this(Constants.REEL_TIME_OFFSET * 1, Constants.REEL_TIME_OFFSET * 2, Constants.REEL_TIME_OFFSET * 3,
                Constants.REEL_TIME_OFFSET * 4, Constants.REEL_TIME_OFFSET * 5);
    }

    public Drum(float reel1, float reel2, float reel3, float reel4, float reel5) {
        super();

        // reels
        Reel reel_1 = new Reel(Constants.SYMBOL_COLUMN[0], 97, duration, reel1);
        reel_1.setName("REEL 1");
        Reel reel_2 = new Reel(Constants.SYMBOL_COLUMN[1], 97, duration, reel2);
        reel_2.setName("REEL 2");
        Reel reel_3 = new Reel(Constants.SYMBOL_COLUMN[2], 97, duration, reel3);
        reel_3.setName("REEL 3");
        Reel reel_4 = new Reel(Constants.SYMBOL_COLUMN[3], 97, duration, reel4);
        reel_4.setName("REEL 4");
        Reel reel_5 = new Reel(Constants.SYMBOL_COLUMN[4], 97, duration, reel5);
        reel_5.setName("REEL 5");

        this.addActor(reel_1);
        this.addActor(reel_2);
        this.addActor(reel_3);
        this.addActor(reel_4);
        this.addActor(reel_5);

        setReelsState(ReelState.STOP);
    }

    private void setReelsState(ReelState reelState) {
        Actor[] actors = this.getChildren().begin();
        Reel reel;
        for (int i = 0, n = this.getChildren().size; i < n; i++) {
            reel = (Reel) actors[i];
            reel.setState(reelState);
        }
        this.getChildren().end();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        switch (GameState.instance.getState()) {
            case START:
                break;
            case LOADING:
                break;
            case LOADED:
                break;
            case PAUSED:
                break;
            case DEFAULT:
                break;
            case START_ROLLING:
                setReelsState(ReelState.ROLL);
                GameState.instance.setState(States.ROLLING);
                break;
            case ROLLING:
                break;
            case STOPPING:
                setReelsState(ReelState.BEGIN_STOP);
                GameState.instance.setState(States.DEFAULT);
                break;
            case RISK:
                break;
            case BONUS:
                break;
        }
    }
}
