package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

public class ShuffleInTurnsPower extends AbstractPower implements CloneablePowerInterface, NonStackablePower {

    public static final String POWER_ID = FireflyMod.makeID("ShuffleInTurnsPower");

    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/StealthCooldown_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/StealthCooldown_power32.png");

    private AbstractCard myCard;

    public ShuffleInTurnsPower(final AbstractCreature owner, final int amount, AbstractCard card) {
        this.name = "Stealth Cooldown";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.canGoNegative = false;
        myCard = card;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    public void atEndOfRound() {
        if (this.amount == 1) {// 46
            flash();
            AbstractCard c = myCard.makeStatEquivalentCopy();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, 1, true, false));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));// 47
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this, 1));// 49
        }
    }

    @Override
    public void updateDescription() {
        description = "In #b" + amount + " turns, add #b1 " + myCard.name + " into your draw pile.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new ShuffleInTurnsPower(owner, amount, myCard);
    }
}