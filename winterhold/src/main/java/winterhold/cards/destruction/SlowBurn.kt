package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.powers.SlowBurnPower

class SlowBurn : AbstractDestructionCard(ID, IMG, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.NONE) {
    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(ApplyPowerAction(p, p, SlowBurnPower(p, applySingeAmount = magicNumber)))
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER)
        }
    }

    companion object {
        val ID: String = WinterholdMod.makeID(SlowBurn::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath(SlowBurn::class.java)

        private const val COST = 2
        private const val MAGIC_NUMBER = 1
        private const val UPGRADE_PLUS_MAGIC_NUMBER = 1
    }

    init {
        magicNumber = MAGIC_NUMBER
        baseMagicNumber = MAGIC_NUMBER
    }
}