package winterhold.cards.illusion

import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.cards.RollForArt

class Fork : AbstractIllusionCard(
    specificClass = Fork::class,
    cost = 0,
    damage = 3,
    type = CardType.ATTACK,
    rarity = CardRarity.COMMON,
    target = CardTarget.ENEMY
), RollForArt {

    override fun use(p: AbstractPlayer, m: AbstractMonster) {
    }
}