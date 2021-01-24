package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.util.ReturnAction;

public class Reposition extends AbstractFireflyCard {

    public final static String ID = makeID("Reposition");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int BLOCK = 9;
    private static final int UPG_BLOCK = 3;

    public Reposition() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new ReturnAction(p, p, 1, false));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
            initializeDescription();
        }
    }
}