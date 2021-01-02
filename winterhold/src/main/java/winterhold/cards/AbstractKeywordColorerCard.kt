package winterhold.cards

import basemod.helpers.TooltipInfo
import winterhold.spelldamage.SpellDamageType

abstract class AbstractKeywordColorerCard(
    id: String,
    name: String,
    img: String,
    cost: Int,
    rawDescription: String,
    type: CardType,
    color: CardColor,
    rarity: CardRarity,
    target: CardTarget
) : AbstractSecondMagicNumberCard(
    id,
    name,
    img,
    cost,
    replaceColoredKeywords(rawDescription),
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

    companion object {
        fun replaceColoredKeywords(text: String): String {
            var newText = text
            for (keyword in SpellDamageType.values()) {
                val color: String = keyword.color.rgb.let { Integer.toHexString(it) }.substring(2)
                newText = newText.replace(
                    keyword.fullName,
                    "[#$color]${keyword.prettyName}[]"
                )
            }
            return newText
        }
    }
}
