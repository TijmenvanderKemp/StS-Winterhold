package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.powers.FireRunePower
import winterhold.spelldamage.SpellDamageTags.DEALS_FIRE_DAMAGE

class FireRune : AbstractDestructionCard(
    specificClass = FireRune::class,
    cost = 2,
    damage = 4,
    upgradeDamageBy = 3,
    block = 4,
    upgradeBlockBy = 3,
    type = CardType.SKILL,
    rarity = CardRarity.COMMON,
    target = CardTarget.NONE
) {


    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(GainBlockAction(p, block))

        addToBot(
            ApplyPowerAction(
                p, p, FireRunePower(p, damage),
                damage
            )
        )
    }

    init {
        tags.add(DEALS_FIRE_DAMAGE)
    }
}