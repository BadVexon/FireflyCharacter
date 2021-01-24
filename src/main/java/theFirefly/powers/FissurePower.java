package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

public class FissurePower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = FireflyMod.makeID("FissurePower");

    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/Fissure_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/Fissure_power32.png");

    public FissurePower(final AbstractCreature owner, final int amount) {
        this.name = "Fissure";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.canGoNegative = true;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {// 65 66 67
            this.flashWithoutSound();// 68
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new UnrealityPower(owner, amount), amount));
        }
    }

    @Override
    public void updateDescription() {
        description = "At the start of its turn, gains #b" + amount + " #yUnreality.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new FissurePower(owner, amount);
    }
}