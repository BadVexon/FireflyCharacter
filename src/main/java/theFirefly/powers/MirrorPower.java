package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseBlockPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

public class MirrorPower extends AbstractPower implements OnLoseBlockPower, CloneablePowerInterface {

    public static final String POWER_ID = FireflyMod.makeID("MirrorPower");

    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/MirrorShield_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/MirrorShield_power32.png");

    public MirrorPower(final AbstractCreature owner) {
        this.name = "Mirror Shield";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = 0;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.canGoNegative = true;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public int onLoseBlock(DamageInfo info, int damageAmount) {
        amount += Math.min(damageAmount, owner.currentBlock);
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        description = "At the start of your turn, deal #b" + amount + " damage to ALL enemies. Increased by #yBlock loss.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new MirrorPower(owner);
    }
}