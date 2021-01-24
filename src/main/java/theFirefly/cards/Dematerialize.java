package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import theFirefly.powers.StealthPower;

public class Dematerialize extends AbstractFireflyCard {

    public final static String ID = makeID("Dematerialize");

    //stupid intellij stuff SKILL, SELF, RARE

    public Dematerialize() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StealthPower(p, magicNumber));
        applyToSelf(new FrailPower(p, 2, false));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}