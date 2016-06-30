package ru.badver.jff.slotgame.game.actors;

import ru.badver.jff.slotgame.game.assets.Assets;
import ru.badver.jff.slotgame.util.Constants;

public class GirlBlackActor extends AbstractSymbol {

    public GirlBlackActor() {
        super(Assets.instance.girlBlack.girlBlack, Assets.instance.girlBlack.animGirlBlack, Constants.SYMBOL_WIDTH, Constants.SYMBOL_HEIGHT);
    }
}
