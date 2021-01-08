package winterhold.actions

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.cards.CardGroup
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import winterhold.WinterholdMod.Companion.makeID

internal class SurveilAction(numCards: Int) : AbstractGameAction() {
    private val startingDuration: Float
    override fun update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            isDone = true
            return
        }
        if (duration == startingDuration) {
            if (AbstractDungeon.player.drawPile.isEmpty) {
                this.isDone = true
                return
            }
            val tmpGroup = CardGroup(CardGroup.CardGroupType.UNSPECIFIED)
            AbstractDungeon.player.drawPile.group
                .let { if (amount == -1) it else it.takeLast(amount) }
                .forEach { tmpGroup.addToBottom(it) }
            AbstractDungeon.gridSelectScreen.open(tmpGroup, this.amount, true, TEXT[0])
        } else if (AbstractDungeon.gridSelectScreen.selectedCards.isNotEmpty()) {
            for (c in AbstractDungeon.gridSelectScreen.selectedCards) {
                AbstractDungeon.player.drawPile.moveToExhaustPile(c)
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear()
        }
        tickDuration()
    }

    companion object {
        private val uiStrings = CardCrawlGame.languagePack.getUIString(makeID(SurveilAction::class.java.simpleName))
        val TEXT: Array<String> = uiStrings.TEXT
    }

    init {
        amount = numCards
        actionType = ActionType.CARD_MANIPULATION
        startingDuration = Settings.ACTION_DUR_FAST
        duration = startingDuration
    }
}