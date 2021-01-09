package winterhold.powers

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.powers.AbstractPower
import org.apache.logging.log4j.LogManager
import winterhold.WinterholdMod
import winterhold.actions.SpellDamageAction
import winterhold.coloredkeywords.KeywordColorer.colorKeywords
import winterhold.spelldamage.SpellDamageType
import winterhold.util.TextureLoader

class FireRunePower(owner: AbstractCreature, damageAmount: Int) : AbstractPower() {

    companion object {
        private val logger = LogManager.getLogger(FireRunePower::class.java.name)
        val POWER_ID = WinterholdMod.makeID(FireRunePower::class.java.simpleName)
        private val powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID)
        val NAME = powerStrings.NAME
        val DESCRIPTIONS = powerStrings.DESCRIPTIONS
        private val tex84 = TextureLoader.getTexture(WinterholdMod.makePowerPath("placeholder_power84.png"))
        private val tex32 = TextureLoader.getTexture(WinterholdMod.makePowerPath("placeholder_power32.png"))
    }

    init {
        name = NAME
        ID = POWER_ID
        this.owner = owner
        this.amount = damageAmount
        region128 = TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84)
        region48 = TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32)
        updateDescription()
    }

    override fun updateDescription() {
        description = DESCRIPTIONS[0]
            .replace("{damageAmount}", amount.toString())
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

    override fun onAttacked(info: DamageInfo, damageAmount: Int): Int {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner !== owner) {
            flash()
            addToTop(RemoveSpecificPowerAction(owner, owner, POWER_ID))
            addToTop(
                SpellDamageAction(
                    info.owner,
                    DamageInfo(owner, amount),
                    SpellDamageType.FIRE,
                    attackEffect = AbstractGameAction.AttackEffect.FIRE
                )
            )
        }
        return damageAmount
    }
}