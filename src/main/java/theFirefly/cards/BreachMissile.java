package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.util.ShootAnythingAction;

public class BreachMissile extends AbstractFireflyCard {

    public final static String ID = makeID("BreachMissile");

    //stupid intellij stuff ATTACK, ENEMY, RARE

    private static final int DAMAGE = 20;
    private static final int UPG_DAMAGE = 10;

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public BreachMissile() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.player.relics.isEmpty())
            atb(new ShootAnythingAction(m, AbstractDungeon.player.relics.get(AbstractDungeon.cardRandomRng.random(AbstractDungeon.player.relics.size() - 1)).img, 20));
        dmg(m, makeInfo(), AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        applyToEnemy(m, autoVuln(m, magicNumber));
        applyToEnemy(m, autoWeak(m, magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            upgradeMagicNumber(UPG_MAGIC);
            initializeDescription();
        }
    }
}