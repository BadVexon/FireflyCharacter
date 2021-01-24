package theFirefly.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

import static theFirefly.FireflyMod.makeRelicOutlinePath;
import static theFirefly.FireflyMod.makeRelicPath;

public class AbsoluteTerror extends CustomRelic {

    public static final String ID = FireflyMod.makeID("AbsoluteTerror");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("AbsoluteTerror.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("AbsoluteTerror.png"));

    public boolean activated = false;

    public AbsoluteTerror() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        activated = false;
        this.beginPulse();
    }

    @Override
    public void onPlayerEndTurn() {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() < 2 && !activated) {
            flash();
            stopPulse();
            grayscale = true;
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 40));
            activated = true;
        }
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
