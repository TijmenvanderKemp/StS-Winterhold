package winterhold.actions

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.animations.VFXAction
import com.megacrit.cardcrawl.actions.utility.SFXAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.vfx.combat.LightningEffect
import winterhold.spelldamage.SpellDamageType

class SpellDamageRandomEnemyAction(private val info: DamageInfo, private val spellDamageType: SpellDamageType, attackEffect: AttackEffect) : AbstractGameAction() {
    init {
        this.attackEffect = attackEffect
    }

    override fun update() {
        target = AbstractDungeon.getMonsters()
            .getRandomMonster(null, true, AbstractDungeon.cardRandomRng) as AbstractCreature
        if (target != null) {
            addToTop(SpellDamageAction(target, info, spellDamageType, attackEffect = attackEffect))
            addToTop(VFXAction(LightningEffect(target.drawX, target.drawY), 0.0f))
            addToTop(SFXAction("ORB_LIGHTNING_EVOKE", 0.1f))
        }
        isDone = true
    }

}