package winterhold.spelldamage

import org.apache.logging.log4j.LogManager
import java.util.*

object SpellDamageHelper : Observable() {
    var comboType: SpellDamageType? = null
    var amountOfSameTypeDamageInARow = 0
    private val logger = LogManager.getLogger(SpellDamageHelper::class.java.name)


    fun dealDamage(spellDamageType: SpellDamageType) {
        if (comboType == spellDamageType) {
            amountOfSameTypeDamageInARow++
        } else {
            amountOfSameTypeDamageInARow = 1
            comboType = spellDamageType
        }
        setChanged()
    }

    private fun isCombo() = amountOfSameTypeDamageInARow > 1

    fun publishCombo() {
        if (isCombo()) {
            logger.info("Publishing Combo")
            notifyObservers()
        }
    }
}
