package theFirefly.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFirefly.FireflyMod;
import theFirefly.cards.PlasmaWisp;
import theFirefly.util.TextureLoader;

import static theFirefly.FireflyMod.makeRelicOutlinePath;
import static theFirefly.FireflyMod.makeRelicPath;

public class PlasmaFairy extends CustomRelic {

    public static final String ID = FireflyMod.makeID("PlasmaFairy");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("PlasmaFairy.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("PlasmaFairy.png"));

    public PlasmaFairy() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public void atBattleStartPreDraw() {
        AbstractCard c = new PlasmaWisp();
        c.upgrade();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, 1, true, false));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
