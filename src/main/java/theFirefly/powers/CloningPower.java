package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseBlockPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnPlayerDeathPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

public class CloningPower extends AbstractPower implements CloneablePowerInterface, OnPlayerDeathPower, OnLoseBlockPower {

    public static final String POWER_ID = FireflyMod.makeID("CloningPower");

    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/Clone_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/Clone_power32.png");

    public CloningPower(final AbstractCreature owner, final int amount) {
        this.name = "Clone Created";
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

    block

    @Override
    public boolean onPlayerDeath(AbstractPlayer abstractPlayer, DamageInfo damageInfo) {
        abstractPlayer.decreaseMaxHealth(abstractPlayer.maxHealth / 2);
        abstractPlayer.heal(abstractPlayer.maxHealth);
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, this, 1));
        return false;
    }

    @Override
    public void onVictory() {
        owner.increaseMaxHp(amount * 2, true);
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = "Next time you would die, come back with #b50% Max HP. At the end of combat, if you haven't died, gain #b" + amount * 2 + " Max HP.";
        } else {
            description = "The next #b" + amount + " times you would die, come back with #b50% Max HP. At the end of combat, if you haven't died, gain #b" + amount * 2 + " Max HP.";
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new CloningPower(owner, amount);
    }
}