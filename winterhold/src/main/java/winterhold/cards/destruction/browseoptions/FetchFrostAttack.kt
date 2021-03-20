package winterhold.cards.destruction.browseoptions

import basemod.AutoAdd
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.actions.BetterDrawPileToHandAction
import winterhold.cards.RollForArt
import winterhold.cards.destruction.AbstractDestructionCard
import winterhold.spelldamage.SpellDamageTags

@AutoAdd.Ignore
class FetchFrostAttack : AbstractDestructionCard(
    specificClass = FetchFrostAttack::class,
    cost = -2,
    type = CardType.SKILL,
    rarity = CardRarity.SPECIAL,
    target = CardTarget.NONE
), RollForArt {

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        onChoseThisOption()
    }

    override fun onChoseThisOption() {
        addToBot(BetterDrawPileToHandAction(1) { it.hasTag(SpellDamageTags.DEALS_FROST_DAMAGE) })
    }

    override fun canUpgrade() = false

    override fun makeCopy(): AbstractCard {
        return FetchFrostAttack()
    }
}