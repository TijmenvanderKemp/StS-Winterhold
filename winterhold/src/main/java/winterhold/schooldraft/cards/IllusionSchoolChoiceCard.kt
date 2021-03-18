package winterhold.schooldraft.cards

import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.cards.RollForArt
import winterhold.cards.illusion.AbstractIllusionCard

class IllusionSchoolChoiceCard : AbstractIllusionCard(
    id = WinterholdMod.makeID("IllusionSchoolChoice"),
    img = WinterholdMod.makeCardPath("TomeStack.png"),
    cost = -1,
    type = CardType.ATTACK,
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
        AbstractDungeon.player.increaseMaxHp(0, true)
    }
}