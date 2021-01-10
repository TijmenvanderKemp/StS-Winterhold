package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.animations.VFXAction
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.utility.SFXAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.powers.VulnerablePower
import com.megacrit.cardcrawl.vfx.combat.LightningEffect
import winterhold.WinterholdMod
import winterhold.actions.SpellDamageAction
import winterhold.spelldamage.SpellDamageTags
import winterhold.spelldamage.SpellDamageType

class Shockbolt : AbstractDestructionCard(
    ID, IMG, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY
) {
    override fun use(p: AbstractPlayer, m: AbstractMonster) {
        addToBot(
            SpellDamageAction(
                m,
                DamageInfo(p, damage, damageTypeForTurn),
                SpellDamageType.SHOCK,
                attackEffect = AbstractGameAction.AttackEffect.NONE
            )
        )
        addToBot(SFXAction("ORB_LIGHTNING_EVOKE", 0.1f))
        addToBot(VFXAction(LightningEffect(m.drawX, m.drawY), 0.0f))

        addToBot(
            ApplyPowerAction(
                m, p, VulnerablePower(m, magicNumber, false)
            )
        )
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER)
        }
    }

    companion object {
        val ID: String = WinterholdMod.makeID(Shockbolt::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath(Shockbolt::class.java)

        private const val COST = 1
        private const val DAMAGE = 5
        private const val MAGIC_NUMBER = 1
        private const val UPGRADE_PLUS_MAGIC_NUMBER = 1
    }

    init {
        baseMagicNumber = MAGIC_NUMBER
        magicNumber = MAGIC_NUMBER
        baseDamage = DAMAGE
        tags.add(SpellDamageTags.DEALS_SHOCK_DAMAGE)
    }
}