package winterhold.schooldraft.cards

import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import winterhold.cards.SubColor

class ConjurationSchoolChoiceCard : AbstractSchoolChoiceCard(
    specificClass = ConjurationSchoolChoiceCard::class,
    subcolor = SubColor.CONJURATION,
) {
    override fun onPick() {
        AbstractDungeon.player.increaseMaxHp(6, true)
    }
}