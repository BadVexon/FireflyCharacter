package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theFirefly.util.LaserBarrageAction;
import theFirefly.util.PerformXAction;

public class LaserBarrage extends AbstractFireflyCard {

    public final static String ID = makeID("LaserBarrage");

    //stupid intellij stuff ATTACK, ALL_ENEMY, UNCOMMON

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 1;

    public LaserBarrage() {
        super(ID, -1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (energyOnUse < EnergyPanel.totalCount) {
            energyOnUse = EnergyPanel.totalCount;
        }
        LaserBarrageAction l = new LaserBarrageAction(upgraded ? 1 : 0, this);
        AbstractDungeon.actionManager.addToBottom(new PerformXAction(l, p, energyOnUse, freeToPlayOnce));

    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}