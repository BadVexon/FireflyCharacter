package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

public class UnburdenPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = FireflyMod.makeID("UnburdenPower");

    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/Unburden_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/Unburden_power32.png");

    public UnburdenPower(final AbstractCreature owner, final int amount1) {
        this.name = "Unburden";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount1;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.canGoNegative = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void onExhaust(AbstractCard card) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new DexterityPower(owner, amount), amount));
    }

    public void atEndOfRound() {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));// 47
    }

    @Override
    public void updateDescription() {
        description = "Whenever you #yExhaust a card this turn, gain #b" + amount + " #yDexterity.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new UnburdenPower(owner, amount);
    }
}