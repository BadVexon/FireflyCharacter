package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.ParadoxPower;

public class ParadoxEngine extends AbstractFireflyCard {

    public final static String ID = makeID("ParadoxEngine");

    //stupid intellij stuff SKILL, SELF, RARE

    private static final int MAGIC = 1;

    public ParadoxEngine() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ParadoxPower(p, magicNumber, 3 * magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}