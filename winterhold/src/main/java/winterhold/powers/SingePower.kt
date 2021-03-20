package winterhold.powers

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.powers.AbstractPower
import com.megacrit.cardcrawl.rooms.AbstractRoom
import winterhold.WinterholdMod
import winterhold.WinterholdMod.Companion.makeID
import winterhold.actions.SpellDamageAction
import winterhold.coloredkeywords.KeywordColorer
import winterhold.spelldamage.SpellDamageType
import winterhold.util.TextureLoader

class SingePower(owner: AbstractCreature, private val source: AbstractCreature, singeAmt: Int) : AbstractPower() {
    companion object {
        val POWER_ID = makeID(SingePower::class.java.simpleName)
        private val powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID)
        val NAME = powerStrings.NAME
        val DESCRIPTIONS = powerStrings.DESCRIPTIONS

        private val tex84 = TextureLoader.getTexture(WinterholdMod.makePowerPath("placeholder_power84.png"))
        private val tex32 = TextureLoader.getTexture(WinterholdMod.makePowerPath("placeholder_power32.png"))
    }

    init {
        name = NAME
        ID = POWER_ID
        this.owner = owner
        amount = singeAmt
        if (amount >= 9999) {
            amount = 9999
        }
        region128 = TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84)
        region48 = TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32)
        updateDescription()
        type = PowerType.DEBUFF
        isTurnBased = true
    }

    override fun onHeal(healAmount: Int): Int {
        addToTop(RemoveSpecificPowerAction(owner, owner, POWER_ID))
        return healAmount
    }

    override fun updateDescription() {
        description = KeywordColorer.replaceColoredKeywords(
            if (owner == null || owner.isPlayer) {
                DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]
            } else {
                DESCRIPTIONS[2] + amount + DESCRIPTIONS[1]
            }
        )
    }


    override fun atEndOfTurn(isPlayer: Boolean) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT &&
            !AbstractDungeon.getMonsters().areMonstersBasicallyDead()
        ) {
            flashWithoutSound()
            addToBot(
                SpellDamageAction(
                    owner,
                    DamageInfo(source, amount, DamageInfo.DamageType.THORNS),
                    SpellDamageType.FIRE,
                    attackEffect = AbstractGameAction.AttackEffect.FIRE
                )
            )
        }
    }
}