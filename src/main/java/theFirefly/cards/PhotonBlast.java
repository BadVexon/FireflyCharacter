package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.util.ExhaustThenChangeDamageAction;

public class PhotonBlast extends AbstractFireflyCard {

    public final static String ID = makeID("PhotonBlast");

    //stupid intellij stuff Skill, NONE, BASIC

    private static final int DAMAGE = 4;

    private static final int MAGIC = 1;

    public PhotonBlast() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        baseQuantum = quantum = DAMAGE;
        isEthereal = true;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ExhaustThenChangeDamageAction(this.uuid, this.magicNumber));
    }

    @Override
    public void triggerOnExhaust() {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(baseQuantum, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}