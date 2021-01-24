package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

public class NebulaPower extends TwoAmountPower implements CloneablePowerInterface {

    public static final String POWER_ID = FireflyMod.makeID("NebulaPower");

    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/Nebula_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/Nebula_power32.png");

    public NebulaPower() {
        this.ID = POWER_ID;
        name = "Nebula...";
        this.owner = AbstractDungeon.player;
        this.amount = 1;
        this.amount2 = 3;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.canGoNegative = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    public void onExhaust(AbstractCard card) {
        amount2--;
        if (amount2 == 0) {
            amount2 = 3;
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new SmokeBombEffect(owner.hb.cX, owner.hb.cY)));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StealthPower(AbstractDungeon.player, amount), amount));
        }
    }

    @Override
    public void updateDescription() {
        description = "Whenever you #yExhaust #b" + amount2 + " cards, gain #b" + amount + " #yStealth.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new NebulaPower();
    }
}