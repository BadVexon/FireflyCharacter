package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.FissurePower;

public class SearingEnigma extends AbstractFireflyCard {

    public final static String ID = makeID("SearingEnigma");

    //stupid intellij stuff SKILL, ENEMY, UNCOMMON

    private static final int MAGIC = 10;
    private static final int UPG_MAGIC = 5;

    public SearingEnigma() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new FissurePower(m, magicNumber));
    }

    public void triggerOnEndOfTurnForPlayingCard() {
        this.superFlash();
        atb(new DamageAction(AbstractDungeon.player, new DamageInfo(null, 6, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
    }// 55

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}