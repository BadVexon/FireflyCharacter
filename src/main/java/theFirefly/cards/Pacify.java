package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFirefly.powers.GainStrengthWhenAttackedPower;

public class Pacify extends AbstractFireflyCard {

    public final static String ID = makeID("Pacify");

    //stupid intellij stuff SKILL, ENEMY, COMMON

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public Pacify() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new StrengthPower(m, -magicNumber));
        if (!m.hasPower(ArtifactPower.POWER_ID)) {
            applyToEnemy(m, new GainStrengthWhenAttackedPower(m, magicNumber));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
            initializeDescription();
        }
    }
}