package winterhold.actions

import com.badlogic.gdx.graphics.Color
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.utility.WaitAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect
import winterhold.spelldamage.SpellDamageInfoCreator
import winterhold.spelldamage.SpellDamageTracker
import winterhold.spelldamage.SpellDamageType

class SpellDamageAction(
    val target: AbstractCreature,
    private val info: DamageInfo,
    private val spellDamageType: SpellDamageType,
    private var skipWait: Boolean = false,
    attackEffect: AttackEffect = AttackEffect.NONE,
    private var muteSfx: Boolean = false
) : AbstractGameAction() {

    init {
        setValues(target, info)
        this.attackEffect = attackEffect
        duration = 0.1f
        actionType = ActionType.DAMAGE
    }

    override fun update() {
        if (shouldCancelAction() && info.type != DamageType.THORNS) {
            isDone = true
        } else {
            if (duration == 0.1f) {
                if (info.type != DamageType.THORNS && (info.owner.isDying || info.owner.halfDead)) {
                    isDone = true
                    return
                }
                AbstractDungeon.effectList.add(FlashAtkImgEffect(target.hb.cX, target.hb.cY, attackEffect, muteSfx))
            }
            tickDuration()
            if (isDone) {
                whenDone()
            }
        }
    }

    private fun whenDone() {
        if (attackEffect == AttackEffect.POISON) {
            target.tint.color.set(Color.CHARTREUSE.cpy())
            target.tint.changeColor(Color.WHITE.cpy())
        } else if (attackEffect == AttackEffect.FIRE) {
            target.tint.color.set(Color.RED)
            target.tint.changeColor(Color.WHITE.cpy())
        }

        SpellDamageTracker.dealDamage(spellDamageType)
        SpellDamageTracker.inDamagePhaseOfElementalAttack = true
        target.damage(SpellDamageInfoCreator.createDamageInfo(info, spellDamageType))
        SpellDamageTracker.inDamagePhaseOfElementalAttack = false
        SpellDamageTracker.publishCombo()

        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions()
        }
        if (!skipWait && !Settings.FAST_MODE) {
            addToTop(WaitAction(0.1f))
        }
    }

}