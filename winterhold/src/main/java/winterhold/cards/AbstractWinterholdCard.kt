package winterhold.cards

import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import winterhold.characters.Archmage

abstract class AbstractWinterholdCard(
    id: String,
    img: String,
    cost: Int,
    type: CardType,
    val subcolor: SubColor,
    rarity: CardRarity,
    target: CardTarget,
    val cardStrings: CardStrings = CardCrawlGame.languagePack.getCardStrings(id)
) : AbstractHasPortraitImageCard(
    id,
    cardStrings.NAME,
    img,
    cost,
    cardStrings.DESCRIPTION,
    type,
    Archmage.Enums.ARCHMAGE_COLOR,
    rarity,
    target
) {
    fun useUpgradeDescription() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION
        initializeDescription()
    }

    /**
     * Override this and set it to final so we can safely use it in the constructors of subclasses.
     */
    final override fun setBackgroundTexture(backgroundSmallImg: String, backgroundLargeImg: String) {
        super.setBackgroundTexture(backgroundSmallImg, backgroundLargeImg)
    }


}
