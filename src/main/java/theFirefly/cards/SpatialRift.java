package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.CardIgnore;
import theFirefly.powers.StealthPower;

@CardIgnore
public class SpatialRift extends AbstractFireflyCard {

    public final static String ID = makeID("SpatialRift");

    //stupid intellij stuff ATTACK, ALL_ENEMY, RARE

    private static final int DAMAGE = 20;
    private static final int UPG_DAMAGE = 6;

    public SpatialRift() {
        super(ID, 4, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        isMultiDamage = true;
    }

    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player.hasPower(StealthPower.POWER_ID)) {
            this.costForTurn = Math.max(this.cost - AbstractDungeon.player.getPower(StealthPower.POWER_ID).amount, 0);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            initializeDescription();
        }
    }
}