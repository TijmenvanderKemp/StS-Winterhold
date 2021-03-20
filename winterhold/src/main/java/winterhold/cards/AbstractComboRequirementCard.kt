package winterhold.cards

import basemod.abstracts.CustomCard

abstract class AbstractComboRequirementCard(
    id: String,
    name: String,
    img: String?,
    cost: Int,
    rawDescription: String,
    type: CardType,
    color: CardColor,
    rarity: CardRarity,
    target: CardTarget
) : CustomCard(id, name, img, cost, rawDescription, type, color, rarity, target) {
    var comboRequirement = 0
    var baseComboRequirement = 0
    var upgradedComboRequirement // A boolean to check whether the number has been upgraded or not.
            = false
    var isComboRequirementModified // A boolean to check whether the number has been modified or not, for coloring purposes. (red/green)
            : Boolean

    override fun displayUpgrades() {
        super.displayUpgrades()
        if (upgradedComboRequirement) {
            comboRequirement = baseComboRequirement
            isComboRequirementModified = true
        }
    }

    fun upgradeComboToNewNumber(amount: Int) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.
        baseComboRequirement = amount
        comboRequirement = baseComboRequirement // Set the number to be equal to the base value.
        upgradedComboRequirement = true // Upgraded = true - which does what the above method does.
    }

    init {

        // Set all the things to their default values.
        isCostModified = false
        isCostModifiedForTurn = false
        isDamageModified = false
        isBlockModified = false
        isMagicNumberModified = false
        isComboRequirementModified = false
    }
}