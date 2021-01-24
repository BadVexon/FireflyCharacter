package theFirefly.cards;

import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.StealthPower;

public class FullProjection extends AbstractFireflyCard {

    public final static String ID = makeID("FullProjection");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int BLOCK = 9;
    private static final int UPG_BLOCK = 3;

    public FullProjection() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StealthPower(p, 1));
        blck();
        shuffleIn(new Dazed());
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
            initializeDescription();
        }
    }
}