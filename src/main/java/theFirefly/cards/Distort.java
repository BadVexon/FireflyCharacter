package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.UnrealityPower;
import theFirefly.util.SpecificNonChosenDiscardPileToHandAction;

public class Distort extends AbstractFireflyCard {

    public final static String ID = makeID("Distort");

    //stupid intellij stuff SKILL, ENEMY, COMMON

    private static final int MAGIC = 7;
    private static final int UPG_MAGIC = 3;

    public Distort() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new UnrealityPower(m, magicNumber));
        if (!p.discardPile.isEmpty())
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    AbstractCard s = AbstractDungeon.player.discardPile.getRandomCard(AbstractDungeon.cardRandomRng);
                    AbstractDungeon.actionManager.addToBottom(new SpecificNonChosenDiscardPileToHandAction(s));
                }
            });
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}