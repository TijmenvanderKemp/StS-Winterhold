package winterhold.cards.illusion

import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.cards.RollForArt
import winterhold.spelldamage.SpellDamageTags

class Fork : AbstractIllusionCard(ID, IMG, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY),
    RollForArt {

    override fun use(p: AbstractPlayer, m: AbstractMonster) {
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
        }
    }

    companion object {
        val ID: String = WinterholdMod.makeID(Fork::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Attack.png")

        private const val COST = 0
        private const val DAMAGE = 3
        private const val COMBO = 3
        private const val UPGRADE_NEW_COMBO = 2
        private const val MAGIC_NUMBER = 1
    }

    init {
        baseDamage = DAMAGE
        baseComboRequirement = COMBO
        comboRequirement = COMBO
        baseMagicNumber = MAGIC_NUMBER
        magicNumber = MAGIC_NUMBER
        tags.add(SpellDamageTags.DEALS_FROST_DAMAGE)
    }
}