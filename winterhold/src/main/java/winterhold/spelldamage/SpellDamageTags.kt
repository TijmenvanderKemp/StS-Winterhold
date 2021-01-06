package winterhold.spelldamage

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum
import com.megacrit.cardcrawl.cards.AbstractCard

object SpellDamageTags {
    @SpireEnum
    lateinit var DEALS_FIRE_DAMAGE: AbstractCard.CardTags

    @SpireEnum
    lateinit var DEALS_FROST_DAMAGE: AbstractCard.CardTags

    @SpireEnum
    lateinit var DEALS_SHOCK_DAMAGE: AbstractCard.CardTags
}