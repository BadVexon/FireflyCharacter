package theFirefly.cards;

import com.megacrit.cardcrawl.actions.animations.AnimateHopAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;

public class EvasiveManeuver extends AbstractFireflyCard {

    public final static String ID = makeID("EvasiveManeuver");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public EvasiveManeuver() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AnimateHopAction(p));
        applyToSelf(new DexterityPower(p, magicNumber));
        applyToSelf(new LoseDexterityPower(p, magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
            initializeDescription();
        }
    }
}