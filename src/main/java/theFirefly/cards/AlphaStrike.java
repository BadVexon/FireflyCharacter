package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.PrecisionPower;
import theFirefly.powers.StealthPower;

public class AlphaStrike extends AbstractFireflyCard {

    public final static String ID = makeID("AlphaStrike");

    //stupid intellij stuff SKILL, SELF, RARE

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public AlphaStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        baseDamage = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (AbstractDungeon.player.hasPower(StealthPower.POWER_ID)) {
                    int x = AbstractDungeon.player.getPower(StealthPower.POWER_ID).amount;
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, StealthPower.POWER_ID));
                    applyToSelf(new PrecisionPower(p, x * 2));
                    AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, x * magicNumber));
                }
                isDone = true;
            }
        });
        dmg(m, makeInfo(), AbstractGameAction.AttackEffect.FIRE);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}