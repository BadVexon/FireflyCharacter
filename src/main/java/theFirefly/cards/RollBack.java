package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.util.RetrieveAction;
import theFirefly.util.ReturnAction;

public class RollBack extends AbstractFireflyCard {

    public final static String ID = makeID("RollBack");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public RollBack() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new RetrieveAction(magicNumber));
        atb(new ReturnAction(p, p, magicNumber, false));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            exhaust = false;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}