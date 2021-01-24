package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theFirefly.util.EnergyShieldAction;
import theFirefly.util.PerformXAction;

public class EnergyShield extends AbstractFireflyCard {

    public final static String ID = makeID("EnergyShield");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 2;

    public EnergyShield() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (energyOnUse < EnergyPanel.totalCount) {
            energyOnUse = EnergyPanel.totalCount;
        }

        EnergyShieldAction a = new EnergyShieldAction(0, block);
        AbstractDungeon.actionManager.addToBottom(new PerformXAction(a, p, energyOnUse, freeToPlayOnce));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
            initializeDescription();
        }
    }
}