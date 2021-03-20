package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.utility.WaitAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.actions.SpellDamageAction
import winterhold.cards.RollForArt
import winterhold.spelldamage.SpellDamageTags
import winterhold.spelldamage.SpellDamageType

class ColdBurn : AbstractDestructionCard(
    specificClass = ColdBurn::class,
    cost = 1,
    damage = 5,
    upgradeDamageBy = 2,
    type = CardType.ATTACK,
    rarity = CardRarity.UNCOMMON,
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
        if (!Settings.FAST_MODE) {
            addToBot(WaitAction(0.3f))
        }
        addToBot(
            SpellDamageAction(
                m,
                DamageInfo(p, damage, damageTypeForTurn),
                SpellDamageType.FIRE,
                attackEffect = AbstractGameAction.AttackEffect.FIRE
            )
        )
    }

    init {
        tags.add(SpellDamageTags.DEALS_FROST_DAMAGE)
        tags.add(SpellDamageTags.DEALS_FIRE_DAMAGE)
    }
}