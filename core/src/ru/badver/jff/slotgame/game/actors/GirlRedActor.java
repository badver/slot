package ru.badver.jff.slotgame.game.actors;

import ru.badver.jff.slotgame.game.assets.Assets;
import ru.badver.jff.slotgame.util.Constants;

public class GirlRedActor extends AbstractSymbol {

    public GirlRedActor() {
        super(Assets.instance.girlRed.girlRed, Assets.instance.girlRed.animGirlRed, Constants.SYMBOL_WIDTH, Constants.SYMBOL_HEIGHT);
    }
}
