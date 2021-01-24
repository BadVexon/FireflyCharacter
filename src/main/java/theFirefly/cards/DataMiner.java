package theFirefly.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class DataMiner extends AbstractFireflyCard {

    public final static String ID = makeID("DataMiner");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public DataMiner() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
    }

    @Override
    public void triggerWhenDrawn() {
        shuffleIn(this.makeStatEquivalentCopy());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c;
        if (upgraded) {
            c = AbstractDungeon.returnTrulyRandomCardInCombat();
        } else {
            ArrayList<AbstractCard> cardList = new ArrayList<>(AbstractDungeon.srcCommonCardPool.group);
            c = cardList.get(AbstractDungeon.cardRandomRng.random(cardList.size() - 1));
        }
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, true));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}