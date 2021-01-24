package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.CardIgnore;
import theFirefly.powers.DontBreakStealthPower;
import theFirefly.powers.StealthPower;

@CardIgnore
public class StealthWeapon extends AbstractFireflyCard {

    public final static String ID = makeID("StealthWeapon");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public StealthWeapon() {
        super(ID, 0, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DontBreakStealthPower(p, magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
            initializeDescription();
        }
    }
}