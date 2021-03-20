package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import winterhold.actions.SpellDamageAction
import winterhold.spelldamage.SpellDamageTags
import winterhold.spelldamage.SpellDamageType

class Hail : AbstractDestructionCard(
    specificClass = Hail::class,
    cost = 1,
    damage = 1,
    magicNumber = 4,
    upgradeMagicNumberBy = 2,
    type = CardType.ATTACK,
    rarity = CardRarity.UNCOMMON,
    target = CardTarget.ENEMY) {
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

    init {
        tags.add(SpellDamageTags.DEALS_FROST_DAMAGE)
    }
}