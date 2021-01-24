package theFirefly.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ViceCrushEffect;

import java.util.ArrayList;

public class SoulStrike extends AbstractFireflyCard {

    public final static String ID = makeID("SoulStrike");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    public SoulStrike() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new ViceCrushEffect(m.hb.cX, m.hb.cY)));
        dmg(m, makeInfo(), AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    public void applyPowers() {
        ArrayList<String> IDList = new ArrayList<>();

        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (!IDList.contains(c.cardID)) {
                IDList.add(c.cardID);
            }
        }

        this.baseDamage = IDList.size() * this.magicNumber;// 77
        super.applyPowers();// 78
        this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0];// 79
        this.initializeDescription();// 80

    }// 82

    public void onMoveToDiscard() {
        this.rawDescription = DESCRIPTION;// 86
        this.initializeDescription();// 87
    }// 88

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);// 92
        this.rawDescription = DESCRIPTION;// 94
        this.rawDescription = this.rawDescription + EXTENDED_DESCRIPTION[0];// 95
        this.initializeDescription();// 96
    }// 97

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(2);
            initializeDescription();
        }
    }
}