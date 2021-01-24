package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.BoltzmannPower;

public class BoltzmannBrain extends AbstractFireflyCard {

    public final static String ID = makeID("BoltzmannBrain");

    //stupid intellij stuff POWER, SELF, RARE

    public BoltzmannBrain() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BoltzmannPower());
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            initializeDescription();
        }
    }
}