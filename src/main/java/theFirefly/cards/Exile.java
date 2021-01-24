package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EntanglePower;
import theFirefly.powers.EntanglePowerButYouCanChooseHowMuch;
import theFirefly.powers.StealthPower;

public class Exile extends AbstractFireflyCard {

    public final static String ID = makeID("Exile");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public Exile() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StealthPower(p, magicNumber));
        applyToSelf(new EntanglePowerButYouCanChooseHowMuch(2));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
            initializeDescription();
        }
    }
}