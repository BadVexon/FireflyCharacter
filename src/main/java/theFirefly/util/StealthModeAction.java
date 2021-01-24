package theFirefly.util;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFirefly.powers.StealthPower;

public class StealthModeAction extends AbstractXAction {

    private int bonusAmt;

    public StealthModeAction(int bonus) {
        this.bonusAmt = bonus;
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
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StealthPower(AbstractDungeon.player, amount), amount));
        }

        this.isDone = true;
    }
}