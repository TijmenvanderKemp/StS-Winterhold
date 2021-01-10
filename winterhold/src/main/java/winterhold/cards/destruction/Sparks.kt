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
import winterhold.WinterholdMod
import winterhold.actions.ComboAction
import winterhold.actions.SpellDamageRandomEnemyAction
import winterhold.spelldamage.SpellDamageTags
import winterhold.spelldamage.SpellDamageType
import winterhold.util.stillFightingMonsters

class Sparks : AbstractDestructionCard(ID, IMG, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY) {
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

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeComboToNewNumber(UPGRADE_NEW_COMBO)
        }
    }

    companion object {
        val ID: String = WinterholdMod.makeID(Sparks::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath(Sparks::class.java)

        private const val COST = 0
        private const val DAMAGE = 3
        private const val COMBO = 3
        private const val UPGRADE_NEW_COMBO = 2
        private const val MAGIC_NUMBER = 1
    }

    init {
        baseDamage = DAMAGE
        baseComboRequirement = COMBO
        comboRequirement = COMBO
        baseMagicNumber = MAGIC_NUMBER
        magicNumber = MAGIC_NUMBER
        tags.add(SpellDamageTags.DEALS_SHOCK_DAMAGE)
    }
}