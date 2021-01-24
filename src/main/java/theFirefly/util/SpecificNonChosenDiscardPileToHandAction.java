package theFirefly.util;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFirefly.powers.ErraticPower;

public class SpecificNonChosenDiscardPileToHandAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractCard cardToReturn;

    public SpecificNonChosenDiscardPileToHandAction(AbstractCard c) {
        this.p = AbstractDungeon.player;
        this.cardToReturn = c;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.p.hand.size() >= 10) {
            this.isDone = true;
        } else {
            if (this.p.hand.size() < 10) {
                this.p.hand.addToHand(cardToReturn);
                this.p.discardPile.removeCard(cardToReturn);
                if (p.hasPower(ErraticPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, p.getPower(ErraticPower.POWER_ID).amount));
                }
            }

            cardToReturn.lighten(false);
            this.p.hand.refreshHandLayout();
            this.isDone = true;
        }
        this.tickDuration();
    }
}