package winterhold.cards

import basemod.helpers.TooltipInfo
import winterhold.coloredkeywords.KeywordColorer.colorKeywords
import winterhold.spelldamage.SpellDamageType

abstract class AbstractKeywordColorerCard(
    val cardId: String,
    name: String,
    img: String?,
    cost: Int,
    rawDescription: String,
    type: CardType,
    color: CardColor,
    rarity: CardRarity,
    target: CardTarget
) : AbstractComboRequirementCard(
    cardId,
    name.colorKeywords(),
    img,
    cost,
    rawDescription.colorKeywords(),
    type,
    color,
    rarity,
    target
) {
    private val tooltipsForPresentColoredKeywords = SpellDamageType.values()
        .filter { rawDescription.contains(it.fullName) }
        .map { TooltipInfo(it.prettyName, it.description) }

    override fun getCustomTooltipsTop(): List<TooltipInfo> {
        return tooltipsForPresentColoredKeywords
    }

    override fun initializeDescription() {
        if (rawDescription == null) {
            throw NullPointerException("rawDescription of card $cardId is null")
        }
        rawDescription = rawDescription.colorKeywords()
        super.initializeDescription()
    }
}