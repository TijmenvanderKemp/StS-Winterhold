package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.cards.destruction.browseoptions.FetchFireAttack
import winterhold.cards.destruction.browseoptions.FetchFrostAttack
import winterhold.cards.destruction.browseoptions.FetchShockAttack

class Browse : AbstractDestructionCard(ID, IMG, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE) {
    companion object {
        val ID: String = WinterholdMod.makeID(Browse::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath(Browse::class.java)

        private const val COST = 1
        private const val UPGRADED_COST = 0
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(ChooseOneAction(arrayListOf(FetchFireAttack(), FetchFrostAttack(), FetchShockAttack())))
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeBaseCost(UPGRADED_COST)
        }
    }
}