package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

public class TargetingPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = FireflyMod.makeID("RecollectionPower");

    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/AutoTargeting_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/AutoTargeting_power32.png");

    public TargetingPower(final AbstractCreature owner, final int amount) {
        this.name = "Targeting...";
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
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new PrecisionPower(owner, amount), amount));
    }

    @Override
    public void updateDescription() {
        description = "At the start of your turn, gain #b" + this.amount + " #yPrecision.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new TargetingPower(owner, amount);
    }
}