package winterhold.actions

import com.megacrit.cardcrawl.actions.AbstractGameAction
import winterhold.spelldamage.SpellDamageTracker

class ComboAction(private val comboRequirement: Int, private val action: AbstractGameAction) : AbstractGameAction() {
    override fun update() {
        if (SpellDamageTracker.combo.amount >= comboRequirement) {
            addToTop(action)
        }
        isDone = true
    }
}
