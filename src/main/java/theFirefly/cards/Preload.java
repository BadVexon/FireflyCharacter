package theFirefly.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.util.ReturnAction;

public class Preload extends AbstractFireflyCard {

    public final static String ID = makeID("Preload");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public Preload() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        baseQuantum = quantum = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(p, magicNumber));
        atb(new ReturnAction(p, p, quantum, false));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            upgradeQuantum(1);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}