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

public class PhaseCrystal extends CustomRelic {

    public static final String ID = FireflyMod.makeID("PhaseCrystal");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("PhaseCrystal.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("PhaseCrystal.png"));

    public PhaseCrystal() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
