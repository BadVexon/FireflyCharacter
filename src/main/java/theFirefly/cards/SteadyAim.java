package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.PrecisionPower;

public class SteadyAim extends AbstractFireflyCard {

    public final static String ID = makeID("SteadyAim");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public SteadyAim() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new PrecisionPower(p, magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
            initializeDescription();
        }
    }
}