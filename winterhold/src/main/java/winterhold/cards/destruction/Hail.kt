package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.WinterholdMod
import winterhold.actions.SpellDamageAction
import winterhold.spelldamage.SpellDamageTags
import winterhold.spelldamage.SpellDamageType

class Hail : AbstractDestructionCard(ID, IMG, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY) {
    override fun use(p: AbstractPlayer, m: AbstractMonster) {
        repeat(magicNumber) {
            AbstractDungeon.actionManager.addToBottom(
                SpellDamageAction(
                    m,
                    DamageInfo(p, damage, damageTypeForTurn),
                    SpellDamageType.FROST,
                    attackEffect = AbstractGameAction.AttackEffect.BLUNT_LIGHT
                )
            )
        }
    }

    //Upgraded stats.
    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER)
        }
    }

    companion object {
        val ID: String = WinterholdMod.makeID(Hail::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Attack.png")

        private const val COST = 1
        private const val DAMAGE = 1
        private const val MAGIC_NUMBER = 4
        private const val UPGRADE_PLUS_MAGIC_NUMBER = 2
    }

    init {
        magicNumber = MAGIC_NUMBER
        baseMagicNumber = MAGIC_NUMBER
        baseDamage = DAMAGE
        tags.add(SpellDamageTags.DEALS_FROST_DAMAGE)
    }
}