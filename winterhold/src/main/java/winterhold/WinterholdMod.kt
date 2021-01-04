package winterhold

import basemod.*
import basemod.helpers.RelicType
import basemod.interfaces.EditCardsSubscriber
import basemod.interfaces.EditCharactersSubscriber
import basemod.interfaces.EditKeywordsSubscriber
import basemod.interfaces.EditRelicsSubscriber
import basemod.interfaces.EditStringsSubscriber
import basemod.interfaces.OnStartBattleSubscriber
import basemod.interfaces.PostInitializeSubscriber
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.evacipated.cardcrawl.mod.stslib.Keyword
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer
import com.google.gson.Gson
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.helpers.CardHelper
import com.megacrit.cardcrawl.helpers.FontHelper
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.localization.CharacterStrings
import com.megacrit.cardcrawl.localization.EventStrings
import com.megacrit.cardcrawl.localization.OrbStrings
import com.megacrit.cardcrawl.localization.PotionStrings
import com.megacrit.cardcrawl.localization.PowerStrings
import com.megacrit.cardcrawl.localization.RelicStrings
import com.megacrit.cardcrawl.rooms.AbstractRoom
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import winterhold.cards.AbstractWinterholdCard
import winterhold.characters.DestructionMage
import winterhold.relics.AbstractWinterholdRelic
import winterhold.spelldamage.SpellDamageHelper
import winterhold.util.IDCheckDontTouchPls
import winterhold.util.TextureLoader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.*


