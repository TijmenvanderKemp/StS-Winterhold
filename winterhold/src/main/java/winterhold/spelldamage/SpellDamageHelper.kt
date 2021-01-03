package winterhold.spelldamage

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.*

object SpellDamageHelper : Observable() {
    var combo = Combo(0, null)
    private val logger: Logger = LogManager.getLogger(SpellDamageHelper::class.java.name)
    var inDamagePhaseOfElementalAttack = false

    data class Combo(var amount: Int, var comboType: SpellDamageType?)

    fun dealDamage(spellDamageType: SpellDamageType) {
        if (combo.comboType == spellDamageType) {
            combo.amount++
        } else {
            combo.amount = 1
            combo.comboType = spellDamageType
        }
        setChanged()
    }

    fun publishCombo() {
        logger.info("Publishing $combo")
        notifyObservers(combo)
    }

    fun resetCombo() {
        combo = Combo(0, null)
        setChanged()
    }
}
