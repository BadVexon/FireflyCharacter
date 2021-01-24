package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.util.LeerVFX;

public class ChargedLaser extends AbstractFireflyCard {

    public final static String ID = makeID("ChargedLaser");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 4;
    private static final int MAGIC = 6;

    public ChargedLaser() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new LeerVFX(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY, p.flipHorizontal)));
        dmg(m, makeInfo(), AbstractGameAction.AttackEffect.FIRE);
    }

    public void triggerWhenDrawn() {
        AbstractDungeon.actionManager.addToBottom(new ModifyDamageAction(this.uuid, this.magicNumber));
        this.superFlash();
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                applyPowers();
                initializeDescription();
                isDone = true;
            }
        });
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            initializeDescription();
        }
    }
}