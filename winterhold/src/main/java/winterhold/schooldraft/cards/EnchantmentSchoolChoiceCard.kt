package winterhold.schooldraft.cards

import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import winterhold.cards.SubColor

class EnchantmentSchoolChoiceCard : AbstractSchoolChoiceCard(
    specificClass = EnchantmentSchoolChoiceCard::class,
    subcolor = SubColor.ENCHANTMENT,
) {
    override fun onPick() {
        AbstractDungeon.player.increaseMaxHp(3, true)
    }
}