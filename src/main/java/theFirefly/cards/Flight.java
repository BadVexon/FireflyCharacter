package theFirefly.cards;

import com.megacrit.cardcrawl.actions.animations.AnimateJumpAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Flight extends AbstractFireflyCard {

    public final static String ID = makeID("Flight");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int BLOCK = 15;
    private static final int UPG_BLOCK = 3;

    public Flight() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AnimateJumpAction(p));
        blck();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
            initializeDescription();
        }
    }
}