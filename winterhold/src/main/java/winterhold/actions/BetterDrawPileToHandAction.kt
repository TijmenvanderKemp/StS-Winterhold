package winterhold.actions

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.CardGroup
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

class BetterDrawPileToHandAction(amountOfCards: Int, isThisAGoodCard: (AbstractCard) -> Boolean) :
    AbstractGameAction() {
    private val p = AbstractDungeon.player
    private val isThisAGoodCard: (AbstractCard) -> Boolean

    init {
        setValues(p, p, amountOfCards)
        actionType = ActionType.CARD_MANIPULATION
        duration = Settings.ACTION_DUR_MED
        this.isThisAGoodCard = isThisAGoodCard
    }

    override fun update() {
        if (duration == Settings.ACTION_DUR_MED) {
            if (p.drawPile.isEmpty) {
                isDone = true
                return
            }
            val tmp = CardGroup(CardGroup.CardGroupType.UNSPECIFIED)
            p.drawPile.group
                .filter { isThisAGoodCard(it) }
                    .forEach { tmp.addToRandomSpot(it) }
            if (tmp.isEmpty) {
                isDone = true
                return
            }
            repeat(amount) {
                if (!tmp.isEmpty) {
                    tmp.shuffle()
                    val card = tmp.bottomCard
                    tmp.removeCard(card)
                    if (p.hand.size() == 10) {
                        p.drawPile.moveToDiscardPile(card)
                        p.createHandIsFullDialog()
                    } else {
                        card.unhover()
                        card.lighten(true)
                        card.setAngle(0.0f)
                        card.drawScale = 0.12f
                        card.targetDrawScale = 0.75f
                        card.current_x = CardGroup.DRAW_PILE_X
                        card.current_y = CardGroup.DRAW_PILE_Y
                        p.drawPile.removeCard(card)
                        AbstractDungeon.player.hand.addToTop(card)
                        AbstractDungeon.player.hand.refreshHandLayout()
                        AbstractDungeon.player.hand.applyPowers()
                    }
                }
            }
            isDone = true
        }
        tickDuration()
    }
}