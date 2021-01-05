package winterhold.powers

import basemod.interfaces.CloneablePowerInterface
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.powers.AbstractPower
import com.megacrit.cardcrawl.powers.StrengthPower
import winterhold.WinterholdMod.Companion.makeID
import winterhold.WinterholdMod.Companion.makePowerPath
import winterhold.coloredkeywords.KeywordColorer
import winterhold.spelldamage.SpellDamageTracker
import winterhold.util.TextureLoader
import java.util.*

class GainStrengthWhenComboReachesMultipleOfFivePower(owner: AbstractCreature, strengthAmount: Int) : AbstractPower(),
    CloneablePowerInterface, Observer {

    init {
        SpellDamageTracker.addObserver(this)
    }

    companion object {
        val POWER_ID = makeID(GainStrengthWhenComboReachesMultipleOfFivePower::class.java.simpleName)
        private val powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID)
        val NAME = KeywordColorer.replaceColoredKeywords(powerStrings.NAME)
        val DESCRIPTIONS = powerStrings.DESCRIPTIONS
        private val tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"))
        private val tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"))
    }

    init {
        name = NAME
        ID = POWER_ID
        this.owner = owner
        this.amount = strengthAmount
        type = PowerType.BUFF
        region128 = AtlasRegion(tex84, 0, 0, 84, 84)
        region48 = AtlasRegion(tex32, 0, 0, 32, 32)
        updateDescription()
    }

    override fun updateDescription() {
        description = powerStrings.DESCRIPTIONS[0]
    }

    override fun update(o: Observable?, arg: Any?) {
        if (SpellDamageTracker.combo.amount > 0 && SpellDamageTracker.combo.amount.divisibleBy(5)) {
            addToBot(
                ApplyPowerAction(
                    owner,
                    owner,
                    StrengthPower(owner, amount),
                    amount
                )
            )

        }
    }

    private fun Int.divisibleBy(divisor: Int) = this % divisor == 0

    override fun makeCopy() = GainStrengthWhenComboReachesMultipleOfFivePower(owner, amount)
}
