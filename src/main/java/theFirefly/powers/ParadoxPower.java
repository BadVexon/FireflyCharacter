package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

public class ParadoxPower extends TwoAmountPower implements CloneablePowerInterface {

    public static final String POWER_ID = FireflyMod.makeID("ParadoxPower");

    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/Paradox_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/Paradox_power32.png");

    public ParadoxPower(final AbstractCreature owner, final int amount1, final int amount3) {
        this.name = "Paradoxical Engine";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount1;
        this.amount2 = amount3;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.canGoNegative = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        flash();
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(owner, amount));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new UnrealityPower(owner, amount2), amount2));
    }

    public void atEndOfRound() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));// 47
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = "Whenever you play a card this turn, draw #b" + amount + " card and gain #b" + amount2 + " #yUnreality.";
        else
            description = "Whenever you play a card this turn, draw #b" + amount + " cards and gain #b" + 3 + amount2 + " #yUnreality.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new ParadoxPower(owner, amount, amount2);
    }
}