package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

public class RegenerationPowerButNotTHATOne extends AbstractPower implements CloneablePowerInterface, NonStackablePower {

    public static final String POWER_ID = FireflyMod.makeID("RegeratinPower");

    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/NanoGel_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/NanoGel_power32.png");

    public RegenerationPowerButNotTHATOne() {
        this.ID = POWER_ID;
        name = "Self-Forming";
        this.owner = AbstractDungeon.player;
        this.amount = 2;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.canGoNegative = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    public void atEndOfRound() {
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this, 1));// 49
        if (amount == 1) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BufferPower(AbstractDungeon.player, 1), 1));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenerationPowerButNotTHATOne(), 1));
        }
    }

    @Override
    public void updateDescription() {
        description = "In #b" + amount + "turns, prevent the next time you would lose HP. Repeat every other turn.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new RegenerationPowerButNotTHATOne();
    }
}