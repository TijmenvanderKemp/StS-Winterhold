package winterhold.schooldraft.cards

import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import winterhold.cards.School
import winterhold.relics.ComboTrackerRelic
import winterhold.relics.FaraldasCharm

class DestructionSchoolChoiceCard : AbstractSchoolChoiceCard(
    specificClass = DestructionSchoolChoiceCard::class,
    school = School.DESTRUCTION
) {
    override fun onPick() {
        AbstractDungeon.player.getRelic(ComboTrackerRelic.ID)
        AbstractDungeon.player.getRelic(FaraldasCharm.ID)
        AbstractDungeon.player.increaseMaxHp(3, true)
    }
}