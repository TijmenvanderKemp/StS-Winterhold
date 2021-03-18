package winterhold.cards.destruction

import winterhold.cards.AbstractWinterholdCard
import winterhold.cards.SubColor

abstract class AbstractDestructionCard(
    id: String,
    img: String,
    cost: Int,
    type: CardType,
    rarity: CardRarity,
    target: CardTarget
) : AbstractWinterholdCard(
    id = id,
    img = img,
    cost = cost,
    type = type,
    subcolor = SubColor.DESTRUCTION,
    rarity = rarity,
    target = target
) {
    init {
        when (type) {
            CardType.ATTACK -> setBackgroundTexture(
                "winterholdResources/images/512/bg_attack_destruction.png",
                "winterholdResources/images/1024/bg_attack_destruction.png",
            )
            CardType.SKILL -> setBackgroundTexture(
                "winterholdResources/images/512/bg_skill_destruction.png",
                "winterholdResources/images/1024/bg_skill_destruction.png",
            )
            CardType.POWER -> setBackgroundTexture(
                "winterholdResources/images/512/bg_power_destruction.png",
                "winterholdResources/images/1024/bg_power_destruction.png",
            )
            else -> { /* Default background options are fine */
            }
        }
    }
}