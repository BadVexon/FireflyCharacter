package theFirefly.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

import static theFirefly.FireflyMod.makeRelicOutlinePath;
import static theFirefly.FireflyMod.makeRelicPath;

public class ShadowThruster extends CustomRelic {

    public static final String ID = FireflyMod.makeID("ShadowThruster");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ShadowThruster.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ShadowThruster.png"));

    public ShadowThruster() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.HEAVY);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster++;
    }

    @Override
    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster--;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.type == AbstractCard.CardType.POWER) {
            this.flash();
            for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(q, q, new MetallicizePower(q, 3), 3));
            }
        }
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
