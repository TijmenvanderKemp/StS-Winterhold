package winterhold.schooldraft.cards

import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import winterhold.cards.SubColor

class IllusionSchoolChoiceCard : AbstractSchoolChoiceCard(
    specificClass = IllusionSchoolChoiceCard::class,
    subcolor = SubColor.ILLUSION
) {
    override fun onPick() {
        AbstractDungeon.player.increaseMaxHp(0, true)
    }
}