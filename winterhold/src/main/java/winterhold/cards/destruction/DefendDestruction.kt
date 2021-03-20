package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.cards.RollForArt

class DefendDestruction : AbstractDestructionCard(
    specificClass = DefendDestruction::class,
    cost = 1,
    block = 5,
    upgradeBlockBy = 3,
    type = CardType.SKILL,
    rarity = CardRarity.BASIC,
    target = CardTarget.SELF
), RollForArt {
    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(GainBlockAction(p, p, block))
    }

    override fun makeCopy(): AbstractCard {
        return DefendDestruction()
    }

    init {
        tags.add(CardTags.STARTER_DEFEND)
    }
}