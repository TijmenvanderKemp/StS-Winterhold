package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.utility.WaitAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.actions.SpellDamageAction
import winterhold.spelldamage.SpellDamageTags
import winterhold.spelldamage.SpellDamageType

class ColdBurn : AbstractDestructionCard(
    ID, IMG, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY
) {
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

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDamage(UPGRADE_PLUS_DAMAGE)
        }
    }

    companion object {
        val ID: String = WinterholdMod.makeID(ColdBurn::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Attack.png")

        private const val COST = 1
        private const val DAMAGE = 5
        private const val UPGRADE_PLUS_DAMAGE = 2
    }

    init {
        baseDamage = DAMAGE
        tags.add(SpellDamageTags.DEALS_FROST_DAMAGE)
    }
}