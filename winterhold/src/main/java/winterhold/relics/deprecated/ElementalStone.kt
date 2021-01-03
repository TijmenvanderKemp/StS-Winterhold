package winterhold.relics.deprecated

import basemod.abstracts.CustomRelic
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.spelldamage.SpellDamageHelper
import winterhold.util.FatalChecker
import winterhold.util.TextureLoader
import java.util.*

class ElementalStone : CustomRelic(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL), Observer {

    init {
        SpellDamageHelper.addObserver(this)
    }

    override fun atBattleStart() {
        this.counter = 0
    }

    override fun update(o: Observable?, arg: Any?) {
        if (this in AbstractDungeon.player.relics) {
            this.counter = SpellDamageHelper.combo.amount
        }
    }

    override fun onMonsterDeath(m: AbstractMonster) {
        if (FatalChecker.isFatal(m)) {
            AbstractDungeon.player.heal(SpellDamageHelper.combo.amount)
            SpellDamageHelper.resetCombo()
            this.counter = SpellDamageHelper.combo.amount
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