@SpireInitializer
class WinterholdMod : EditCardsSubscriber, EditRelicsSubscriber, EditStringsSubscriber, EditKeywordsSubscriber,
    EditCharactersSubscriber, PostInitializeSubscriber, OnStartBattleSubscriber {
    // ============== /SUBSCRIBE, CREATE THE COLOR_GRAY, INITIALIZE/ =================
    // =============== LOAD THE CHARACTER =================
    override fun receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + DestructionMage.Enums.DESTRUCTION_MAGE.toString())
        BaseMod.addCharacter(
            DestructionMage("the Default", DestructionMage.Enums.DESTRUCTION_MAGE),
            THE_DEFAULT_BUTTON, THE_DEFAULT_PORTRAIT, DestructionMage.Enums.DESTRUCTION_MAGE
        )
        receiveEditPotions()
        logger.info("Added " + DestructionMage.Enums.DESTRUCTION_MAGE.toString())
    }

    // =============== /LOAD THE CHARACTER/ =================
    // =============== POST-INITIALIZE =================
    override fun receivePostInitialize() {
        logger.info("Loading badge image and mod options")

        // Load the Mod Badge
        val badgeTexture = TextureLoader.getTexture(BADGE_IMAGE)

        // Create the Mod Menu
        val settingsPanel = ModPanel()

        // Create the on/off button:
        val enableNormalsButton = ModLabeledToggleButton(
            "This is the text which goes next to the checkbox.",
            350.0f,
            700.0f,
            Settings.CREAM_COLOR,
            FontHelper.charDescFont,  // Position (trial and error it), color, font
            enablePlaceholder,  // Boolean it uses
            settingsPanel,  // The mod panel in which this button will be in
            { }
        )
        { button: ModToggleButton ->  // The actual button:
            enablePlaceholder = button.enabled // The boolean true/false will be whether the button is enabled or not
            try {
                // And based on that boolean, set the settings and save them
                val config = SpireConfig("defaultMod", "theDefaultConfig", theDefaultDefaultSettings)
                config.setBool(ENABLE_PLACEHOLDER_SETTINGS, enablePlaceholder)
                config.save()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        settingsPanel.addUIElement(enableNormalsButton) // Add the button to the settings panel. Button is a go.
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel)

        // =============== EVENTS =================
        // https://github.com/daviscook477/BaseMod/wiki/Custom-Events


        // =============== /EVENTS/ =================
        logger.info("Done loading badge Image and mod options")
    }

    // =============== / POST-INITIALIZE/ =================
    // ================ ADD POTIONS ===================
    private fun receiveEditPotions() {
        logger.info("Beginning to edit potions")


        logger.info("Done editing potions")
    }

    // ================ /ADD POTIONS/ ===================
    // ================ ADD RELICS ===================
    override fun receiveEditRelics() {
        logger.info("Adding relics")
        AutoAdd("Winterhold")
            .packageFilter(AbstractWinterholdRelic::class.java)
            .any(AbstractWinterholdRelic::class.java) { _: AutoAdd.Info, relic: AbstractWinterholdRelic ->
                if (relic.color == null) {
                    BaseMod.addRelic(relic, RelicType.SHARED)
                } else {
                    BaseMod.addRelicToCustomPool(relic, relic.color)
                }
            }
        logger.info("Done adding relics!")
    }

    // ================ /ADD RELICS/ ===================
    // ================ ADD CARDS ===================
    override fun receiveEditCards() {
        logger.info("Adding variables")
        pathCheck()
        // Add the Custom Dynamic Variables
        logger.info("Add variables")
        // Add the Custom Dynamic variables
        logger.info("Adding cards")
        // Add the cards
        // Don't delete these default cards yet. You need 1 of each type and rarity (technically) for your game not to crash
        // when generating card rewards/shop screen items.

        // This method automatically adds any cards so you don't have to manually load them 1 by 1
        // For more specific info, including how to exclude cards from being added:
        // https://github.com/daviscook477/BaseMod/wiki/AutoAdd

        // The ID for this function isn't actually your modid as used for prefixes/by the getModID() method.
        // It's the mod id you give MTS in ModTheSpire.json - by default your artifact ID in your pom.xml

        // ${project.artifactId} from pom.xml
        AutoAdd("Winterhold")
            .packageFilter(AbstractWinterholdCard::class.java)
            .setDefaultSeen(true)
            .cards()

        // .setDefaultSeen(true) unlocks the cards
        // This is so that they are all "seen" in the library,
        // for people who like to look at the card list before playing your mod
        logger.info("Done adding cards!")
    }

    // ================ /ADD CARDS/ ===================
    // ================ LOAD THE TEXT ===================
    override fun receiveEditStrings() {
        logger.info("You seeing this?")
        logger.info("Beginning to edit strings for mod with ID: $modID")

        // CardStrings
        BaseMod.loadCustomStringsFile(
            CardStrings::class.java,
            modID + "Resources/localization/eng/Winterhold-Card-Strings.json"
        )

        // PowerStrings
        BaseMod.loadCustomStringsFile(
            PowerStrings::class.java,
            modID + "Resources/localization/eng/Winterhold-Power-Strings.json"
        )

        // RelicStrings
        BaseMod.loadCustomStringsFile(
            RelicStrings::class.java,
            modID + "Resources/localization/eng/Winterhold-Relic-Strings.json"
        )

        // Event Strings
        BaseMod.loadCustomStringsFile(
            EventStrings::class.java,
            modID + "Resources/localization/eng/Winterhold-Event-Strings.json"
        )

        // PotionStrings
        BaseMod.loadCustomStringsFile(
            PotionStrings::class.java,
            modID + "Resources/localization/eng/Winterhold-Potion-Strings.json"
        )

        // CharacterStrings
        BaseMod.loadCustomStringsFile(
            CharacterStrings::class.java,
            modID + "Resources/localization/eng/Winterhold-Character-Strings.json"
        )

        // OrbStrings
        BaseMod.loadCustomStringsFile(
            OrbStrings::class.java,
            modID + "Resources/localization/eng/Winterhold-Orb-Strings.json"
        )
        logger.info("Done edittting strings")
    }

    // ================ /LOAD THE TEXT/ ===================
    // ================ LOAD THE KEYWORDS ===================
    override fun receiveEditKeywords() {
        // Keywords on cards are supposed to be Capitalized, while in Keyword-String.json they're lowercase
        //
        // Multiword keywords on cards are done With_Underscores
        //
        // If you're using multiword keywords, the first element in your NAMES array in your keywords-strings.json has to be the same as the PROPER_NAME.
        // That is, in Card-Strings.json you would have #yA_Long_Keyword (#y highlights the keyword in yellow).
        // In Keyword-Strings.json you would have PROPER_NAME as A Long Keyword and the first element in NAMES be a long keyword, and the second element be a_long_keyword
        val gson = Gson()
        val json = Gdx.files.internal(modID + "Resources/localization/eng/Winterhold-Keyword-Strings.json")
            .readString(StandardCharsets.UTF_8.toString())
        val keywords = gson
            .fromJson(json, Array<Keyword>::class.java)
        if (keywords != null) {
            for (keyword in keywords) {
                BaseMod.addKeyword(modID!!.toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION)
                //  getModID().toLowerCase() makes your keyword mod specific (it won't show up in other cards that use that word)
            }
        }
    }

    override fun receiveOnBattleStart(abstractRoom: AbstractRoom) {
        SpellDamageHelper.resetCombo()
    }

    companion object {
        val logger: Logger = LogManager.getLogger(WinterholdMod::class.java.name)

        var modID: String? = null
            set(value) {
                val exceptionStrings = getExceptionStrings()
                logger.info("You are attempting to set your mod ID as: $value")
                field = when (value) {
                    exceptionStrings.DEFAULTID -> throw RuntimeException(exceptionStrings.EXCEPTION)
                    exceptionStrings.DEVID -> exceptionStrings.DEFAULTID
                    else -> value
                }
                logger.info("Success! ID is $modID")
            }

        // Mod-settings settings. This is if you want an on/off savable button
        var theDefaultDefaultSettings = Properties()
        const val ENABLE_PLACEHOLDER_SETTINGS = "enablePlaceholder"
        var enablePlaceholder = true // The boolean we'll be setting on/off (true/false)

        //This is for the in-game mod settings panel.
        private const val MODNAME = "Winterhold"
        private const val AUTHOR = "Tijmen"
        private const val DESCRIPTION = "Play as a mage from the College of Winterhold."

        // =============== INPUT TEXTURE LOCATION =================
        // Colors (RGB)
        // Character Color
        val DEFAULT_GRAY: Color = CardHelper.getColor(64.0f, 70.0f, 70.0f)

        // Potion Colors in RGB
        val PLACEHOLDER_POTION_LIQUID: Color = CardHelper.getColor(209.0f, 53.0f, 18.0f) // Orange-ish Red
        val PLACEHOLDER_POTION_HYBRID: Color = CardHelper.getColor(255.0f, 230.0f, 230.0f) // Near White
        val PLACEHOLDER_POTION_SPOTS: Color = CardHelper.getColor(100.0f, 25.0f, 10.0f) // Super Dark Red/Brown

        // Card backgrounds - The actual rectangular card.
        private const val ATTACK_DEFAULT_GRAY = "winterholdResources/images/512/bg_attack_default_gray.png"
        private const val SKILL_DEFAULT_GRAY = "winterholdResources/images/512/bg_skill_default_gray.png"
        private const val POWER_DEFAULT_GRAY = "winterholdResources/images/512/bg_power_default_gray.png"
        private const val ENERGY_ORB_DEFAULT_GRAY = "winterholdResources/images/512/card_default_gray_orb.png"
        private const val CARD_ENERGY_ORB = "winterholdResources/images/512/card_small_orb.png"
        private const val ATTACK_DEFAULT_GRAY_PORTRAIT = "winterholdResources/images/1024/bg_attack_default_gray.png"
        private const val SKILL_DEFAULT_GRAY_PORTRAIT = "winterholdResources/images/1024/bg_skill_default_gray.png"
        private const val POWER_DEFAULT_GRAY_PORTRAIT = "winterholdResources/images/1024/bg_power_default_gray.png"
        private const val ENERGY_ORB_DEFAULT_GRAY_PORTRAIT = "winterholdResources/images/1024/card_default_gray_orb.png"

        // Character assets
        private const val THE_DEFAULT_BUTTON = "winterholdResources/images/charSelect/DefaultCharacterButton.png"
        private const val THE_DEFAULT_PORTRAIT = "winterholdResources/images/charSelect/DefaultCharacterPortraitBG.png"
        const val THE_DEFAULT_SHOULDER_1 = "winterholdResources/images/char/destructionMage/shoulder.png"
        const val THE_DEFAULT_SHOULDER_2 = "winterholdResources/images/char/destructionMage/shoulder2.png"
        const val THE_DEFAULT_CORPSE = "winterholdResources/images/char/destructionMage/corpse.png"

        //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
        const val BADGE_IMAGE = "winterholdResources/images/Badge.png"

        // Atlas and JSON files for the Animations
        const val THE_DEFAULT_SKELETON_ATLAS = "winterholdResources/images/char/destructionMage/skeleton.atlas"
        const val THE_DEFAULT_SKELETON_JSON = "winterholdResources/images/char/destructionMage/skeleton.json"

        // =============== MAKE IMAGE PATHS =================
        @JvmStatic
        fun makeCardPath(resourcePath: String): String {
            return modID + "Resources/images/cards/" + resourcePath
        }

        @JvmStatic
        fun makeRelicPath(resourcePath: String): String {
            return modID + "Resources/images/relics/" + resourcePath
        }

        @JvmStatic
        fun makeRelicOutlinePath(resourcePath: String): String {
            return modID + "Resources/images/relics/outline/" + resourcePath
        }

        @JvmStatic
        fun makeOrbPath(resourcePath: String): String {
            return modID + "Resources/images/orbs/" + resourcePath
        }

        @JvmStatic
        fun makePowerPath(resourcePath: String): String {
            return modID + "Resources/images/powers/" + resourcePath
        }

        @JvmStatic
        fun makeEventPath(resourcePath: String): String {
            return modID + "Resources/images/events/" + resourcePath
        }

        private fun pathCheck() {
            val exceptionStrings = getExceptionStrings()
            val packageName = WinterholdMod::class.java.getPackage().name
            val resourcePathExists = Gdx.files.internal(modID + "Resources")
            if (modID != exceptionStrings.DEVID) {
                if (packageName != modID) {
                    throw RuntimeException(exceptionStrings.PACKAGE_EXCEPTION + modID)
                }
                if (!resourcePathExists.exists()) {
                    throw RuntimeException(exceptionStrings.RESOURCE_FOLDER_EXCEPTION + modID + "Resources")
                }
            }
        }

        private fun getExceptionStrings(): IDCheckDontTouchPls =
            WinterholdMod::class.java.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json")
                .let { InputStreamReader(it, StandardCharsets.UTF_8) }
                .let { Gson().fromJson(it, IDCheckDontTouchPls::class.java) }

        // ====== YOU CAN EDIT AGAIN ======
        @JvmStatic
        @Suppress("unused")
        fun initialize() {
            logger.info("========================= Initializing Default Mod. Hi. =========================")
            WinterholdMod()
            logger.info("========================= /Default Mod Initialized. Hello World./ =========================")
        }

        // ================ /LOAD THE KEYWORDS/ ===================
        // this adds "ModName:" before the ID of any card/relic/power etc.
        // in order to avoid conflicts if any other mod uses the same ID.
        @JvmStatic
        fun makeID(idText: String): String {
            return "$modID:$idText"
        }
    }

    init {
        logger.info("Subscribe to BaseMod hooks")
        BaseMod.subscribe(this)
        modID = ("winterhold")
        logger.info("Done subscribing")
        logger.info("Creating the color " + DestructionMage.Enums.DESTRUCTION_COLOR.toString())
        BaseMod.addColor(
            DestructionMage.Enums.DESTRUCTION_COLOR, DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY,
            DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY,
            ATTACK_DEFAULT_GRAY, SKILL_DEFAULT_GRAY, POWER_DEFAULT_GRAY, ENERGY_ORB_DEFAULT_GRAY,
            ATTACK_DEFAULT_GRAY_PORTRAIT, SKILL_DEFAULT_GRAY_PORTRAIT, POWER_DEFAULT_GRAY_PORTRAIT,
            ENERGY_ORB_DEFAULT_GRAY_PORTRAIT, CARD_ENERGY_ORB
        )
        logger.info("Done creating the color")
        logger.info("Adding mod settings")
        // This loads the mod settings.
        // The actual mod Button is added below in receivePostInitialize()
        theDefaultDefaultSettings
            .setProperty(ENABLE_PLACEHOLDER_SETTINGS, "FALSE") // This is the default setting. It's actually set...
        try {
            val config = SpireConfig(
                modID, modID + "Config",
                theDefaultDefaultSettings
            ) // ...right here
            // the "fileName" parameter is the name of the file MTS will create where it will save our setting.
            config.load() // Load the setting and set the boolean to equal it
            enablePlaceholder = config.getBool(ENABLE_PLACEHOLDER_SETTINGS)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        logger.info("Done adding mod settings")
    }
}