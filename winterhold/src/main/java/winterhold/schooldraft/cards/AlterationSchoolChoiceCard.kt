package winterhold.schooldraft.cards

import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import winterhold.cards.SubColor

class AlterationSchoolChoiceCard : AbstractSchoolChoiceCard(
    specificClass = AlterationSchoolChoiceCard::class,
    subcolor = SubColor.ALTERATION,
) {
    override fun onPick() {
        AbstractDungeon.player.increaseMaxHp(6, true)
    }
}