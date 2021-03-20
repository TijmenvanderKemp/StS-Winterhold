package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.animations.VFXAction
import com.megacrit.cardcrawl.actions.utility.SFXAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.vfx.combat.LightningEffect
import winterhold.actions.SpellDamageAllEnemiesAction
import winterhold.spelldamage.SpellDamageTags
import winterhold.spelldamage.SpellDamageType
import winterhold.util.stillFightingMonsters

class ChainLightning : AbstractDestructionCard(
    specificClass = ChainLightning::class,
    cost = 1,
    damage = 8,
    upgradeDamageBy = 3,
    type = CardType.ATTACK,
    rarity = CardRarity.COMMON,
    target = CardTarget.ALL_ENEMY
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

    init {
        isMultiDamage = true
        tags.add(SpellDamageTags.DEALS_SHOCK_DAMAGE)
    }
}