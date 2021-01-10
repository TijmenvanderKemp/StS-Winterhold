package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.powers.SimonSaysPower

class SimonSays : AbstractDestructionCard(ID, IMG, COST, CardType.POWER, CardRarity.RARE, CardTarget.NONE) {
    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(ApplyPowerAction(p, p, SimonSaysPower(p, stackAmount = magicNumber)))
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            this.isInnate = true
        }
    }

    companion object {
        val ID: String = WinterholdMod.makeID(SimonSays::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Power.png")

        private const val COST = 1
        private const val MAGIC_NUMBER = 1
    }

    init {
        magicNumber = MAGIC_NUMBER
        baseMagicNumber = MAGIC_NUMBER
    }
}