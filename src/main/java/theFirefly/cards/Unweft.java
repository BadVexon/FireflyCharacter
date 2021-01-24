package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFirefly.powers.FissurePower;
import theFirefly.powers.StealthPower;

public class Unweft extends AbstractFireflyCard {

    public final static String ID = makeID("Unweft");

    //stupid intellij stuff SKILL, SELF, RARE

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;

    public Unweft() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (AbstractDungeon.player.hasPower(StealthPower.POWER_ID)) {
                    int x = AbstractDungeon.player.getPower(StealthPower.POWER_ID).amount;
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, StealthPower.POWER_ID));
                    for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters)
                        applyToEnemy(q, new FissurePower(q, x * magicNumber));
                }
                isDone = true;
            }
        });
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}