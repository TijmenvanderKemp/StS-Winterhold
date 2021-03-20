package winterhold.schooldraft.cards

import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import winterhold.cards.School

class ConjurationSchoolChoiceCard : AbstractSchoolChoiceCard(
    specificClass = ConjurationSchoolChoiceCard::class,
    school = School.CONJURATION,
) {
    override fun onPick() {
        AbstractDungeon.player.increaseMaxHp(6, true)
    }
}