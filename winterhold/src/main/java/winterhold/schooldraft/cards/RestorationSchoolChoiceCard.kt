package winterhold.schooldraft.cards

import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import winterhold.cards.School

class RestorationSchoolChoiceCard : AbstractSchoolChoiceCard(
    specificClass = RestorationSchoolChoiceCard::class,
    school = School.RESTORATION,
) {
    override fun onPick() {
        AbstractDungeon.player.increaseMaxHp(9, true)
    }
}