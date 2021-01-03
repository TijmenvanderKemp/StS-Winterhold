package winterhold.actions

import com.badlogic.gdx.graphics.Color
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.actions.utility.WaitAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect
import winterhold.spelldamage.SpellDamageHelper
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
            if (info.type != DamageType.THORNS && (info.owner.isDying || info.owner.halfDead)) {
                isDone = true
                return
            }
            AbstractDungeon.effectList.add(FlashAtkImgEffect(target.hb.cX, target.hb.cY, attackEffect, muteSfx))
            tickDuration()
            if (isDone) {
                if (attackEffect == AttackEffect.POISON) {
                    target.tint.color.set(Color.CHARTREUSE.cpy())
                    target.tint.changeColor(Color.WHITE.cpy())
                } else if (attackEffect == AttackEffect.FIRE) {
                    target.tint.color.set(Color.RED)
                    target.tint.changeColor(Color.WHITE.cpy())
                }

                SpellDamageHelper.dealDamage(spellDamageType)
                SpellDamageHelper.inDamagePhaseOfElementalAttack = true
                target.damage(info)
                SpellDamageHelper.inDamagePhaseOfElementalAttack = false
                SpellDamageHelper.publishCombo()

                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions()
                }
                if (!skipWait && !Settings.FAST_MODE) {
                    addToTop(WaitAction(0.1f))
                }
            }
        }
    }

}