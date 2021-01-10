package winterhold.powers

import basemod.interfaces.CloneablePowerInterface
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import winterhold.WinterholdMod.Companion.makeID
import winterhold.WinterholdMod.Companion.makePowerPath
import winterhold.coloredkeywords.KeywordColorer
import winterhold.util.TextureLoader

class ConductivityPower(owner: AbstractCreature, damageBoost: Int) : AbstractWinterholdPower(),
    CloneablePowerInterface {

    companion object {
        val POWER_ID = makeID(ConductivityPower::class.java.simpleName)
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
        this.amount = damageBoost
        type = PowerType.BUFF
        region128 = AtlasRegion(tex84, 0, 0, 84, 84)
        region48 = AtlasRegion(tex32, 0, 0, 32, 32)
        updateDescription()
    }

    override fun updateDescription() {
        if (amount < 1) {
            setDescription(powerStrings.DESCRIPTIONS[0])
        } else {
            setDescription(powerStrings.DESCRIPTIONS[1])
        }
    }

    override fun makeCopy() = ConductivityPower(owner, amount)

    override fun atEndOfTurn(isPlayer: Boolean) {
        addToBot(RemoveSpecificPowerAction(owner, owner, POWER_ID))
    }

    override fun stackPower(stackAmount: Int) {
        super.stackPower(stackAmount)
        if (amount > 2) {
            amount = 2
        }
    }
}
