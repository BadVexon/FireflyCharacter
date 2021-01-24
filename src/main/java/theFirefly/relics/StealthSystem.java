package theFirefly.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFirefly.FireflyMod;
import theFirefly.powers.StealthPower;
import theFirefly.util.TextureLoader;

import static theFirefly.FireflyMod.makeRelicOutlinePath;
import static theFirefly.FireflyMod.makeRelicPath;

public class StealthSystem extends CustomRelic {

    public static final String ID = FireflyMod.makeID("StealthSystem");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BentBrush.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Prayer_Bead.png"));

    public StealthSystem() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.FLAT);
    }

    public void atBattleStart() {
        applyToSelf(new StealthPower(AbstractDungeon.player, 2));
    }

    public void applyToSelf(AbstractPower po) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
