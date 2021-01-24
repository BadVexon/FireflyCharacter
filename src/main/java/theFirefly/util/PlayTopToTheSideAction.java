package theFirefly.util;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.QueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PlayTopToTheSideAction extends AbstractGameAction {
    private boolean exhaustCards;

    public PlayTopToTheSideAction(AbstractCreature target, boolean exhausts) {
        this.duration = Settings.ACTION_DUR_FAST;// 17
        this.actionType = ActionType.WAIT;// 18
        this.source = AbstractDungeon.player;// 19
        this.target = target;// 20
        this.exhaustCards = exhausts;// 21
    }// 22

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {// 26
            if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0) {// 27
                this.isDone = true;// 28
                return;// 29
            }

            if (AbstractDungeon.player.drawPile.isEmpty()) {// 32
                AbstractDungeon.actionManager.addToTop(new PlayTopToTheSideAction(this.target, this.exhaustCards));// 33
                AbstractDungeon.actionManager.addToTop(new EmptyDeckShuffleAction());// 34
                this.isDone = true;// 35
                return;// 36
            }

            if (!AbstractDungeon.player.drawPile.isEmpty()) {// 39
                AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();// 40
                AbstractDungeon.player.drawPile.group.remove(card);// 41
                AbstractDungeon.getCurrRoom().souls.remove(card);// 42
                if (card.cost > 0) {// 43
                    card.freeToPlayOnce = true;// 44
                }

                card.exhaustOnUseOnce = this.exhaustCards;// 46
                AbstractDungeon.player.limbo.group.add(card);// 47
                card.current_y = -200.0F * Settings.scale;// 48
                card.target_x = (float) Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F - 10.0F * Settings.scale;
                card.target_y = (float) Settings.HEIGHT / 2.0F;// 50
                card.targetAngle = 0.0F;// 51
                card.lighten(false);// 52
                card.drawScale = 0.12F;// 53
                card.targetDrawScale = 0.75F;// 54
                if (!card.canUse(AbstractDungeon.player, (AbstractMonster) this.target)) {// 56
                    if (this.exhaustCards) {// 57
                        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(card, AbstractDungeon.player.limbo));// 58
                    } else {
                        AbstractDungeon.actionManager.addToTop(new UnlimboAction(card));// 62
                        AbstractDungeon.actionManager.addToTop(new DiscardSpecificCardAction(card, AbstractDungeon.player.limbo));// 63
                        AbstractDungeon.actionManager.addToTop(new WaitAction(0.4F));// 65
                    }
                } else {
                    card.applyPowers();// 68
                    card.freeToPlayOnce = true;// 69
                    AbstractDungeon.actionManager.addToTop(new QueueCardAction(card, this.target));// 70
                    AbstractDungeon.actionManager.addToTop(new UnlimboAction(card));// 71
                    if (!Settings.FAST_MODE) {// 72
                        AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_MED));// 73
                    } else {
                        AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));// 75
                    }
                }
            }

            this.isDone = true;// 79
        }

    }// 81
}
