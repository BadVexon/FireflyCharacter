package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFirefly.powers.LoseStrengthWhenPlayAttackPower;
import theFirefly.util.IncreaseBaseDamageAction;

public class PotentAmmunition extends AbstractFireflyCard {

    public final static String ID = makeID("PotentAmmunition");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 3;

    public PotentAmmunition() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new IncreaseBaseDamageAction(magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}