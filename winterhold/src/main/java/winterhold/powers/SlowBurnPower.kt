package winterhold.powers

import basemod.interfaces.CloneablePowerInterface
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import winterhold.WinterholdMod.Companion.makeID
import winterhold.WinterholdMod.Companion.makePowerPath
import winterhold.coloredkeywords.KeywordColorer
import winterhold.spelldamage.SpellDamageTracker
import winterhold.spelldamage.SpellDamageType
import winterhold.util.TextureLoader

class SlowBurnPower(owner: AbstractCreature, applySingeAmount: Int) : AbstractWinterholdPower(),
    CloneablePowerInterface {

    companion object {
        val POWER_ID = makeID(SlowBurnPower::class.java.simpleName)
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
        this.amount = applySingeAmount
        type = PowerType.BUFF
        region128 = AtlasRegion(tex84, 0, 0, 84, 84)
        region48 = AtlasRegion(tex32, 0, 0, 32, 32)
        updateDescription()
    }

    override fun updateDescription() {
        setDescription(powerStrings.DESCRIPTIONS[0])
    }

    override fun onAttack(info: DamageInfo, damageAmount: Int, target: AbstractCreature?) {
        if (damageAmount > 0
            && info.type == DamageInfo.DamageType.NORMAL
            && SpellDamageTracker.inDamagePhaseOfElementalAttack
            && SpellDamageTracker.combo.currentDamageType == SpellDamageType.FIRE
            && target != null
        ) {
            addToBot(
                ApplyPowerAction(
                    target,
                    owner,
                    SingePower(target, owner, amount),
                    amount
                )
            )
        }
    }

    override fun makeCopy() = SlowBurnPower(owner, amount)
}
