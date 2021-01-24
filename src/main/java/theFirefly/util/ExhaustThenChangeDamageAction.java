package theFirefly.util;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theFirefly.cards.AbstractFireflyCard;

import java.util.UUID;

public class ExhaustThenChangeDamageAction extends AbstractGameAction {
    private AbstractPlayer p;
    private UUID bruh;

    public ExhaustThenChangeDamageAction(UUID cardUUID, int buffAmt) {
        this.p = AbstractDungeon.player;
        this.amount = buffAmt;
        this.duration = Settings.ACTION_DUR_FAST;// 36
        bruh = cardUUID;
        this.actionType = ActionType.EXHAUST;// 37
    }// 38

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {// 51
            if (this.p.hand.size() == 0) {// 54
                this.isDone = true;// 55
                return;// 56
            }

            if (this.p.hand.size() == amount) {
                for (int i = 0; i < amount; i++) {
                    AbstractCard c = AbstractDungeon.player.hand.getTopCard();
                    this.p.hand.moveToExhaustPile(c);// 90
                    if (c.cost > -1) {
                        for (AbstractCard q : GetAllInBattleInstances.get(bruh)) {
                            if (q instanceof AbstractFireflyCard) {
                                ((AbstractFireflyCard) q).baseQuantum += 8 * c.cost;
                                q.superFlash();
                                q.applyPowers();
                            }
                        }
                    } else if (c.cost == -1) {
                        for (AbstractCard q : GetAllInBattleInstances.get(bruh)) {
                            if (q instanceof AbstractFireflyCard) {
                                ((AbstractFireflyCard) q).baseQuantum += 8 * EnergyPanel.getCurrentEnergy();
                                q.superFlash();
                                q.applyPowers();
                            }
                        }
                    }
                }
                isDone = true;
                return;
            }

            AbstractDungeon.handCardSelectScreen.open("Exhaust for Photon Blast", Math.min(AbstractDungeon.player.hand.size(), amount), false, false);// 80
            this.tickDuration();// 81
            return;// 82
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {// 88
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.p.hand.moveToExhaustPile(c);// 90
                if (c.cost > -1) {
                    for (AbstractCard q : GetAllInBattleInstances.get(bruh)) {
                        if (q instanceof AbstractFireflyCard) {
                            ((AbstractFireflyCard) q).baseQuantum += 8 * c.cost;
                            q.superFlash();
                            q.applyPowers();
                        }
                    }
                } else if (c.cost == -1) {
                    for (AbstractCard q : GetAllInBattleInstances.get(bruh)) {
                        if (q instanceof AbstractFireflyCard) {
                            ((AbstractFireflyCard) q).baseQuantum += 8 * EnergyPanel.getCurrentEnergy();
                            q.superFlash();
                            q.applyPowers();
                        }
                    }
                }
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;// 93
        }

        this.tickDuration();// 96
    }// 97
}
