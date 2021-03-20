package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.cards.RollForArt
import winterhold.powers.GainStrengthWhenComboReachesMultipleOfFivePower

class ElementalFlow : AbstractDestructionCard(
    specificClass = ElementalFlow::class,
    cost = 3,
    upgradeCostTo = 2,
    type = CardType.POWER,
    rarity = CardRarity.RARE,
    target = CardTarget.NONE,
), RollForArt {
    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(ApplyPowerAction(p, p, GainStrengthWhenComboReachesMultipleOfFivePower(p, strengthAmount = 1)))
    }
}