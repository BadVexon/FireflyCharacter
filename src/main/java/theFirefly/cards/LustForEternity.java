package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.EternityPower;

public class LustForEternity extends AbstractFireflyCard {

    public final static String ID = makeID("LustForEternity");

    //stupid intellij stuff POWER, SELF, RARE

    private static final int MAGIC = 5;

    public LustForEternity() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EternityPower(p, magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(94);
            initializeDescription();
        }
    }
}