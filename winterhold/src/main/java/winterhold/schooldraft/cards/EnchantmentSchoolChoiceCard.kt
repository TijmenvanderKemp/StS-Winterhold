package winterhold.schooldraft.cards

import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import winterhold.cards.School

class EnchantmentSchoolChoiceCard : AbstractSchoolChoiceCard(
    specificClass = EnchantmentSchoolChoiceCard::class,
    school = School.ENCHANTMENT,
) {
    override fun onPick() {
        AbstractDungeon.player.increaseMaxHp(3, true)
    }
}