package theFirefly.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFirefly.FireflyMod;
import theFirefly.powers.StealthPower;
import theFirefly.util.TextureLoader;

import static theFirefly.FireflyMod.makeRelicOutlinePath;
import static theFirefly.FireflyMod.makeRelicPath;

public class UpgradedSystem extends CustomRelic {

    public static final String ID = FireflyMod.makeID("UpgradedSystem");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("UpgradedSystem.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("UpgradedSystem.png"));

    public UpgradedSystem() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.HEAVY);
        this.counter = -1;
    }

    public void onVictory() {
        this.counter = -1;
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(StealthSystem.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(StealthSystem.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(StealthSystem.ID);
    }

    @Override
    public String getUpdatedDescription()
    {
        // Colorize the starter relic's name
        String name = new StealthSystem().name;
        StringBuilder sb = new StringBuilder();
        for (String word : name.split(" ")) {
            sb.append("[#").append(FireflyMod.chemColor.toString()).append("]").append(word).append("[] ");
        }
        sb.setLength(sb.length()-1);
        sb.append("[#").append(FireflyMod.chemColor.toString()).append("]");

        return DESCRIPTIONS[0] + sb.toString() + DESCRIPTIONS[1];
    }

    public void atBattleStart() {
        this.counter = 0;
    }

    public void atTurnStart() {
        if (this.counter == 2) {
            this.counter = 1;
        } else
            this.counter++;
        if (this.counter == 2) {
            applyToSelf(new StealthPower(AbstractDungeon.player, 1));
        }
    }

    public void applyToSelf(AbstractPower po) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount));
    }
}
