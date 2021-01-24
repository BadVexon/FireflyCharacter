package theFirefly;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import theFirefly.cards.Defend;
import theFirefly.cards.PhotonBlast;
import theFirefly.cards.StealthMode;
import theFirefly.cards.Strike;
import theFirefly.relics.StealthSystem;
import theFirefly.util.TextureLoader;

import java.util.ArrayList;
import java.util.List;

import static theFirefly.FireflyMod.*;
import static theFirefly.TheFirefly.Enums.FIREFLY_CYAN;

public class TheFirefly extends CustomPlayer {
    private static final String[] orbTextures = {
            "thefireflyResources/images/char/artistChar/orb/layer1.png",
            "thefireflyResources/images/char/artistChar/orb/layer2.png",
            "thefireflyResources/images/char/artistChar/orb/layer3.png",
            "thefireflyResources/images/char/artistChar/orb/layer4.png",
            "thefireflyResources/images/char/artistChar/orb/layer5.png",
            "thefireflyResources/images/char/artistChar/orb/layer6.png",
            "thefireflyResources/images/char/artistChar/orb/layer1d.png",
            "thefireflyResources/images/char/artistChar/orb/layer2d.png",
            "thefireflyResources/images/char/artistChar/orb/layer3d.png",
            "thefireflyResources/images/char/artistChar/orb/layer4d.png",
            "thefireflyResources/images/char/artistChar/orb/layer5d.png",};
    private static final String ID = makeID("theFirefly");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;


    public TheFirefly(String name, PlayerClass setClass) {
        super(name, setClass, new CustomEnergyOrb(orbTextures, "thefireflyResources/images/char/artistChar/orb/vfx.png", null), new SpriterAnimation(
                "thefireflyResources/images/char/artistChar/artist.scml"));
        initializeClass(null,
                ARTIST_SHOULDER_1,
                ARTIST_SHOULDER_2,
                ARTIST_CORPSE,
                getLoadout(), 20.0F, -10.0F, 200.0F, 300.0F, new EnergyManager(3));


        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 240.0F * Settings.scale);
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                55, 55, 0, 99, 5, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(StealthMode.ID);
        retVal.add(PhotonBlast.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(StealthSystem.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("UNLOCK_PING", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "UNLOCK_PING";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return FIREFLY_CYAN;
    }

    @Override
    public Color getCardTrailColor() {
        return chemColor.cpy();
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new StealthMode();
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheFirefly(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return chemColor.cpy();
    }

    @Override
    public Color getSlashAttackColor() {
        return chemColor.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.FIRE};
    }

    @Override
    public Texture getCutsceneBg() {
        return TextureLoader.getTexture("thefireflyResources/images/cards/bg.png");
    }

    @Override
    public List<CutscenePanel> getCutscenePanels() {
        List<CutscenePanel> panels = new ArrayList<>();
        CardCrawlGame.sound.playA("ATTACK_MAGIC_FAST_3", -0.4F);// 46
        panels.add(new CutscenePanel("thefireflyResources/images/cards/credit0.png"));
        panels.add(new CutscenePanel("thefireflyResources/images/cards/credit1.png"));
        panels.add(new CutscenePanel("thefireflyResources/images/cards/credit2.png"));
        panels.add(new CutscenePanel("thefireflyResources/images/cards/credit3.png"));
        return panels;
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_FIREFLY;
        @SpireEnum(name = "FIREFLY_COLOR")
        public static AbstractCard.CardColor FIREFLY_CYAN;
        @SpireEnum(name = "FIREFLY_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }
}
