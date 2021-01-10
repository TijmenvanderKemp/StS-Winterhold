package winterhold.spelldamage

import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import winterhold.powers.ConductivityPower

object SpellDamageInfoCreator {
    fun createDamageInfo(damageInfo: DamageInfo, spellDamageType: SpellDamageType): DamageInfo {
        return createDamageInfo(damageInfo.owner, damageInfo.base, damageInfo.type, spellDamageType)
    }

    fun createDamageInfo(
        source: AbstractCreature,
        amount: Int,
        damageType: DamageInfo.DamageType,
        spellDamageType: SpellDamageType
    ): DamageInfo {
        if (AbstractDungeon.player.hasPower(ConductivityPower.POWER_ID) && spellDamageType == SpellDamageType.SHOCK) {
            val damageBoost = (AbstractDungeon.player.getPower(ConductivityPower.POWER_ID) as ConductivityPower).amount
            return DamageInfo(source, amount + damageBoost, DamageInfo.DamageType.HP_LOSS)
        }
        return DamageInfo(source, amount, damageType)
    }
}