package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.ErraticPower;

public class ErraticMotions extends AbstractFireflyCard {

    public final static String ID = makeID("ErraticMotions");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public ErraticMotions() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ErraticPower(p, magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
            initializeDescription();
        }
    }
}