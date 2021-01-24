package theFirefly.relics;

import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.map.MapRoomNode;
import theFirefly.FireflyMod;
import theFirefly.util.TextureLoader;

import java.util.HashMap;
import java.util.Map;

import static theFirefly.FireflyMod.makeRelicOutlinePath;
import static theFirefly.FireflyMod.makeRelicPath;

public class LongRangeScanner extends CustomRelic implements CustomSavable<Map<String, EventHelper.RoomResult>>  {

    public static final String ID = FireflyMod.makeID("LongRangeScanner");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("LongRangeScanner.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("LongRangeScanner.png"));

    public static Map<String, EventHelper.RoomResult> nodeList = new HashMap<>();
    public static MapRoomNode realCurrNode = null;

    public LongRangeScanner() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.HEAVY);
    }

    @Override
    public Map<String, EventHelper.RoomResult> onSave() {
        return nodeList;
    }

    @Override
    public void onLoad(Map<String, EventHelper.RoomResult> maptime) {
        for (String s : maptime.keySet()) {
            System.out.println("Loading in located ? room node at " + s + " with result " + maptime.get(s));
            nodeList.put(s, maptime.get(s));
        }
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
