package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

public class EternityPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = FireflyMod.makeID("EternityPower");

    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/Eternity_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/Eternity_power32.png");

    public EternityPower(final AbstractCreature owner, final int amount) {
        this.name = "Eternal Fragment";
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

    public void atEndOfRound() {
        if (this.amount == 0) {// 46
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));// 47
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this, 1));// 49
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = "#yPrecision or #yStealth don't get reduced by cards for #b" + this.amount + " turn.";
        } else {
            description = "#yPrecision or #yStealth don't get reduced by cards for #b " + this.amount + " turns.";
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new EternityPower(owner, amount);
    }
}