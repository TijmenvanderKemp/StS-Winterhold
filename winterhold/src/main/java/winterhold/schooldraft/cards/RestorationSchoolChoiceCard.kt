package winterhold.schooldraft.cards

import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import winterhold.cards.SubColor

class RestorationSchoolChoiceCard : AbstractSchoolChoiceCard(
    specificClass = RestorationSchoolChoiceCard::class,
    subcolor = SubColor.RESTORATION,
) {
    override fun onPick() {
        AbstractDungeon.player.increaseMaxHp(9, true)
    }
}