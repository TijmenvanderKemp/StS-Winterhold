package winterhold.relics

import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import winterhold.WinterholdMod
import winterhold.spelldamage.SpellDamageTracker
import winterhold.util.TextureLoader
import java.util.*

class FaraldasCharm : AbstractWinterholdRelic(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL), Observer {

    init {
        SpellDamageTracker.addObserver(this)
    }

    override fun update(o: Observable?, arg: Any?) {
        if (this in AbstractDungeon.player.relics && SpellDamageTracker.combo.spellWeave) {
            flash()
            addToBot(GainBlockAction(AbstractDungeon.player, 3, Settings.FAST_MODE))
        }
    }

    override fun getUpdatedDescription(): String {
        return DESCRIPTIONS[0]
    }

    companion object {
        val ID = WinterholdMod.makeID(FaraldasCharm::class.java.simpleName)
        private val IMG = TextureLoader.getTexture(WinterholdMod.makeRelicPath("placeholder_relic.png"))
        private val OUTLINE = TextureLoader.getTexture(WinterholdMod.makeRelicOutlinePath("placeholder_relic.png"))
    }
}