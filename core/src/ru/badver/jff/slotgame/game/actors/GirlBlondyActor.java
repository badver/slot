package ru.badver.jff.slotgame.game.actors;

import ru.badver.jff.slotgame.game.assets.Assets;
import ru.badver.jff.slotgame.util.Constants;

public class GirlBlondyActor extends AbstractSymbol {

    public GirlBlondyActor() {
        super(Assets.instance.girlBlondy.girlBlondy, Assets.instance.girlBlondy.animGirlBlondy, Constants.SYMBOL_WIDTH, Constants.SYMBOL_HEIGHT);
    }
}
