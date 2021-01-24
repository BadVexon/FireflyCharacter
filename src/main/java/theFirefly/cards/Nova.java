package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Nova extends AbstractFireflyCard {

    public final static String ID = makeID("Nova");

    //stupid intellij stuff SKILL, SELF, RARE

    public Nova() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                int x = p.hand.size();
                atb(new ExhaustAction(x, false));
                AbstractCard q = new PlasmaWisp();
                if (upgraded) {
                    q.upgrade();
                }
                for (int i = 0; i < x; i++) {
                    makeInHand(q);
                }
                isDone = true;
            }
        });
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}