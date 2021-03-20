package winterhold.cards.illusion

import winterhold.cards.AbstractWinterholdCard
import winterhold.cards.SubColor
import kotlin.reflect.KClass

abstract class AbstractIllusionCard(
    specificClass: KClass<out AbstractWinterholdCard>,
    cost: Int,
    upgradeCostTo: Int? = null,
    damage: Int? = null,
    upgradeDamageBy: Int? = null,
    block: Int? = null,
    upgradeBlockBy: Int? = null,
    magicNumber: Int? = null,
    upgradeMagicNumberBy: Int? = null,
    type: CardType,
    rarity: CardRarity,
    target: CardTarget
) : AbstractWinterholdCard(
    specificClass = specificClass,
    cost = cost,
    upgradeCostTo = upgradeCostTo,
    initialDamage = damage,
    upgradeDamageBy = upgradeDamageBy,
    initialBlock = block,
    upgradeBlockBy = upgradeBlockBy,
    initialMagicNumber = magicNumber,
    upgradeMagicNumberBy = upgradeMagicNumberBy,
    type = type,
    subcolor = SubColor.ILLUSION,
    rarity = rarity,
    target = target
) {
    init {
        when (type) {
            CardType.ATTACK -> setBackgroundTexture(
                "winterholdResources/images/512/bg_attack_illusion.png",
                "winterholdResources/images/1024/bg_attack_illusion.png",
            )
            CardType.SKILL -> setBackgroundTexture(
                "winterholdResources/images/512/bg_skill_illusion.png",
                "winterholdResources/images/1024/bg_skill_illusion.png",
            )
            CardType.POWER -> setBackgroundTexture(
                "winterholdResources/images/512/bg_power_illusion.png",
                "winterholdResources/images/1024/bg_power_illusion.png",
            )
            else -> { /* Default background options are fine */
            }
        }
    }
}