package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.animations.VFXAction
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.utility.SFXAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.powers.VulnerablePower
import com.megacrit.cardcrawl.vfx.combat.LightningEffect
import winterhold.actions.ComboAction
import winterhold.actions.SpellDamageRandomEnemyAction
import winterhold.cards.CustomUpgrade
import winterhold.spelldamage.SpellDamageTags
import winterhold.spelldamage.SpellDamageType
import winterhold.util.stillFightingMonsters

class Sparks : AbstractDestructionCard(
    specificClass = Sparks::class,
    cost = 0,
    damage = 3,
    magicNumber = 1,
    type = CardType.ATTACK,
    rarity = CardRarity.COMMON,
    target = CardTarget.ENEMY
), CustomUpgrade {
    override fun use(p: AbstractPlayer, m: AbstractMonster) {
        addToBot(SFXAction("THUNDERCLAP", 0.05F))
        AbstractDungeon.getCurrRoom().monsters.stillFightingMonsters.forEach {
            addToBot(VFXAction(LightningEffect(it.drawX, it.drawY), 0.05F))
        }
        addToBot(
            SpellDamageRandomEnemyAction(
                DamageInfo(p, damage, damageTypeForTurn),
                SpellDamageType.SHOCK,
                AbstractGameAction.AttackEffect.NONE
            )
        )
        addToBot(
            ComboAction(
                comboRequirement, ApplyPowerAction(m, p, VulnerablePower(m, magicNumber, false), magicNumber)
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
        tags.add(SpellDamageTags.DEALS_SHOCK_DAMAGE)
    }
}