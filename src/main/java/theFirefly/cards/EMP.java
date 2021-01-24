package theFirefly.cards;

import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EMP extends AbstractFireflyCard {

    public final static String ID = makeID("EMP");

    //stupid intellij stuff SKILL, ENEMY, COMMON

    public EMP() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster q : monsterList()) {
            if (!q.isDead && !q.isDying) {
                AbstractDungeon.actionManager.addToBottom(new SFXAction("ORB_LIGHTNING_EVOKE"));
                if (q.currentBlock > 0) {
                    atb(new RemoveAllBlockAction(q, p));
                } else {
                    applyToEnemy(q, autoVuln(q, magicNumber));
                    applyToEnemy(q, autoWeak(q, magicNumber));
                }
            }
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}