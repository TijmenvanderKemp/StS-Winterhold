package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.actions.ComboAction
import winterhold.actions.SpellDamageAction
import winterhold.spelldamage.SpellDamageTags
import winterhold.spelldamage.SpellDamageType

class Ignite : AbstractDestructionCard(ID, IMG, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY) {
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

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeComboToNewNumber(UPGRADE_NEW_COMBO)
        }
    }

    companion object {
        val ID: String = WinterholdMod.makeID(Ignite::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Attack.png")

        private const val COST = 0
        private const val DAMAGE = 3
        private const val COMBO = 3
        private const val UPGRADE_NEW_COMBO = 2
    }

    init {
        baseDamage = DAMAGE
        baseComboRequirement = COMBO
        comboRequirement = COMBO
        tags.add(SpellDamageTags.DEALS_FIRE_DAMAGE)
    }
}