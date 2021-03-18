package winterhold.schooldraft.cards

import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.cards.RollForArt
import winterhold.cards.destruction.AbstractDestructionCard
import winterhold.relics.ComboTrackerRelic
import winterhold.relics.FaraldasCharm

class DestructionSchoolChoiceCard : AbstractDestructionCard(
    id = WinterholdMod.makeID("DestructionSchoolChoice"),
    img = WinterholdMod.makeCardPath("Firebolt.png"),
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
        AbstractDungeon.player.getRelic(ComboTrackerRelic.ID)
        AbstractDungeon.player.getRelic(FaraldasCharm.ID)
        AbstractDungeon.player.increaseMaxHp(3, true)
    }
}