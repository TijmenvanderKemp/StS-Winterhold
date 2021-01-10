package winterhold.dynamicvariables

import basemod.abstracts.DynamicVariable
import com.megacrit.cardcrawl.cards.AbstractCard
import winterhold.cards.AbstractComboRequirementCard

class ComboVariable : DynamicVariable() {
    override fun key() = "C"

    override fun isModified(card: AbstractCard?) =
        card is AbstractComboRequirementCard && card.isComboRequirementModified

    override fun value(card: AbstractCard?) =
        if (card is AbstractComboRequirementCard) card.comboRequirement else 0

    override fun baseValue(card: AbstractCard?) =
        if (card is AbstractComboRequirementCard) card.baseComboRequirement else 0

    override fun upgraded(card: AbstractCard?) =
        card is AbstractComboRequirementCard && card.upgradedComboRequirement
}