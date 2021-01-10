package winterhold.actions

import com.badlogic.gdx.graphics.Color
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.utility.WaitAction
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect
import winterhold.spelldamage.SpellDamageInfoCreator.createDamageInfo
import winterhold.spelldamage.SpellDamageTracker
import winterhold.spelldamage.SpellDamageType

class SpellDamageAllEnemiesAction(
    source: AbstractCreature,
    val damage: IntArray,
    val spellDamageType: SpellDamageType,
    type: DamageType,
    effect: AttackEffect,
    isFast: Boolean = false
) : AbstractGameAction() {
    private var firstFrame = true

    init {
        this.source = source
        actionType = ActionType.DAMAGE
        damageType = type
        attackEffect = effect
        duration = if (isFast) {
            Settings.ACTION_DUR_XFAST
        } else {
            Settings.ACTION_DUR_FAST
        }
    }

    override fun update() {
        if (firstFrame) {
            doOnFirstFrame()
            firstFrame = false
        }
        tickDuration()
        if (isDone) {
            whenDone()
        }
    }

    private fun doOnFirstFrame() {
        var playedMusic = false
        for (monster in AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!monster.isDying && monster.currentHealth > 0 && !monster.isEscaping) {
                AbstractDungeon.effectList.add(
                    FlashAtkImgEffect(
                        monster.hb.cX,
                        monster.hb.cY,
                        attackEffect,
                        playedMusic
                    )
                )
                playedMusic = true
            }
        }
    }

    private fun whenDone() {
        AbstractDungeon.player.powers.forEach { it.onDamageAllEnemies(damage) }
        for ((index, monster) in AbstractDungeon.getCurrRoom().monsters.monsters.withIndex()) {
            if (monster.isDeadOrEscaped) continue
            if (attackEffect == AttackEffect.POISON) {
                monster.tint.color.set(Color.CHARTREUSE)
                monster.tint.changeColor(Color.WHITE.cpy())
            } else if (attackEffect == AttackEffect.FIRE) {
                monster.tint.color.set(Color.RED)
                monster.tint.changeColor(Color.WHITE.cpy())
            }
            SpellDamageTracker.dealDamage(spellDamageType)
            SpellDamageTracker.inDamagePhaseOfElementalAttack = true
            monster.damage(createDamageInfo(source, damage[index], damageType, spellDamageType))
            SpellDamageTracker.inDamagePhaseOfElementalAttack = false
            SpellDamageTracker.publishCombo()
        }
        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions()
        }
        if (!Settings.FAST_MODE) addToTop(WaitAction(0.1f))
    }
}