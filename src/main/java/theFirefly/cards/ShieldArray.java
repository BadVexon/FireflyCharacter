package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShieldArray extends AbstractFireflyCard {

    public final static String ID = makeID("ShieldArray");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int BLOCK = 2;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public ShieldArray() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            blck();
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