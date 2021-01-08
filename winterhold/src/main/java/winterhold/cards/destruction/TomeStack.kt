package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.common.DrawCardAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.actions.SurveilAction

class TomeStack : AbstractDestructionCard(
    ID, IMG, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE
) {
    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(SurveilAction(magicNumber))
        addToBot(DrawCardAction(1))
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER)
        }
    }

    companion object {
        val ID: String = WinterholdMod.makeID(TomeStack::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath(TomeStack::class.java)

        private const val COST = 1
        private const val MAGIC_NUMBER = 1
        private const val UPGRADE_PLUS_MAGIC_NUMBER = 1
    }

    init {
        baseMagicNumber = MAGIC_NUMBER
        magicNumber = MAGIC_NUMBER
    }
}