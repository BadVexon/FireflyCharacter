package theFirefly.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.util.SpecificNonChosenDiscardPileToHandAction;

public class ReverseEngine extends AbstractFireflyCard {

    public final static String ID = makeID("ReverseEngine");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 3;

    public ReverseEngine() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (!p.discardPile.isEmpty()) {
            atb(new SpecificNonChosenDiscardPileToHandAction(AbstractDungeon.player.discardPile.getTopCard()));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
            initializeDescription();
        }
    }
}