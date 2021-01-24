package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Darkling;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFirefly.FireflyMod;
import theFirefly.util.FadeAway;
import theFirefly.util.KillEnemyAction;
import theFirefly.util.TextureLoader;
import theFirefly.util.YeetPlayerAction;

public class UnrealityPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = FireflyMod.makeID("UnrealityPower");

    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/Unreality_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/Unreality_power32.png");

    public UnrealityPower(final AbstractCreature owner, final int amount) {
        this.name = "Unreality";
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

    @Override
    public void onInitialApplication() {
        decreaseMaxHealth(owner, amount);
        if (owner.maxHealth < 1) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new FadeAway(owner)));
            if (owner instanceof AbstractMonster)
                if (owner instanceof Darkling) {
                    owner.isDead = true;
                } else {
                    AbstractDungeon.actionManager.addToBottom(new KillEnemyAction((AbstractMonster) owner));
                }
            else if (owner instanceof AbstractPlayer) {
                AbstractDungeon.actionManager.addToBottom(new YeetPlayerAction());
            }
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        decreaseMaxHealth(owner, stackAmount);
        if (owner.maxHealth < 1) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new FadeAway(owner)));
            if (owner instanceof AbstractMonster)
                AbstractDungeon.actionManager.addToBottom(new KillEnemyAction((AbstractMonster) owner));
            else if (owner instanceof AbstractPlayer) {
                AbstractDungeon.actionManager.addToBottom(new YeetPlayerAction());
            }
        }
    }

    public void decreaseMaxHealth(AbstractCreature m, int amount) {
        m.maxHealth -= amount;// 230

        if (m.currentHealth > m.maxHealth) {// 234
            m.currentHealth = m.maxHealth;// 235
        }

        m.healthBarUpdatedEvent();// 237
    }

    @Override
    public void onRemove() {
        if (owner == AbstractDungeon.player) {
            owner.increaseMaxHp(amount, true);
            owner.currentHealth -= amount;
        }
    }

    @Override
    public void onVictory() {
        if (owner == AbstractDungeon.player) {
            owner.increaseMaxHp(amount, true);
            owner.currentHealth -= amount;
        }
    }

    @Override
    public void updateDescription() {
        description = "Max HP is reduced by #b" + amount + " this combat.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new UnrealityPower(owner, amount);
    }
}