package winterhold.powers.spelldamagevulnerable

import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.powers.VulnerablePower
import winterhold.spelldamage.SpellDamageTracker
import winterhold.spelldamage.SpellDamageType

object VulnerableCalculator {
    fun calculateExtraDamageFactor(
        damageType: DamageInfo.DamageType, spellDamageType: SpellDamageType, owner: AbstractCreature
    ): Float = when {
        // Don't stack with Vulnerable
        owner.hasPower(VulnerablePower.POWER_ID) -> 1f
        damageType != DamageInfo.DamageType.NORMAL -> 1f
        SpellDamageTracker.inDamagePhaseOfElementalAttack.not() -> 1f
        SpellDamageTracker.combo.currentDamageType != spellDamageType -> 1f
        owner.isPlayer && AbstractDungeon.player.hasRelic("Odd Mushroom") -> 1.25f
        !owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Frog") -> 1.75f
        else -> 1.5f
    }

    fun calculateExtraDamagePercentageForTooltip(owner: AbstractCreature): Int = when {
        owner.hasPower(VulnerablePower.POWER_ID) -> 0
        owner.isPlayer && AbstractDungeon.player.hasRelic("Odd Mushroom") -> 25
        !owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Frog") -> 75
        else -> 50
    }
}