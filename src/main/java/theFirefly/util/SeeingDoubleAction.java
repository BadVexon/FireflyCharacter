//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package theFirefly.util;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class SeeingDoubleAction extends AbstractGameAction {
    private AbstractPlayer p;
    private boolean upgrad;

    public SeeingDoubleAction(boolean upgraded) {
        source = AbstractDungeon.player;
        upgrad = upgraded;
        this.actionType = ActionType.DRAW;
        this.duration = 0.25F;
        this.p = AbstractDungeon.player;
    }

    public void update() {
        Iterator var1;
        if (this.duration == Settings.ACTION_DUR_FAST) {

            if (this.p.hand.group.size() == 1) {
                var1 = this.p.hand.group.iterator();

                while (var1.hasNext()) {
                    AbstractCard c = (AbstractCard) var1.next();
                    if (upgrad) c.freeToPlayOnce = true;
                    AbstractDungeon.actionManager.addToTop(new MakeTempCardInDrawPileAction(c.makeStatEquivalentCopy(), 1, false, false));
                    this.isDone = true;
                    return;
                }
            }

            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open("to copy into your hand and draw pile.", 1, false, false, false, false);
                this.tickDuration();
                return;
            }

            if (this.p.hand.group.size() == 1) {
                AbstractCard c = this.p.hand.getTopCard().makeStatEquivalentCopy();
                if (upgrad) c.freeToPlayOnce = true;
                AbstractDungeon.actionManager.addToTop(new MakeTempCardInDrawPileAction(c, 1, false, false));

                this.isDone = true;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();

            while (var1.hasNext()) {
                AbstractCard c = (AbstractCard) var1.next();
                AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
                if (upgrad) c.freeToPlayOnce = true;
                AbstractDungeon.actionManager.addToTop(new MakeTempCardInDrawPileAction(c.makeStatEquivalentCopy(), 1, false, false));
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }

        this.tickDuration();
    }
}
