package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction
import com.megacrit.cardcrawl.cards.status.VoidCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod

class Hypothermia : AbstractDestructionCard(
    ID, IMG, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE
) {


    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(GainBlockAction(p, block))
        if (!upgraded) {
            addToBot(MakeTempCardInDrawPileAction(VoidCard(), 1, true, true))
        } else {
            addToBot(MakeTempCardInDiscardAction(VoidCard(), 1))
        }
    }

    override fun upgrade() {
        if (!upgraded) {
            useUpgradeDescription()
            upgradeName()
            upgradeBlock(UPGRADE_PLUS_BLOCK)
        }
    }

    companion object {
        val ID: String = WinterholdMod.makeID(Hypothermia::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Skill.png")

        private const val COST = 0
        private const val BLOCK = 10
        private const val UPGRADE_PLUS_BLOCK = 4
    }

    init {
        baseBlock = BLOCK
    }
}