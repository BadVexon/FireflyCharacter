package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

public class CosmogenesisPower extends TwoAmountPower implements CloneablePowerInterface {

    public static final String POWER_ID = FireflyMod.makeID("CosmogenesisPower");
    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/Cosmogenesis_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/Cosmogenesis_power32.png");

    public CosmogenesisPower() {
        this.ID = POWER_ID;
        name = "Cosmogenesis";
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
            for (int i = 0; i < amount; i++) {
                flash();
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(AbstractDungeon.returnTrulyRandomCardInCombat()));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = "Every time you #yExhaust #b" + amount2 + " cards, add #b" + amount + " random card into your hand.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new CosmogenesisPower();
    }
}