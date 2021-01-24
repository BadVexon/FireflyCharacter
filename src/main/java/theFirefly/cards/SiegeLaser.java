package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.CardIgnore;
import theFirefly.util.LeerVFX;

@CardIgnore
public class SiegeLaser extends AbstractFireflyCard {

    public final static String ID = makeID("SiegeLaser");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 3;

    public SiegeLaser() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new LeerVFX(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY, p.flipHorizontal)));
        dmg(m, makeInfo(), AbstractGameAction.AttackEffect.FIRE);
        if (m.getIntentBaseDmg() <= -1) {
            dmg(m, makeInfo(), AbstractGameAction.AttackEffect.FIRE);
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            initializeDescription();
        }
    }
}