package winterhold.schooldraft.cards

import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import winterhold.cards.School

class AlterationSchoolChoiceCard : AbstractSchoolChoiceCard(
    specificClass = AlterationSchoolChoiceCard::class,
    school = School.ALTERATION,
) {
    override fun onPick() {
        AbstractDungeon.player.increaseMaxHp(6, true)
    }
}