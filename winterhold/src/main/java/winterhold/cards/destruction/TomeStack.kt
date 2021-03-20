package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.common.DrawCardAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.actions.SurveilAction

class TomeStack : AbstractDestructionCard(
    specificClass = TomeStack::class,
    cost = 1,
    magicNumber = 1,
    upgradeMagicNumberBy = 1,
    type = CardType.SKILL,
    rarity = CardRarity.COMMON,
    target = CardTarget.NONE
) {
    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(SurveilAction(magicNumber))
        addToBot(DrawCardAction(1))
    }
}