package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FlameBarrierPower;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

public class IonizedPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = FireflyMod.makeID("IonizedPower");

    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/Ionized_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/Ionized_power32.png");

    public IonizedPower(final AbstractCreature owner, final int amount) {
        this.name = "Ionized";
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
    public void onGainedBlock(float blockAmount) {
        if (blockAmount > 0F) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new FlameBarrierPower(owner, amount), amount));
        }
    }

    @Override
    public void updateDescription() {
        description = "Whenever you gain #yBlock, deal #b" + this.amount + " damage back whenever you are attacked this turn.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new IonizedPower(owner, amount);
    }
}