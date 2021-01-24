
package theFirefly.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.ExhaustBlurEffect;
import com.megacrit.cardcrawl.vfx.ExhaustEmberEffect;

public class FadeAway extends AbstractGameEffect {
    private AbstractCreature c;
    private static final float DUR = 1.0F;

    public FadeAway(AbstractCreature c) {
        this.duration = 1.0F;// 17
        this.c = c;// 18
    }// 19

    public void update() {
        if (this.duration == 1.0F) {// 22
            CardCrawlGame.sound.play("CARD_EXHAUST", 0.2F);// 23

            for (int i = 0; i < 90; ++i) {// 24
                AbstractDungeon.effectsQueue.add(new ExhaustBlurEffect(this.c.hb.x + MathUtils.random(this.c.hb.width), c.hb.y));// 25
            }

            for (int i = 0; i < 50; ++i) {// 27
                AbstractDungeon.effectsQueue.add(new ExhaustEmberEffect(this.c.hb.x + MathUtils.random(this.c.hb.width), c.hb.y));// 28
            }
        }

        this.duration -= Gdx.graphics.getDeltaTime();// 32

        if (this.duration < 0.0F) {// 38
            this.isDone = true;// 39
        }

    }// 42

    public void render(SpriteBatch sb) {
    }// 47

    public void dispose() {
    }// 52
}
