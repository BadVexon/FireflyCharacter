package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.MirrorPower;

public class MirrorShield extends AbstractFireflyCard {

    public final static String ID = makeID("MirrorShield");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 4;

    public MirrorShield() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new MirrorPower(p));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
            initializeDescription();
        }
    }
}