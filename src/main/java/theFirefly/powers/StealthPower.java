package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFirefly.FireflyMod;
import theFirefly.relics.PhaseCrystal;
import theFirefly.util.TextureLoader;

public class StealthPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = FireflyMod.makeID("StealthPower");

    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/Stealth_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/Stealth_power32.png");

    public StealthPower(final AbstractCreature owner, final int amount) {
        this.name = "Stealth";
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

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && !owner.hasPower(EternityPower.POWER_ID) && !owner.hasPower(DontBreakStealthPower.POWER_ID)) {
            flash();
            if (this.amount == 0) {// 46
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));// 47
            } else {
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this, 1));// 49
            }
        }
    }

    @Override
    public void onRemove() {
        if (AbstractDungeon.player.hasRelic(PhaseCrystal.ID)) {
            AbstractDungeon.player.getRelic(PhaseCrystal.ID).flash();
            AbstractPlayer p = AbstractDungeon.player;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 3), 3));// 39
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LoseStrengthPower(p, 3), 3));// 41
        }
    }

    public void atEndOfRound() {
        if (this.amount == 1) {// 46
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));// 47
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this, 1));// 49
        }
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return (damage / 2);
    }

    @Override
    public void updateDescription() {
        description = "Enemy attack damage is halved. At the end of the enemy turn or whenever you play an #yAttack, lose #b1 #yStealth.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new StealthPower(owner, amount);
    }
}