package winterhold.spelldamage

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.*

object SpellDamageHelper : Observable() {
    var combo = Combo()
    private val logger: Logger = LogManager.getLogger(SpellDamageHelper::class.java.name)
    var inDamagePhaseOfElementalAttack = false

    class Combo {
        var amount: Int = 0
        var currentDamageType: SpellDamageType? = null
            set(value) {
                lastDamageType = currentDamageType
                field = value
            }
        var lastDamageType: SpellDamageType? = null
            private set
        val spellWeave: Boolean
            get() = lastDamageType != currentDamageType
                    && lastDamageType != null
                    && currentDamageType != null
    }

    fun dealDamage(spellDamageType: SpellDamageType) {
        if (combo.currentDamageType == spellDamageType) {
            combo.amount++
        } else {
            combo.amount = 1
            combo.currentDamageType = spellDamageType
        }
        setChanged()
    }

    fun publishCombo() {
        logger.info("Publishing $combo")
        notifyObservers(combo)
    }

    fun resetCombo() {
        combo = Combo()
        setChanged()
    }
}
