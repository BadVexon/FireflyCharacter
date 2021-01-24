package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.PrecisionPower;

public class HyperFocus extends AbstractFireflyCard {

    public final static String ID = makeID("HyperFocus");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public HyperFocus() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (p.hand.size() > 1) {
                    atb(new DiscardAction(p, p, p.hand.size() - 1, false));
                    applyToSelf(new PrecisionPower(p, (p.hand.size() - 1)));
                }
                isDone = true;
            }
        });
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            initializeDescription();
        }
    }
}