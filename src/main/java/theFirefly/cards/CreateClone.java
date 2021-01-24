package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.CloningPower;

public class CreateClone extends AbstractFireflyCard {

    public final static String ID = makeID("CreateClone");

    //stupid intellij stuff POWER, SELF, RARE

    public CreateClone() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new CloningPower(p, 1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(1);
            initializeDescription();
        }
    }
}