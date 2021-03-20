package winterhold.schooldraft.cards

import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import winterhold.cards.School

class IllusionSchoolChoiceCard : AbstractSchoolChoiceCard(
    specificClass = IllusionSchoolChoiceCard::class,
    school = School.ILLUSION
) {
    override fun onPick() {
        AbstractDungeon.player.increaseMaxHp(0, true)
    }
}