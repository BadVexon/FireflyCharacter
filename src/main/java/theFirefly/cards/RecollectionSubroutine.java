package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.RecollectionPower;

public class RecollectionSubroutine extends AbstractFireflyCard {

    public final static String ID = makeID("RecollectionSubroutine");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    public RecollectionSubroutine() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new RecollectionPower(p, 1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(1);
            initializeDescription();
        }
    }
}