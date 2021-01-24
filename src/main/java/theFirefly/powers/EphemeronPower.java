package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

public class EphemeronPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = FireflyMod.makeID("EphemeronPower");

    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/Ephemeron_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/Ephemeron_power32.png");

    public EphemeronPower(final AbstractCreature owner, final int amount) {
        this.name = "Ephemeral";
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
        flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new StealthPower(owner, amount), amount));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new ExhaustAction(AbstractDungeon.player.hand.size(), true, false));
        }
    }

    @Override
    public void updateDescription() {
        description = "At the start of your turn, gain #b" + this.amount + " #yStealth. At the end of your turn, #yExhaust your hand.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new EphemeronPower(owner, amount);
    }
}