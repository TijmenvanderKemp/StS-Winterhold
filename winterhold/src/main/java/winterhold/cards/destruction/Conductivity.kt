package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.cards.CustomUpgrade
import winterhold.cards.RollForArt
import winterhold.powers.ConductivityPower

class Conductivity : AbstractDestructionCard(
    specificClass = Conductivity::class,
    cost = 0,
    type = CardType.SKILL,
    rarity = CardRarity.UNCOMMON,
    target = CardTarget.NONE
), CustomUpgrade, RollForArt {

    private var increasedShockDamage = 0

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(ApplyPowerAction(p, p, ConductivityPower(p, increasedShockDamage)))
    }

    companion object {
        val ID: String = WinterholdMod.makeID(Conductivity::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Power.png")

        private const val COST = 0
    }

    override fun doCustomUpgrade() {
        increasedShockDamage = 2
    }
}