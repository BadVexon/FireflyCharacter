package theFirefly.cards;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

public class MindControl extends AbstractFireflyCard {

    public final static String ID = makeID("MindControl");

    //stupid intellij stuff SKILL, ENEMY, RARE

    public MindControl() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.getIntentBaseDmg() > -1 && !m.hasPower(ArtifactPower.POWER_ID)) {
            if ((boolean) ReflectionHacks.getPrivate(m, AbstractMonster.class, "isMultiDmg")) {
                for (int i = 0; i < (int) ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentMultiAmt"); i++) {
                    AbstractMonster q = AbstractDungeon.getRandomMonster();
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(q, new DamageInfo(m, m.getIntentDmg(), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

                }
            } else {
                AbstractMonster q = AbstractDungeon.getRandomMonster();
                AbstractDungeon.actionManager.addToBottom(new DamageAction(q, new DamageInfo(m, m.getIntentDmg(), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

            }
        }
        atb(new StunMonsterAction(m, p));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            exhaust = false;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}