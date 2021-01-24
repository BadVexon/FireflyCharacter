package theFirefly.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class StoredPower extends AbstractFireflyCard {

    public final static String ID = makeID("StoredPower");

    //stupid intellij stuff ATTACK, ENEMY, COMMON


    public StoredPower() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        exhaust = true;
        baseMagicNumber = magicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
    }

    @Override
    public void triggerWhenDrawn() {
        baseMagicNumber++;
        magicNumber++;
        applyPowers();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}