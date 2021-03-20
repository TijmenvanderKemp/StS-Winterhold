package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.actions.SpellDamageAction
import winterhold.cards.RollForArt
import winterhold.spelldamage.SpellDamageTags
import winterhold.spelldamage.SpellDamageType

class Frostbolt : AbstractDestructionCard(
    specificClass = Frostbolt::class,
    cost = 1,
    damage = 5,
    upgradeDamageBy = 2,
    block = 5,
    upgradeBlockBy = 2,
    type = CardType.ATTACK,
    rarity = CardRarity.BASIC,
    target = CardTarget.ENEMY
), RollForArt {
    override fun use(p: AbstractPlayer, m: AbstractMonster) {
        addToBot(
            SpellDamageAction(
                m,
                DamageInfo(p, damage, damageTypeForTurn),
                SpellDamageType.FROST,
                attackEffect = AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
            )
        )
        addToBot(
            GainBlockAction(p, block)
        )
    }

    init {
        tags.add(SpellDamageTags.DEALS_FROST_DAMAGE)
    }
}