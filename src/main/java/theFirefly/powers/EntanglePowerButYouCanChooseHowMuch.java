package theFirefly.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.EntanglePower;

public class EntanglePowerButYouCanChooseHowMuch extends EntanglePower {
    public EntanglePowerButYouCanChooseHowMuch(int amount2) {
        super(AbstractDungeon.player);
        this.amount = amount2;
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {// 38
            if (this.amount == 0) {// 46
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));// 47
            } else {
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this, 1));// 49
            }
        }
    }
}
