package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.cards.destruction.browseoptions.FetchFireAttack
import winterhold.cards.destruction.browseoptions.FetchFrostAttack
import winterhold.cards.destruction.browseoptions.FetchShockAttack

class Browse : AbstractDestructionCard(
    specificClass = Browse::class,
    cost = 1,
    upgradeCostTo = 0,
    type = CardType.SKILL,
    rarity = CardRarity.UNCOMMON,
    target = CardTarget.NONE) {

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(ChooseOneAction(arrayListOf(FetchFireAttack(), FetchFrostAttack(), FetchShockAttack())))
    }
}