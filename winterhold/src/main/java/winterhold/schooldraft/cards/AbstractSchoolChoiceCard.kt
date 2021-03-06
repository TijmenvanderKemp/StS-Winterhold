package winterhold.schooldraft.cards

import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.cards.AbstractWinterholdCard
import winterhold.cards.RollForArt
import winterhold.cards.School
import kotlin.reflect.KClass

abstract class AbstractSchoolChoiceCard(
    specificClass: KClass<out AbstractWinterholdCard>,
    school: School
) : AbstractWinterholdCard(
    specificClass = specificClass,
    cost = -2,
    // Can't be attack when rolling for card art because Eviscerate cares about the current dungeon room which is null
    type = CardType.SKILL,
    school = school,
    rarity = CardRarity.SPECIAL,
    target = CardTarget.NONE
), SchoolChoiceCard, RollForArt {
    final override fun use(paramAbstractPlayer: AbstractPlayer?, paramAbstractMonster: AbstractMonster?) {
        TODO("Not yet implemented")
    }
}