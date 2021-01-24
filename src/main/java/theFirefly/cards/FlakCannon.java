package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.util.ShootAnythingEffect;

public class FlakCannon extends AbstractFireflyCard {

    public final static String ID = makeID("FlakCannon");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 14;
    private static final int UPG_DAMAGE = 4;

    public FlakCannon() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 3; i++) {
            AbstractDungeon.effectsQueue.add(new ShootAnythingEffect(m, AbstractDungeon.player.relics.get(AbstractDungeon.cardRandomRng.random(AbstractDungeon.player.relics.size() - 1)).img, 10));
        }
        dmg(m, makeInfo(), AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        atb(new DiscardAction(p, p, 1, true));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            initializeDescription();
        }
    }
}