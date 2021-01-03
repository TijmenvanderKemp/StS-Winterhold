package winterhold.powers.spelldamagevulnerable

import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.powers.VulnerablePower
import winterhold.coloredkeywords.KeywordColorer

object PowerDescriptionMaker {
    fun makeDescription(owner: AbstractCreature, amount: Int, descriptions: Array<String>) =
        KeywordColorer.replaceColoredKeywords(
            descriptions[0] +
                    VulnerableCalculator.calculateExtraDamagePercentageForTooltip(owner) +
                    descriptions[1] +
                    amount +
                    descriptions[2] +
                    // Explain that this does not stack with Vulnerable
                    if (owner.hasPower(VulnerablePower.POWER_ID)) descriptions[3] else ""
        )
}