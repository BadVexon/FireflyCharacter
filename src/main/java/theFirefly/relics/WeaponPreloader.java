package theFirefly.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

import static theFirefly.FireflyMod.makeRelicOutlinePath;
import static theFirefly.FireflyMod.makeRelicPath;

public class WeaponPreloader extends CustomRelic {

    public static final String ID = FireflyMod.makeID("WeaponPreloader");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("WeaponPreloader.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("WeaponPreloader.png"));

    public boolean activated = false;

    public WeaponPreloader() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    @Override
    public void atTurnStart() {
        activated = false;
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (!activated && drawnCard.type == AbstractCard.CardType.ATTACK) {
            activated = true;
            drawnCard.freeToPlayOnce = true;
            drawnCard.superFlash();
        }
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
