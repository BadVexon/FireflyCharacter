package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theFirefly.powers.ShuffleInTurnsPower;
import theFirefly.util.PerformXAction;
import theFirefly.util.StealthModeAction;

public class StealthMode extends AbstractFireflyCard {

    public final static String ID = makeID("StealthMode");

    //stupid intellij stuff SKILL, SELF, BASIC

    public StealthMode() {
        super(ID, -1, CardType.POWER, CardRarity.BASIC, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (energyOnUse < EnergyPanel.totalCount) {
            energyOnUse = EnergyPanel.totalCount;
        }
        StealthModeAction s = new StealthModeAction(upgraded ? 1 : 0);
        AbstractDungeon.actionManager.addToBottom(new PerformXAction(s, p, energyOnUse, freeToPlayOnce));
        applyToSelf(new ShuffleInTurnsPower(p, 3, this));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}