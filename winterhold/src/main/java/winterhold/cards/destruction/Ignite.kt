package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.actions.ComboAction
import winterhold.actions.SpellDamageAction
import winterhold.cards.CustomUpgrade
import winterhold.cards.RollForArt
import winterhold.spelldamage.SpellDamageTags
import winterhold.spelldamage.SpellDamageType

class Ignite : AbstractDestructionCard(
    specificClass = Ignite::class,
    cost = 0,
    damage = 3,
    type = CardType.ATTACK,
    rarity = CardRarity.COMMON,
    target = CardTarget.ENEMY
), CustomUpgrade, RollForArt {
    override fun use(p: AbstractPlayer, m: AbstractMonster) {
        addToBot(
            SpellDamageAction(
                m,
                DamageInfo(p, damage, damageTypeForTurn),
                SpellDamageType.FIRE,
                attackEffect = AbstractGameAction.AttackEffect.FIRE
            )
        )
        addToBot(
            ComboAction(
                comboRequirement, SpellDamageAction(
                    m,
                    DamageInfo(p, damage, damageTypeForTurn),
                    SpellDamageType.FIRE,
                    attackEffect = AbstractGameAction.AttackEffect.FIRE
                )
            )
        )
    }

    override fun doCustomUpgrade() {
        upgradeComboToNewNumber(UPGRADE_NEW_COMBO)
    }

    companion object {
        private const val COMBO = 3
        private const val UPGRADE_NEW_COMBO = 2
    }

    init {
        baseComboRequirement = COMBO
        comboRequirement = COMBO
        tags.add(SpellDamageTags.DEALS_FIRE_DAMAGE)
    }
}