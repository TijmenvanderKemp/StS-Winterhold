package winterhold.characters

import basemod.abstracts.CustomPlayer
import basemod.animations.SpriterAnimation
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.MathUtils
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.core.EnergyManager
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.helpers.CardLibrary.LibraryType
import com.megacrit.cardcrawl.helpers.FontHelper
import com.megacrit.cardcrawl.helpers.ScreenShake
import com.megacrit.cardcrawl.screens.CharSelectInfo
import com.megacrit.cardcrawl.unlock.UnlockTracker
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import winterhold.WinterholdMod
import winterhold.cards.DefaultCommonAttack
import winterhold.cards.destruction.DefendDestruction
import winterhold.cards.destruction.Firebolt
import winterhold.cards.destruction.Frostbolt
import winterhold.cards.destruction.Shockbolt
import winterhold.relics.ElementalStone
import java.util.*

//Wiki-page https://github.com/daviscook477/BaseMod/wiki/Custom-Characters
//and https://github.com/daviscook477/BaseMod/wiki/Migrating-to-5.0
//All text (starting description and loadout, anything labeled TEXT[]) can be found in DefaultMod-character-Strings.json in the resources
class DestructionMage(name: String, setClass: PlayerClass) : CustomPlayer(
    name, setClass, orbTextures,
    "winterholdResources/images/char/destructionMage/orb/vfx.png", null,
    SpriterAnimation(
        "winterholdResources/images/char/destructionMage/Spriter/theDefaultAnimation.scml"
    )
) {
    object Enums {
        @SpireEnum
        lateinit var DESTRUCTION_MAGE: PlayerClass

        @SpireEnum(name = "DEFAULT_DESTRUCTION_COLOR") // These two HAVE to have the same absolutely identical name.
        lateinit var DESTRUCTION_COLOR: CardColor

        @SpireEnum(name = "DEFAULT_DESTRUCTION_COLOR")
        var LIBRARY_COLOR: LibraryType? = null
    }

    // =============== /CHARACTER CLASS END/ =================
    // Starting description and loadout
    override fun getLoadout(): CharSelectInfo {
        return CharSelectInfo(
            NAMES[0], TEXT[0],
            STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, startingRelics,
            startingDeck, false
        )
    }

    override fun getStartingDeck(): ArrayList<String> {
        logger.info("Begin loading starter Deck Strings")
        return arrayListOf(
            Firebolt.ID, Firebolt.ID, Firebolt.ID, Firebolt.ID, Shockbolt.ID,
            Frostbolt.ID, DefendDestruction.ID, DefendDestruction.ID, DefendDestruction.ID, DefendDestruction.ID
        )
    }

    override fun getStartingRelics(): ArrayList<String> {
        val retVal = ArrayList<String>()
        retVal.add(ElementalStone.ID)

        UnlockTracker.markRelicAsSeen(ElementalStone.ID)
        return retVal
    }

    override fun doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", 1.25f) // Sound Effect
        CardCrawlGame.screenShake.shake(
            ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
            false
        )
    }

    // character Select on-button-press sound effect
    override fun getCustomModeCharacterButtonSoundKey(): String {
        return "ATTACK_DAGGER_1"
    }

    // Should return how much HP your maximum HP reduces by when starting a run at Ascension 14 or higher.
    override fun getAscensionMaxHPLoss(): Int {
        return 5
    }

    // Should return the card color enum to be associated with your character.
    override fun getCardColor(): CardColor {
        return Enums.DESTRUCTION_COLOR
    }

    // Should return a color object to be used to color the trail of moving cards
    override fun getCardTrailColor(): Color {
        return WinterholdMod.DEFAULT_GRAY
    }

    // Should return a BitmapFont object that you can use to customize how your
    // energy is displayed from within the energy orb.
    override fun getEnergyNumFont(): BitmapFont {
        return FontHelper.energyNumFontRed
    }

    // Should return class name as it appears in run history screen.
    override fun getLocalizedCharacterName(): String {
        return NAMES[0]
    }

    //Which card should be obtainable from the Match and Keep event?
    override fun getStartCardForEvent(): AbstractCard {
        return DefaultCommonAttack()
    }

    // The class name as it appears next to your player name in-game
    override fun getTitle(playerClass: PlayerClass): String {
        return NAMES[1]
    }

    // Should return a new instance of your character, sending name as its name parameter.
    override fun newInstance(): AbstractPlayer {
        return DestructionMage(name, chosenClass)
    }

    // Should return a Color object to be used to color the miniature card images in run history.
    override fun getCardRenderColor(): Color {
        return WinterholdMod.DEFAULT_GRAY
    }

    // Should return a Color object to be used as screen tint effect when your
    // character attacks the heart.
    override fun getSlashAttackColor(): Color {
        return WinterholdMod.DEFAULT_GRAY
    }

    // Should return an AttackEffect array of any size greater than 0. These effects
    // will be played in sequence as your character's finishing combo on the heart.
    // Attack effects are the same as used in DamageAction and the like.
    override fun getSpireHeartSlashEffect(): Array<AttackEffect> {
        return arrayOf(
            AttackEffect.BLUNT_HEAVY,
            AttackEffect.BLUNT_HEAVY,
            AttackEffect.BLUNT_HEAVY
        )
    }

    // Should return a string containing what text is shown when your character is
    // about to attack the heart. For example, the defect is "NL You charge your
    // core to its maximum..."
    override fun getSpireHeartText(): String {
        return TEXT[1]
    }

    // The vampire events refer to the base game characters as "brother", "sister",
    // and "broken one" respectively.This method should return a String containing
    // the full text that will be displayed as the first screen of the vampires event.
    override fun getVampireText(): String {
        return TEXT[2]
    }

    companion object {
        val logger: Logger = LogManager.getLogger(DestructionMage::class.java.name)

        // =============== CHARACTER ENUMERATORS  =================
        // =============== BASE STATS =================
        const val ENERGY_PER_TURN = 3
        const val STARTING_HP = 75
        const val MAX_HP = 75
        const val STARTING_GOLD = 99
        const val CARD_DRAW = 9
        const val ORB_SLOTS = 3

        // =============== /BASE STATS/ =================
        // =============== STRINGS =================
        private val ID = WinterholdMod.makeID("DestructionMage")
        private val characterStrings = CardCrawlGame.languagePack.getCharacterString(ID)
        private val NAMES = characterStrings.NAMES
        private val TEXT = characterStrings.TEXT

        // =============== /STRINGS/ =================
        // =============== TEXTURES OF BIG ENERGY ORB ===============
        val orbTextures = arrayOf(
            "winterholdResources/images/char/destructionMage/orb/layer1.png",
            "winterholdResources/images/char/destructionMage/orb/layer2.png",
            "winterholdResources/images/char/destructionMage/orb/layer3.png",
            "winterholdResources/images/char/destructionMage/orb/layer4.png",
            "winterholdResources/images/char/destructionMage/orb/layer5.png",
            "winterholdResources/images/char/destructionMage/orb/layer6.png",
            "winterholdResources/images/char/destructionMage/orb/layer1d.png",
            "winterholdResources/images/char/destructionMage/orb/layer2d.png",
            "winterholdResources/images/char/destructionMage/orb/layer3d.png",
            "winterholdResources/images/char/destructionMage/orb/layer4d.png",
            "winterholdResources/images/char/destructionMage/orb/layer5d.png"
        )
    }

    // =============== /TEXTURES OF BIG ENERGY ORB/ ===============
    // =============== CHARACTER CLASS START =================
    init {


        // =============== TEXTURES, ENERGY, LOADOUT =================
        initializeClass(
            null,  // required call to load textures and setup energy/loadout.
            WinterholdMod.THE_DEFAULT_SHOULDER_2,  // campfire pose
            WinterholdMod.THE_DEFAULT_SHOULDER_1,  // another campfire pose
            WinterholdMod.THE_DEFAULT_CORPSE,  // dead corpse
            loadout,
            20.0f,
            -10.0f,
            220.0f,
            290.0f,
            EnergyManager(ENERGY_PER_TURN)
        )
        // =============== /TEXTURES, ENERGY, LOADOUT/ =================


        // =============== ANIMATIONS =================
        loadAnimation(
            WinterholdMod.THE_DEFAULT_SKELETON_ATLAS,
            WinterholdMod.THE_DEFAULT_SKELETON_JSON,
            1.0f
        )
        val e = state.setAnimation(0, "animation", true)
        e.time = e.endTime * MathUtils.random()
        // =============== /ANIMATIONS/ =================


        // =============== TEXT BUBBLE LOCATION =================
        dialogX = drawX + 0.0f * Settings.scale // set location for text bubbles
        dialogY = drawY + 220.0f * Settings.scale // you can just copy these values
        // =============== /TEXT BUBBLE LOCATION/ =================
    }
}