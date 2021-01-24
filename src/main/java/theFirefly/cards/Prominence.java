package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.ProminencePower;

public class Prominence extends AbstractFireflyCard {

    public final static String ID = makeID("Prominence");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 3;

    public Prominence() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        makeInHand(new PlasmaWisp());
        applyToSelf(new ProminencePower(p, magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
            initializeDescription();
        }
    }
}