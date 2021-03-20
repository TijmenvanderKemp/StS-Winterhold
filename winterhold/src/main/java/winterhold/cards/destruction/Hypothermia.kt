package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction
import com.megacrit.cardcrawl.cards.status.VoidCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.cards.RollForArt

class Hypothermia : AbstractDestructionCard(
    specificClass = Hypothermia::class,
    cost = 0,
    block = 10,
    upgradeBlockBy = 4,
    type = CardType.SKILL,
    rarity = CardRarity.COMMON,
    target = CardTarget.NONE
), RollForArt {
    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(GainBlockAction(p, block))
        if (!upgraded) {
            addToBot(MakeTempCardInDrawPileAction(VoidCard(), 1, true, true))
        } else {
            addToBot(MakeTempCardInDiscardAction(VoidCard(), 1))
        }
    }
}