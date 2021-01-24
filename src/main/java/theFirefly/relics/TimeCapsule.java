package theFirefly.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFirefly.FireflyMod;
import theFirefly.cards.PlasmaWisp;
import theFirefly.util.ReturnAction;
import theFirefly.util.TextureLoader;

import static theFirefly.FireflyMod.makeRelicOutlinePath;
import static theFirefly.FireflyMod.makeRelicPath;

public class TimeCapsule extends CustomRelic {

    public static final String ID = FireflyMod.makeID("TimeCapsule");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TimeCapsule.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("TimeCapsule.png"));

    public TimeCapsule() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public void onPlayerEndTurn() {
        AbstractDungeon.actionManager.addToBottom(new ReturnAction(AbstractDungeon.player, AbstractDungeon.player, 1, false));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
