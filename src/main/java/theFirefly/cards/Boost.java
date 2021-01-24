package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

public class Boost extends AbstractFireflyCard {

    public final static String ID = makeID("Boost");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 3;

    public Boost() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new DrawCardNextTurnPower(p, 1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
            initializeDescription();
        }
    }
}