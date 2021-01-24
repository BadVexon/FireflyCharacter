package theFirefly.util;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShootAnythingAction extends AbstractGameAction {

    private ShootAnythingEffect effect;

    public ShootAnythingAction(AbstractMonster target, Texture texture) {
        this(target, texture, 15);
    }

    public ShootAnythingAction(AbstractMonster target, Texture texture, int time) {
        this.actionType = ActionType.SPECIAL;

        effect = new ShootAnythingEffect(target, texture, time);
        AbstractDungeon.effectList.add(effect);
    }


    public void update() {
        if (effect.finishedAction) {
            this.isDone = true;
        }
    }
}