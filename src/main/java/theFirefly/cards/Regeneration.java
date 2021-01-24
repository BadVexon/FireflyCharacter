package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.RegenerationPowerButNotTHATOne;

public class Regeneration extends AbstractFireflyCard {

    public final static String ID = makeID("Regeneration");

    //stupid intellij stuff POWER, SELF, RARE

    public Regeneration() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new RegenerationPowerButNotTHATOne());
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(2);
            initializeDescription();
        }
    }
}