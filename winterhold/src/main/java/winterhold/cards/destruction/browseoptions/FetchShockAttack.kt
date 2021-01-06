package winterhold.cards.destruction.browseoptions

import basemod.AutoAdd
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.actions.BetterDrawPileToHandAction
import winterhold.cards.destruction.AbstractDestructionCard
import winterhold.spelldamage.SpellDamageTags

@AutoAdd.Ignore
class FetchShockAttack : AbstractDestructionCard(
        ID, IMG, -2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE
) {
    companion object {
        val ID: String = WinterholdMod.makeID(FetchShockAttack::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Skill.png")
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        onChoseThisOption()
    }

    override fun onChoseThisOption() {
        addToBot(BetterDrawPileToHandAction(1) { it.hasTag(SpellDamageTags.DEALS_SHOCK_DAMAGE) })
    }

    override fun upgrade() {
        // Card can never be obtained and therefore doesn't need to be upgraded
    }

    override fun makeCopy(): AbstractCard {
        return FetchShockAttack()
    }
}