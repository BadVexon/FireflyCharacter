package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.StealthPower;
import theFirefly.util.ShootAnythingAction;

public class DecoyShot extends AbstractFireflyCard {

    public final static String ID = makeID("DecoyShot");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 4;


    public DecoyShot() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.player.relics.isEmpty())
            atb(new ShootAnythingAction(m, AbstractDungeon.player.relics.get(AbstractDungeon.cardRandomRng.random(AbstractDungeon.player.relics.size() - 1)).img));
        dmg(m, makeInfo(), AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        applyToSelf(new StealthPower(p, 1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
            initializeDescription();
        }
    }
}