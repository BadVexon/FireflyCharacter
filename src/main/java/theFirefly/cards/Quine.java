package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Quine extends AbstractFireflyCard {

    public final static String ID = makeID("Quine");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int BLOCK = 6;

    public Quine() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        shuffleIn(this.makeStatEquivalentCopy());
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            exhaust = true;
            isEthereal = false;
            initializeDescription();
        }
    }
}