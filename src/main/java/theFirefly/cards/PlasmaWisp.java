package theFirefly.cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import theFirefly.CardIgnore;

@CardIgnore
public class PlasmaWisp extends AbstractFireflyCard {

    public final static String ID = makeID("PlasmaWisp");

    //stupid intellij stuff ATTACK, ENEMY, SPECIAL

    private static final int DAMAGE = 18;
    private static final int UPG_DAMAGE = 6;

    public PlasmaWisp() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = DAMAGE;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new FireballEffect(p.hb.cX, p.hb.cY + MathUtils.random(200, 250) * Settings.scale, m.hb.cX, m.hb.cY), 0.5f));
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