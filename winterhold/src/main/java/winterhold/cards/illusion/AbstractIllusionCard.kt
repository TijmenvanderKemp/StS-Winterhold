package winterhold.cards.illusion

import winterhold.cards.AbstractWinterholdCard
import winterhold.cards.School
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
    school = School.ILLUSION,
    rarity = rarity,
    target = target
)