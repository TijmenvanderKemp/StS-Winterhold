package winterhold.cards

import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import winterhold.WinterholdMod
import winterhold.characters.Archmage
import winterhold.vex.TexLoader
import kotlin.reflect.KClass

abstract class AbstractWinterholdCard(
    specificClass: KClass<out AbstractWinterholdCard>,
    val cost: Int,
    val upgradeCostTo: Int? = null,
    val initialDamage: Int? = null,
    val upgradeDamageBy: Int? = null,
    val initialBlock: Int? = null,
    val upgradeBlockBy: Int? = null,
    val initialMagicNumber: Int? = null,
    val upgradeMagicNumberBy: Int? = null,
    val type: CardType,
    val school: School,
    val rarity: CardRarity,
    val target: CardTarget,
    val cardStrings: CardStrings = CardCrawlGame.languagePack.getCardStrings(WinterholdMod.makeID(specificClass.simpleName!!)),
    val id: String = WinterholdMod.makeID(specificClass.simpleName!!),
) : AbstractHasPortraitImageCard(
    id = id,
    name = cardStrings.NAME,
    img = if (TexLoader.testTexture(WinterholdMod.makeCardPath(specificClass))) WinterholdMod.makeCardPath(specificClass) else null,
    cost = cost,
    rawDescription = cardStrings.DESCRIPTION,
    type = type,
    color = Archmage.Enums.ARCHMAGE_COLOR,
    rarity = rarity,
    target = target
) {
    init {
        if (initialDamage != null) {
            baseDamage = initialDamage
        }
        if (initialBlock != null) {
            baseBlock = initialBlock
        }
        if (initialMagicNumber != null) {
            magicNumber = initialMagicNumber
            baseMagicNumber = initialMagicNumber
        }
    }

    private fun useUpgradeDescription() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION
        initializeDescription()
    }

    final override fun upgrade() {
        if (canUpgrade()) {
            if (this is CustomUpgrade) {
                useUpgradeDescription()
                doCustomUpgrade()
            }
            if (upgradeCostTo != null) {
                upgradeBaseCost(upgradeCostTo)
            }
            if (upgradeDamageBy != null) {
                upgradeDamage(upgradeDamageBy)
            }
            if (upgradeBlockBy != null) {
                upgradeBlock(upgradeBlockBy)
            }
            if (upgradeMagicNumberBy != null) {
                upgradeMagicNumber(upgradeMagicNumberBy)
            }
            upgradeName()
        }
    }

    /**
     * Override this and set it to final so we can safely use it in the constructors of subclasses.
     */
    final override fun setBackgroundTexture(backgroundSmallImg: String, backgroundLargeImg: String) {
        super.setBackgroundTexture(backgroundSmallImg, backgroundLargeImg)
    }


}
