package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.CardIgnore;
import theFirefly.util.ShootAnythingAction;

@CardIgnore
public class Missile extends AbstractFireflyCard {

    public final static String ID = makeID("Missile");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 12;
    private static final int UPG_DAMAGE = 4;

    public Missile() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new RemoveAllBlockAction(m, p));
        if (!AbstractDungeon.player.relics.isEmpty())
            atb(new ShootAnythingAction(m, AbstractDungeon.player.relics.get(AbstractDungeon.cardRandomRng.random(AbstractDungeon.player.relics.size() - 1)).img));
        dmg(m, makeInfo(), AbstractGameAction.AttackEffect.FIRE);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            initializeDescription();
        }
    }
}