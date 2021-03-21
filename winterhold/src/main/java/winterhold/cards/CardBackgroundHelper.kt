package winterhold.cards

import com.megacrit.cardcrawl.cards.AbstractCard

object CardBackgroundHelper {
    fun getBackgrounds(school: School, type: AbstractCard.CardType): Pair<String, String> {
        val schoolString = school.toString().toLowerCase()
        val typeString = type.toString().toLowerCase()
        return Pair(
            "winterholdResources/images/512/bg_${typeString}_$schoolString.png",
            "winterholdResources/images/1024/bg_${typeString}_$schoolString.png"
        )
    }
}
