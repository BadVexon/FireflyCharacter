package theFirefly.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class QuantumTunneling extends AbstractFireflyCard {

    public final static String ID = makeID("QuantumTunneling");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public QuantumTunneling() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(p, 2));
        atb(new GainEnergyAction(magicNumber));
        shuffleIn(new VoidCard());
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}