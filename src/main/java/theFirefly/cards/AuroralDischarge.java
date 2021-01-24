package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AuroralDischarge extends AbstractFireflyCard {

    public final static String ID = makeID("AuroralDischarge");

    //stupid intellij stuff ATTACK, ALL_ENEMY, UNCOMMON

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 2;

    public AuroralDischarge() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                boolean bruh = false;
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c.type == CardType.POWER)
                        bruh = true;
                }
                if (bruh) {
                    AbstractCard c = new PlasmaWisp();
                    if (upgraded) c.upgrade();
                    makeInHand(c);
                }
            }
        });
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
        }
    }
}