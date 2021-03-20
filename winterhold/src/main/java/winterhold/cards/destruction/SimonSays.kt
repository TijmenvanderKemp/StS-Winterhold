package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.cards.CustomUpgrade
import winterhold.cards.RollForArt
import winterhold.powers.SimonSaysPower

class SimonSays : AbstractDestructionCard(
    specificClass = SimonSays::class,
    cost = 1,
    magicNumber = 1,
    type = CardType.POWER,
    rarity = CardRarity.RARE,
    target = CardTarget.NONE,
), CustomUpgrade, RollForArt {
    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        addToBot(ApplyPowerAction(p, p, SimonSaysPower(p, stackAmount = magicNumber)))
    }

    override fun doCustomUpgrade() {
        this.isInnate = true
    }
}