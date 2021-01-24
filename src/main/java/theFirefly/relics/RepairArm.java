package theFirefly.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

import static theFirefly.FireflyMod.makeRelicOutlinePath;
import static theFirefly.FireflyMod.makeRelicPath;

public class RepairArm extends CustomRelic {

    public static final String ID = FireflyMod.makeID("RepairArm");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("RepairArm.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("RepairArm.png"));

    public RepairArm() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.FLAT);
    }

    @Override
    public void onEnterRestRoom() {
        if (AbstractDungeon.player.currentHealth < AbstractDungeon.player.maxHealth)
            for (int i = AbstractDungeon.player.currentHealth; (i < AbstractDungeon.player.maxHealth && AbstractDungeon.player.gold > 0); i++) {
                AbstractDungeon.player.loseGold(1);
                AbstractDungeon.player.heal(1);
            }
    }

    @Override
    public int getPrice() {
        int x = AbstractDungeon.cardRandomRng.random(70, 100);
        return x;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
