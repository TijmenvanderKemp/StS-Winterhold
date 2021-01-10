package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.animations.VFXAction
import com.megacrit.cardcrawl.actions.utility.SFXAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.vfx.combat.LightningEffect
import winterhold.WinterholdMod
import winterhold.actions.SpellDamageAllEnemiesAction
import winterhold.spelldamage.SpellDamageTags
import winterhold.spelldamage.SpellDamageType
import winterhold.util.stillFightingMonsters

class ChainLightning : AbstractDestructionCard(
    ID, IMG, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY
) {
    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(SFXAction("THUNDERCLAP", 0.05F))
        AbstractDungeon.getCurrRoom().monsters.stillFightingMonsters.forEach {
            addToBot(VFXAction(LightningEffect(it.drawX, it.drawY), 0.05F))
        }

        addToBot(
            SpellDamageAllEnemiesAction(
                p,
                multiDamage,
                SpellDamageType.SHOCK,
                damageTypeForTurn,
                AbstractGameAction.AttackEffect.NONE
            )
        )
    }

    //Upgraded stats.
    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDamage(UPGRADE_PLUS_DAMAGE)
        }
    }

    companion object {
        val ID: String = WinterholdMod.makeID(ChainLightning::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath(ChainLightning::class.java)

        private const val COST = 1
        private const val DAMAGE = 8
        private const val UPGRADE_PLUS_DAMAGE = 3
    }

    // /STAT DECLARATION/
    init {
        baseDamage = DAMAGE
        isMultiDamage = true
        tags.add(SpellDamageTags.DEALS_SHOCK_DAMAGE)
    }
}