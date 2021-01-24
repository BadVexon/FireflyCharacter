package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class StarCinder extends AbstractFireflyCard {

    public final static String ID = makeID("StarCinder");

    //stupid intellij stuff SKILL, SELF, COMMON

    public StarCinder() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        makeInHand(new PlasmaWisp());
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            exhaust = false;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}