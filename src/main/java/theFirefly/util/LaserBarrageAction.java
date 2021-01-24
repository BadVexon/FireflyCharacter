package theFirefly.util;

import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class LaserBarrageAction extends AbstractXAction {

    private int bonusAmt;
    private AbstractCard myCard;

    public LaserBarrageAction(int bonusAmt, AbstractCard card) {
        this.bonusAmt = bonusAmt;
        myCard = card;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
    }

    @Override
    public void initialize(int totalAmount) {
        super.initialize(totalAmount);
        this.amount += bonusAmt;
    }

    public void update() {
        if (amount > 0) {
            for (int i = 0; i < amount; i++) {
                AbstractDungeon.actionManager.addToBottom(new AttackDamageRandomEnemyAction(myCard, AttackEffect.FIRE));
            }
        }

        this.isDone = true;
    }
}