package theFirefly.cards;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.StealthPower;

public class PhantomStrike extends AbstractFireflyCard {

    public final static String ID = makeID("PhantomStrike");

    //stupid intellij stuff SKILL, ENEMY, UNCOMMON

    private static final int MAGIC = 7;
    private static final int UPG_MAGIC = 3;

    public PhantomStrike() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(StealthPower.POWER_ID)) {
            int x = p.getPower(StealthPower.POWER_ID).amount;
            for (int i = 0; i < x; i++)
                atb(new LoseHPAction(m, p, magicNumber));
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