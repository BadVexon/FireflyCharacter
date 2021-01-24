package theFirefly.util;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFirefly.powers.ErraticPower;

import java.util.Iterator;

public class RetrieveAction extends AbstractGameAction {
    private AbstractPlayer p;

    public RetrieveAction(int amount) {
        this.p = AbstractDungeon.player;// 15
        this.setValues(this.p, AbstractDungeon.player, amount);// 16
        this.actionType = ActionType.CARD_MANIPULATION;// 17
    }// 18

    public void update() {
        if (this.p.hand.size() >= 10 || p.discardPile.isEmpty()) {// 22
            if (p.hasPower(ErraticPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, p.getPower(ErraticPower.POWER_ID).amount));
            }
            this.isDone = true;// 23
        } else if (this.p.discardPile.size() == 1) {// 27
            AbstractCard card = (AbstractCard)this.p.discardPile.group.get(0);// 28
            if (this.p.hand.size() < 10) {// 29
                this.p.hand.addToHand(card);// 30
                this.p.discardPile.removeCard(card);// 31
                if (p.hasPower(ErraticPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, p.getPower(ErraticPower.POWER_ID).amount));
                }
            }

            card.lighten(false);// 33
            this.p.hand.refreshHandLayout();// 34
            this.isDone = true;// 35
        } else if (this.duration == 0.5F) {// 39
            AbstractDungeon.gridSelectScreen.open(this.p.discardPile, this.amount, "Choose a card to return to your hand.", false);// 40
            this.tickDuration();// 41
        } else {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {// 47
                Iterator var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();// 48

                AbstractCard c;
                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    if (this.p.hand.size() < 10) {// 49
                        this.p.hand.addToHand(c);// 50
                        this.p.discardPile.removeCard(c);// 51
                        if (p.hasPower(ErraticPower.POWER_ID)) {
                            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, p.getPower(ErraticPower.POWER_ID).amount));
                        }
                    }

                    c.lighten(false);// 53
                    c.unhover();// 54
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();// 56
                this.p.hand.refreshHandLayout();// 57

                for(var1 = this.p.discardPile.group.iterator(); var1.hasNext(); c.target_y = 0.0F) {// 59 62
                    c = (AbstractCard)var1.next();
                    c.unhover();// 60
                    c.target_x = (float)CardGroup.DISCARD_PILE_X;// 61
                }

                this.isDone = true;// 64
            }

            this.tickDuration();// 67
        }
    }// 24 36 42 68
}
