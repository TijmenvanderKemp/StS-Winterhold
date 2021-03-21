package winterhold.patches

import com.evacipated.cardcrawl.modthespire.lib.ByRef
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.CardGroup
import winterhold.cards.AbstractWinterholdCard
import winterhold.cards.School
import java.util.*

@SpirePatch(
    clz = CardGroup::class,
    method = "sortWithComparator",
    paramtypez = [Comparator::class, Boolean::class]
)
private object SortCardsBySchoolFirst {

    @JvmStatic
    @SpirePrefixPatch
    fun Prefix(__instance: CardGroup, @ByRef comp: Array<Comparator<AbstractCard>>, ascending: Boolean) {
        comp[0] = compareBy(::getSchool).then(comp[0])
    }

    fun getSchool(card: AbstractCard): School? = if (card is AbstractWinterholdCard) card.school else null
}
