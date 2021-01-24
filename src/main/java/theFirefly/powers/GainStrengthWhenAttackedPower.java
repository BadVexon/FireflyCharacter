package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

public class GainStrengthWhenAttackedPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = FireflyMod.makeID("GainStrengthWhenAttackedPower");

    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/Cursed_power32.png");

    public GainStrengthWhenAttackedPower(final AbstractCreature owner, final int amount) {
        this.name = "Tired";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.canGoNegative = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL)
        {
            flash();
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount), amount));
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(owner, owner, this));
        }
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        description = "When attacked, gains #b" + this.amount + " #yStrength.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new GainStrengthWhenAttackedPower(owner, amount);
    }
}