package winterhold.cards.destruction

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.animations.VFXAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.vfx.combat.FireballEffect
import winterhold.WinterholdMod
import winterhold.actions.SpellDamageAction
import winterhold.cards.AbstractDestructionCard
import winterhold.spelldamage.SpellDamageType

class Fireball : AbstractDestructionCard(
    ID, IMG, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY
) {
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

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER)
            upgradeDamage(UPGRADE_PLUS_DAMAGE)
        }
    }

    companion object {
        @kotlin.jvm.JvmField
        val ID: String = WinterholdMod.makeID(Fireball::class.java.simpleName)
        val IMG: String = WinterholdMod.makeCardPath("Attack.png")

        private const val COST = 3
        private const val DAMAGE = 36
        private const val UPGRADE_PLUS_DAMAGE = 6
        private const val MAGIC_NUMBER = 6
        private const val UPGRADE_PLUS_MAGIC_NUMBER = 1
    }

    init {
        baseMagicNumber = MAGIC_NUMBER
        magicNumber = MAGIC_NUMBER
        baseDamage = DAMAGE
    }
}