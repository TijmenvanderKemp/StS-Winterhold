package winterhold.spelldamage

import winterhold.WinterholdMod
import java.awt.Color

const val SPECIAL_DAMAGE_TYPE = "A special damage type. Look for cards and relics that interact with it!"

enum class SpellDamageType(
    val prettyName: String,
    // Id of the keyword as it's used in localisation
    private val cardDescriptionName: String,
    val color: Color,
    val description: String
) {
    FIRE("Fire", "Fire", Color(255, 125, 0), SPECIAL_DAMAGE_TYPE),
    FROST("Frost", "Frost", Color(0, 250, 250), SPECIAL_DAMAGE_TYPE),
    SHOCK("Shock", "Shock", Color(130, 130, 255), SPECIAL_DAMAGE_TYPE);

    val fullName: String
        get() = WinterholdMod.modID + ":" + cardDescriptionName
}