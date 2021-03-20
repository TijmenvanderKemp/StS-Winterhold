package winterhold.schooldraft.cards

import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.cards.AbstractWinterholdCard
import winterhold.cards.RollForArt
import winterhold.cards.SubColor
import kotlin.reflect.KClass

abstract class AbstractSchoolChoiceCard(
    specificClass: KClass<out AbstractWinterholdCard>,
    subcolor: SubColor
) : AbstractWinterholdCard(
    specificClass = specificClass,
    cost = -2,
    type = CardType.ATTACK,
    subcolor = subcolor,
    rarity = CardRarity.SPECIAL,
    target = CardTarget.NONE
), SchoolChoiceCard, RollForArt {
    final override fun use(paramAbstractPlayer: AbstractPlayer?, paramAbstractMonster: AbstractMonster?) {
        TODO("Not yet implemented")
    }
}