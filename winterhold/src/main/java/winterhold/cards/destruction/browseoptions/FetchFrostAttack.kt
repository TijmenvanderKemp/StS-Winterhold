package winterhold.cards.destruction.browseoptions

import basemod.AutoAdd
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.actions.BetterDrawPileToHandAction
import winterhold.cards.RollForArt
import winterhold.cards.destruction.AbstractDestructionCard
import winterhold.spelldamage.SpellDamageTags

@AutoAdd.Ignore
class FetchFrostAttack : AbstractDestructionCard(
    ID, IMG, -2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE
), RollForArt {
    companion object {
        val ID: String = WinterholdMod.makeID(FetchFrostAttack::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Skill.png")
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        onChoseThisOption()
    }

    override fun onChoseThisOption() {
        addToBot(BetterDrawPileToHandAction(1) { it.hasTag(SpellDamageTags.DEALS_FROST_DAMAGE) })
    }

    override fun upgrade() {
        // Card can never be obtained and therefore doesn't need to be upgraded
    }

    override fun makeCopy(): AbstractCard {
        return FetchFrostAttack()
    }
}