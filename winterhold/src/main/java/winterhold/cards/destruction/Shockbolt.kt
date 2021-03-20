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
import winterhold.actions.SpellDamageAction
import winterhold.spelldamage.SpellDamageTags
import winterhold.spelldamage.SpellDamageType

class Shockbolt : AbstractDestructionCard(
    specificClass = Shockbolt::class,
    cost = 1,
    damage = 5,
    magicNumber = 1,
    upgradeMagicNumberBy = 1,
    type = CardType.ATTACK,
    rarity = CardRarity.BASIC,
    target = CardTarget.ENEMY,
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

    init {
        tags.add(SpellDamageTags.DEALS_SHOCK_DAMAGE)
    }
}