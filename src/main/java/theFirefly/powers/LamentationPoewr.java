package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

public class LamentationPoewr extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = FireflyMod.makeID("LamentationPower");

    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/Lamentation_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/Lamentation_power32.png");

    public LamentationPoewr(final AbstractCreature owner, final int amount) {
        this.name = "Lamenting";
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
    public void onExhaust(AbstractCard card) {
        flash();
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void updateDescription() {
        description = "Whenever you #yExhaust a card, deal #b" + amount + " damage to ALL enemies.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new LamentationPoewr(owner, amount);
    }
}