package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.UnburdenPower;

public class Unburden extends AbstractFireflyCard {

    public final static String ID = makeID("Unburden");

    //stupid intellij stuff SKILL, SELF, RARE

    private static final int MAGIC = 1;

    public Unburden() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new UnburdenPower(p, magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            initializeDescription();
        }
    }
}