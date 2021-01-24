package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFirefly.FireflyMod;
import theFirefly.util.ReturnAction;
import theFirefly.util.TextureLoader;

public class AccountPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = FireflyMod.makeID("AccountPowerPower");

    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/Account_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/Account_power32.png");

    public AccountPower(final AbstractCreature owner, final int amount) {
        this.name = "Accouting";
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
    public void atStartOfTurnPostDraw() {
        this.flash();// 37
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.owner, this.amount));// 38
        AbstractDungeon.actionManager.addToBottom(new ReturnAction(owner, owner, amount, false));
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = "At the start of your turn, draw #b" + this.amount + " card, then #yReturn #b" + this.amount + " card.";
        } else {
            description = "At the start of your turn, draw #b" + this.amount + " cards, then #yReturn #b" + this.amount + " cards.";
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new AccountPower(owner, amount);
    }
}