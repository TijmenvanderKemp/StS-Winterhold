package winterhold.powers

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.powers.AbstractPower
import com.megacrit.cardcrawl.powers.StrengthPower
import org.apache.logging.log4j.LogManager
import winterhold.WinterholdMod
import winterhold.coloredkeywords.KeywordColorer.colorKeywords
import winterhold.spelldamage.SpellDamageTracker
import winterhold.spelldamage.SpellDamageType
import winterhold.util.TextureLoader
import winterhold.util.pick
import java.util.*

class SimonSaysPower(owner: AbstractCreature, stackAmount: Int) : AbstractPower(), Observer {

    companion object {
        private val logger = LogManager.getLogger(SimonSaysPower::class.java.name)
        val POWER_ID = WinterholdMod.makeID(SimonSaysPower::class.java.simpleName)
        private val powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID)
        val NAME = powerStrings.NAME
        val DESCRIPTIONS = powerStrings.DESCRIPTIONS
        private val tex84 = TextureLoader.getTexture(WinterholdMod.makePowerPath("placeholder_power84.png"))
        private val tex32 = TextureLoader.getTexture(WinterholdMod.makePowerPath("placeholder_power32.png"))
    }

    private var requestedDamageType: SpellDamageType

    init {
        name = NAME
        ID = POWER_ID
        this.owner = owner
        this.amount = stackAmount
        region128 = TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84)
        region48 = TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32)
        requestedDamageType = pickARandomDamageType()
        updateDescription()
        SpellDamageTracker.addObserver(this)
    }

    private fun pickARandomDamageType(): SpellDamageType {
        return AbstractDungeon.miscRng!!.pick(
            listOf(
                SpellDamageType.FIRE,
                SpellDamageType.FROST,
                SpellDamageType.SHOCK
            )
        )
    }

    override fun update(o: Observable?, arg: Any?) {
        if (SpellDamageTracker.combo.currentDamageType == requestedDamageType) {
            addToTop(ApplyPowerAction(owner, owner, StrengthPower(owner, amount), amount))
        } else {
            addToTop(RemoveSpecificPowerAction(owner, owner, StrengthPower.POWER_ID))
        }
        requestedDamageType = pickARandomDamageType()
        updateDescription()
    }

    override fun updateDescription() {
        description = DESCRIPTIONS[0]
            .replace("{damageType}", requestedDamageType.fullName)
            .replace("{strengthAmount}", amount.toString())
            .colorKeywords()
    }

    override fun stackPower(stackAmount: Int) {
        if (amount == -1) {
            logger.info("$name does not stack")
            return
        }
        fontScale = 8.0f
        amount += stackAmount
        updateDescription()
    }
}