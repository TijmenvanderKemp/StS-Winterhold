package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod

class DefendDestruction : AbstractDestructionCard(
    ID,
    IMG,
    1,
    CardType.SKILL,
    CardRarity.BASIC,
    CardTarget.SELF
) {
    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(GainBlockAction(p, p, block))
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeBlock(3)
        }
    }

    override fun makeCopy(): AbstractCard {
        return DefendDestruction()
    }

    companion object {
        val ID: String = WinterholdMod.makeID(DefendDestruction::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Skill.png")
        private val cardStrings: CardStrings = CardCrawlGame.languagePack.getCardStrings(ID)
    }

    init {
        baseBlock = 5
        tags.add(CardTags.STARTER_DEFEND)
    }
}