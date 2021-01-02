package winterhold.cards

import winterhold.characters.DestructionMage

abstract class AbstractDestructionCard(
    id: String,
    img: String,
    cost: Int,
    type: CardType,
    rarity: CardRarity,
    target: CardTarget
) : AbstractWinterholdCard(
    id,
    img,
    cost,
    type,
    DestructionMage.Enums.DESTRUCTION_COLOR,
    rarity,
    target
)
