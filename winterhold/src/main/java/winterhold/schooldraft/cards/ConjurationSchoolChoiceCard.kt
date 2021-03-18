package winterhold.schooldraft.cards

import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.cards.AbstractWinterholdCard
import winterhold.cards.RollForArt
import winterhold.cards.SubColor

class ConjurationSchoolChoiceCard : AbstractWinterholdCard(
    id = WinterholdMod.makeID("ConjurationSchoolChoice"),
    img = WinterholdMod.makeCardPath("FireRune.png"),
    cost = -1,
    type = CardType.ATTACK,
    subcolor = SubColor.CONJURATION,
    rarity = CardRarity.SPECIAL,
    target = CardTarget.NONE,
), SchoolChoiceCard, RollForArt {
    override fun upgrade() {
        TODO("Not yet implemented")
    }

    override fun use(paramAbstractPlayer: AbstractPlayer?, paramAbstractMonster: AbstractMonster?) {
        TODO("Not yet implemented")
    }

    override fun onPick() {
        AbstractDungeon.player.increaseMaxHp(6, true)
    }
}