package theFirefly.cards;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Ruminate extends AbstractFireflyCard {

    public final static String ID = makeID("Ruminate");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public Ruminate() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ExhaustAction(1, !upgraded, false));
        AbstractCard c = new Paradox();
        if (upgraded) c.upgrade();
        makeInHand(c);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}