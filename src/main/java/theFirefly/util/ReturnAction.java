package theFirefly.util;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFirefly.powers.ErraticPower;

public class ReturnAction extends AbstractGameAction {
    private AbstractPlayer p;
    private boolean isRandom;
    public static int numDiscarded;
    private static final float DURATION = Settings.ACTION_DUR_XFAST;

    public ReturnAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom) {
        this.p = (AbstractPlayer) target;// 32
        this.isRandom = isRandom;// 33
        this.setValues(target, source, amount);// 34
        this.actionType = ActionType.DISCARD;// 35
        this.duration = DURATION;// 37
    }// 38

    public void update() {
        if (this.duration == DURATION) {// 42
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {// 43
                this.isDone = true;// 44
                return;// 45
            }

            if (this.p.hand.size() <= this.amount) {// 48
                this.amount = this.p.hand.size();// 49
                for (int i = 0; i < this.p.hand.size(); ++i) {// 52
                    AbstractCard c = this.p.hand.getTopCard();// 53
                    this.p.hand.moveToDeck(c, true);
                }
                if (p.hasPower(ErraticPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, p.getPower(ErraticPower.POWER_ID).amount));
                }

                AbstractDungeon.player.hand.applyPowers();// 61
                this.tickDuration();// 62
                return;// 63
            }

            if (!this.isRandom) {// 66
                if (this.amount < 0) {// 73
                    AbstractDungeon.handCardSelectScreen.open("shuffle into your draw pile.", 99, true, true);// 74
                    AbstractDungeon.player.hand.applyPowers();// 75
                    this.tickDuration();// 76
                    return;// 77
                }

                numDiscarded = this.amount;// 79
                if (this.p.hand.size() > this.amount) {// 80
                    AbstractDungeon.handCardSelectScreen.open("shuffle into your draw pile.", this.amount, false);// 81
                }

                AbstractDungeon.player.hand.applyPowers();// 84
                this.tickDuration();// 85
                return;// 86
            }

            for (int i = 0; i < this.amount; ++i) {// 67
                AbstractCard c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);// 68
                this.p.hand.moveToDeck(c, true);
                if (p.hasPower(ErraticPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, p.getPower(ErraticPower.POWER_ID).amount));
                }
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {// 92
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.p.hand.moveToDeck(c, true);
            }
            if (p.hasPower(ErraticPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, p.getPower(ErraticPower.POWER_ID).amount));
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;// 100
        }

        this.tickDuration();// 103
    }// 104
}
