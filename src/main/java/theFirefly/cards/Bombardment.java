package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.util.ShootAnythingAction;

public class Bombardment extends AbstractFireflyCard {

    public final static String ID = makeID("Bombardment");

    //stupid intellij stuff ATTACK, ALL_ENEMY, UNCOMMON

    private static final int DAMAGE = 10;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public Bombardment() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    AbstractMonster q = AbstractDungeon.getRandomMonster();
                    if (!AbstractDungeon.player.relics.isEmpty())
                        atb(new ShootAnythingAction(q, AbstractDungeon.player.relics.get(AbstractDungeon.cardRandomRng.random(AbstractDungeon.player.relics.size() - 1)).img));
                    atb(new DamageAction(q, makeInfo(), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                }
            });
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
            initializeDescription();
        }
    }
}