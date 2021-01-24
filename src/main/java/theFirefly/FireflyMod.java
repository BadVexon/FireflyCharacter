package theFirefly;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import javassist.CtClass;
import javassist.Modifier;
import javassist.NotFoundException;
import org.clapper.util.classutil.*;
import theFirefly.relics.*;
import theFirefly.util.Quantum;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

import static com.megacrit.cardcrawl.core.Settings.language;

@SuppressWarnings({"ConstantConditions", "unused", "WeakerAccess"})
@SpireInitializer
public class FireflyMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        StartGameSubscriber {
    public static final String ARTIST_SHOULDER_1 = "thefireflyResources/images/char/artistChar/shoulder.png";
    public static final String ARTIST_SHOULDER_2 = "thefireflyResources/images/char/artistChar/shoulder2.png";
    public static final String ARTIST_CORPSE = "thefireflyResources/images/char/artistChar/corpse.png";
    private static final String ARTIST_ATTACK_ART = "thefireflyResources/images/512/canvas_attack_s.png";
    private static final String ARTIST_SKILL_ART = "thefireflyResources/images/512/canvas_skill_s.png";
    private static final String ARTIST_POWER_ART = "thefireflyResources/images/512/canvas_power_s.png";
    private static final String ARTIST_CARD_ENERGY_ORB = "thefireflyResources/images/512/card_default_gray_orb.png";
    private static final String ARTIST_TEXT_ENERGY_ORB = "thefireflyResources/images/512/card_small_orb.png";
    private static final String BIG_ARTIST_ATTACK_ART = "thefireflyResources/images/1024/canvas_attack.png";
    private static final String BIG_ARTIST_SKILL_ART = "thefireflyResources/images/1024/canvas_skill.png";
    private static final String BIG_ARTIST_POWER_ART = "thefireflyResources/images/1024/canvas_power.png";
    private static final String BIG_ARTIST_CARD_ENERGY_ORB = "thefireflyResources/images/1024/card_default_gray_orb.png";
    private static final String ARTIST_CHARSELECT_BUTTON = "thefireflyResources/images/charSelect/ArtistCharacterButton.png";
    private static final String ARTIST_CHARSELECT_PORTRAIT = "thefireflyResources/images/charSelect/ArtistCharacterBG.png";
    private static String modID;
    private static boolean thindone;

    public static boolean lastTurnAttacked;

    public static Color chemColor = new Color(0.1F, 0.2F, 0.3F, 1);

    public FireflyMod() {

        BaseMod.subscribe(this);


        modID = "thefirefly";

        BaseMod.addColor(TheFirefly.Enums.FIREFLY_CYAN, chemColor, chemColor, chemColor,
                chemColor, chemColor, chemColor, chemColor,
                ARTIST_ATTACK_ART, ARTIST_SKILL_ART, ARTIST_POWER_ART, ARTIST_CARD_ENERGY_ORB,
                BIG_ARTIST_ATTACK_ART, BIG_ARTIST_SKILL_ART, BIG_ARTIST_POWER_ART,
                BIG_ARTIST_CARD_ENERGY_ORB, ARTIST_TEXT_ENERGY_ORB);

    }

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String getModID() {
        return modID;
    }

    public static void initialize() {
        FireflyMod chemistMod = new FireflyMod();
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheFirefly("the Firefly", TheFirefly.Enums.THE_FIREFLY),
                ARTIST_CHARSELECT_BUTTON, ARTIST_CHARSELECT_PORTRAIT, TheFirefly.Enums.THE_FIREFLY);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new StealthSystem(), TheFirefly.Enums.FIREFLY_CYAN);
        BaseMod.addRelicToCustomPool(new AbsoluteTerror(), TheFirefly.Enums.FIREFLY_CYAN);
        BaseMod.addRelicToCustomPool(new LongRangeScanner(), TheFirefly.Enums.FIREFLY_CYAN);
        BaseMod.addRelicToCustomPool(new PhaseCrystal(), TheFirefly.Enums.FIREFLY_CYAN);
        BaseMod.addRelicToCustomPool(new PlasmaFairy(), TheFirefly.Enums.FIREFLY_CYAN);
        BaseMod.addRelicToCustomPool(new RepairArm(), TheFirefly.Enums.FIREFLY_CYAN);
        BaseMod.addRelicToCustomPool(new ShadowThruster(), TheFirefly.Enums.FIREFLY_CYAN);
        BaseMod.addRelicToCustomPool(new TimeCapsule(), TheFirefly.Enums.FIREFLY_CYAN);
        BaseMod.addRelicToCustomPool(new UpgradedSystem(), TheFirefly.Enums.FIREFLY_CYAN);
        BaseMod.addRelicToCustomPool(new WeaponPreloader(), TheFirefly.Enums.FIREFLY_CYAN);
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new Quantum());
        try {
            autoAddCards();
        } catch (URISyntaxException | IllegalAccessException | InstantiationException | NotFoundException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void autoAddCards()
            throws URISyntaxException, IllegalAccessException, InstantiationException, NotFoundException, ClassNotFoundException {
        ClassFinder finder = new ClassFinder();
        URL url = FireflyMod.class.getProtectionDomain().getCodeSource().getLocation();
        finder.add(new File(url.toURI()));

        ClassFilter filter =
                new AndClassFilter(
                        new NotClassFilter(new InterfaceOnlyClassFilter()),
                        new NotClassFilter(new AbstractClassFilter()),
                        new ClassModifiersClassFilter(Modifier.PUBLIC),
                        new CardFilter()
                );
        Collection<ClassInfo> foundClasses = new ArrayList<>();
        finder.findClasses(foundClasses, filter);

        for (ClassInfo classInfo : foundClasses) {
            CtClass cls = Loader.getClassPool().get(classInfo.getClassName());
            if (cls.hasAnnotation(CardIgnore.class)) {
                continue;
            }
            boolean isCard = false;
            CtClass superCls = cls;
            while (superCls != null) {
                superCls = superCls.getSuperclass();
                if (superCls == null) {
                    break;
                }
                if (superCls.getName().equals(AbstractCard.class.getName())) {
                    isCard = true;
                    break;
                }
            }
            if (!isCard) {
                continue;
            }
            System.out.println(classInfo.getClassName());
            AbstractCard card = (AbstractCard) Loader.getClassPool().getClassLoader().loadClass(cls.getName()).newInstance();
            BaseMod.addCard(card);
            if (cls.hasAnnotation(CardNoSeen.class)) {
                UnlockTracker.hardUnlockOverride(card.cardID);
            } else {
                UnlockTracker.unlockCard(card.cardID);
            }
        }
    }

    private String languageSupport() {
        if (language == Settings.GameLanguage.ZHS) {
            return "eng";
        }
        return "eng";
    }

    String path = "eng";

    @Override
    public void receiveEditStrings() {
        path = languageSupport();

        BaseMod.loadCustomStringsFile(CardStrings.class, getModID() + "Resources/localization/" + path + "/ArtistMod-Card-Strings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, getModID() + "Resources/localization/" + path + "/ArtistMod-Relic-Strings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, getModID() + "Resources/localization/" + path + "/ArtistMod-Character-Strings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/" + path + "/ArtistMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID.toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveStartGame() {
        LongRangeScanner.nodeList.clear();
    }
}
