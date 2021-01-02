package winterhold.relics

import basemod.abstracts.CustomRelic
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import winterhold.WinterholdMod
import winterhold.spelldamage.SpellDamageHelper
import winterhold.util.TextureLoader
import java.util.*

class ElementalStone : CustomRelic(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL), Observer {

    init {
        SpellDamageHelper.addObserver(this)
    }

    override fun update(o: Observable?, arg: Any?) {
        if (this in AbstractDungeon.player.relics) {
            flash()
            AbstractDungeon.player.heal(1)
        }
    }

    // Description
    override fun getUpdatedDescription(): String {
        return DESCRIPTIONS[0]
    }

    companion object {
        // ID, images, text.
        @JvmField
        val ID = WinterholdMod.makeID("ElementalStone")
        private val IMG = TextureLoader.getTexture(WinterholdMod.makeRelicPath("placeholder_relic.png"))
        private val OUTLINE = TextureLoader.getTexture(WinterholdMod.makeRelicOutlinePath("placeholder_relic.png"))
    }
}