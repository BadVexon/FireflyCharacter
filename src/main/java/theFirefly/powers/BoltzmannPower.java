package theFirefly.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

import java.util.ArrayList;

public class BoltzmannPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = FireflyMod.makeID("BoltzmannPower");
    private static final Texture tex84 = TextureLoader.getTexture("thefireflyResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("thefireflyResources/images/powers/Cursed_power32.png");

    public BoltzmannPower() {
        this.ID = POWER_ID;
        this.name = "Boltzmann";
        this.owner = AbstractDungeon.player;
        this.amount = 1;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.canGoNegative = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    public void atStartOfTurnPostDraw() {
        ArrayList<AbstractCard> eligibleCards = new ArrayList<>();
        eligibleCards.addAll(AbstractDungeon.player.drawPile.group);
        eligibleCards.addAll(AbstractDungeon.player.discardPile.group);
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.type != AbstractCard.CardType.STATUS && c.type != AbstractCard.CardType.CURSE) {
                eligibleCards.add(c);
            }
        }
        eligibleCards.removeIf(c -> !c.canUse(AbstractDungeon.player, AbstractDungeon.getRandomMonster()));
        if (!eligibleCards.isEmpty()) {
            AbstractCard c = eligibleCards.get(AbstractDungeon.cardRandomRng.random(eligibleCards.size() - 1));
            AbstractDungeon.player.drawPile.removeCard(c);
            AbstractDungeon.player.discardPile.removeCard(c);
            AbstractDungeon.player.exhaustPile.removeCard(c);
            AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(c));
        }
    }

    @Override
    public void updateDescription() {
        description = "At the start of your turn, put #b" + amount + " playable card from any pile into your hand randomly.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new BoltzmannPower();
    }
}