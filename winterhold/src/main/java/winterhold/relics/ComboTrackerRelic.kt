package winterhold.relics

import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.helpers.PowerTip
import winterhold.WinterholdMod
import winterhold.coloredkeywords.KeywordColorer
import winterhold.spelldamage.SpellDamageTracker
import winterhold.util.TextureLoader
import java.util.*


class ComboTrackerRelic : AbstractWinterholdRelic(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL), Observer {

    init {
        SpellDamageTracker.addObserver(this)
    }

    override fun atBattleStart() {
        this.counter = 0
    }

    override fun update(o: Observable?, arg: Any?) {
        if (this in AbstractDungeon.player.relics) {
            this.counter = SpellDamageTracker.combo.amount
            fixDescription()
        }
    }

    private fun fixDescription() {
        description = updatedDescription
        tips.clear()
        tips.add(PowerTip(name, description))
        initializeTips()
    }

    override fun getUpdatedDescription() = when (counter) {
        -1, 0 -> DESCRIPTIONS[0]
        1 -> DESCRIPTIONS[1].replace("{damageType}", SpellDamageTracker.combo.currentDamageType?.prettyName ?: "no")
        else -> DESCRIPTIONS[2].replace("{damageType}", SpellDamageTracker.combo.currentDamageType?.prettyName ?: "no")
            .replace("{spellAmount}", counter.toString())
    }.let { KeywordColorer.replaceColoredKeywords(it) }

    companion object {
        val ID = WinterholdMod.makeID(ComboTrackerRelic::class.java.simpleName)
        private val IMG = TextureLoader.getTexture(WinterholdMod.makeRelicPath("placeholder_relic.png"))
        private val OUTLINE = TextureLoader.getTexture(WinterholdMod.makeRelicOutlinePath("placeholder_relic.png"))
    }
}