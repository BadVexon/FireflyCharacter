package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.util.ShootAnythingAction;

public class Kugelblitz extends AbstractFireflyCard {

    public final static String ID = makeID("Kugelblitz");

    //stupid intellij stuff ATTACK, ALL_ENEMY, RARE

    private static final int DAMAGE = 32;
    private static final int UPG_DAMAGE = 8;

    private static final int MAGIC = 10;

    public Kugelblitz() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.player.relics.isEmpty()) {
            for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!q.isDead && !q.isDying) {
                    AbstractDungeon.actionManager.addToBottom(new ShootAnythingAction(q, AbstractDungeon.player.relics.get(AbstractDungeon.cardRandomRng.random(AbstractDungeon.player.relics.size() - 1)).img));
                }
            }
        }
        allDmg(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(p, 10, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            initializeDescription();
        }
    }
}