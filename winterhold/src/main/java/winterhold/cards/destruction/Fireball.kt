package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.animations.VFXAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.vfx.combat.FireballEffect
import winterhold.actions.SpellDamageAction
import winterhold.cards.RollForArt
import winterhold.spelldamage.SpellDamageTags.DEALS_FIRE_DAMAGE
import winterhold.spelldamage.SpellDamageType

class Fireball : AbstractDestructionCard(
    specificClass = Fireball::class,
    cost = 2,
    damage = 36,
    upgradeDamageBy = 6,
    magicNumber = 6,
    type = CardType.ATTACK,
    rarity = CardRarity.RARE,
    target = CardTarget.ENEMY
), RollForArt {
    override fun use(p: AbstractPlayer, m: AbstractMonster) {
        addToBot(
            SpellDamageAction(
                p,
                DamageInfo(p, magicNumber, damageTypeForTurn),
                SpellDamageType.FIRE,
                attackEffect = AbstractGameAction.AttackEffect.FIRE
            )
        )
        addToBot(
            VFXAction(
                FireballEffect(
                    AbstractDungeon.player.hb.cX,
                    AbstractDungeon.player.hb.cY,
                    m.hb.cX,
                    m.hb.cY
                ), 0.5f
            )
        )

        addToBot(
            SpellDamageAction(
                m,
                DamageInfo(p, damage, damageTypeForTurn),
                SpellDamageType.FIRE,
                attackEffect = AbstractGameAction.AttackEffect.FIRE
            )
        )
    }

    init {
        tags.add(DEALS_FIRE_DAMAGE)
    }
}