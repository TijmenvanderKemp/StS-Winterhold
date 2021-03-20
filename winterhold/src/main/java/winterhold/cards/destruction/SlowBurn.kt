package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.powers.SlowBurnPower

class SlowBurn : AbstractDestructionCard(
    specificClass = SlowBurn::class,
    cost = 2,
    magicNumber = 1,
    upgradeMagicNumberBy = 1,
    type = CardType.POWER,
    rarity = CardRarity.UNCOMMON,
    target = CardTarget.NONE
) {
    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(ApplyPowerAction(p, p, SlowBurnPower(p, applySingeAmount = magicNumber)))
    }
}