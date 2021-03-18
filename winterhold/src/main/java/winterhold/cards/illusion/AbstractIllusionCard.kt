package winterhold.cards.illusion

import winterhold.cards.AbstractWinterholdCard
import winterhold.cards.SubColor

abstract class AbstractIllusionCard(
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