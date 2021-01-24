package theFirefly.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.UnrealityPower;

public class Paradox extends AbstractFireflyCard {

    public final static String ID = makeID("Paradox");

    //stupid intellij stuff SKILL, ENEMY, SPECIAL

    private static final int MAGIC = 10;
    private static final int UPG_MAGIC = 4;

    public Paradox() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new UnrealityPower(m, magicNumber));
        applyToSelf(new UnrealityPower(p, 3));
        atb(new DrawCardAction(p, 1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}