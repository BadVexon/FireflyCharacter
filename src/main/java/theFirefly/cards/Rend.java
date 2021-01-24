package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.UnrealityPower;

public class Rend extends AbstractFireflyCard {

    public final static String ID = makeID("Rend");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    public Rend() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            applyToEnemy(m, new UnrealityPower(m, 5));
        }
        AbstractCard q = this;
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (m.hasPower(UnrealityPower.POWER_ID))
                    q.baseDamage = m.getPower(UnrealityPower.POWER_ID).amount;
                q.calculateCardDamage(m);
                AbstractDungeon.actionManager.addToTop(
                        new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
            }
        });
        if (!upgraded) {
            applyToEnemy(m, new UnrealityPower(m, 5));
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);// 92
        int i = 0;
        if (upgraded) i += 5;
        if (mo.hasPower(UnrealityPower.POWER_ID)) {
            i += mo.getPower(UnrealityPower.POWER_ID).amount;
        }
        this.baseDamage = i;
        this.rawDescription = DESCRIPTION;// 94
        this.rawDescription = this.rawDescription + " NL (Deals !D! damage.)";// 95
        this.initializeDescription();// 96
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}