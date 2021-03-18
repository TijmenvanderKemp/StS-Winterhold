package winterhold.cards

import com.badlogic.gdx.graphics.Texture
import com.megacrit.cardcrawl.helpers.CardLibrary
import winterhold.vex.CardArtRoller

abstract class AbstractHasPortraitImageCard(
    id: String,
    name: String,
    img: String,
    cost: Int,
    rawDescription: String,
    type: CardType,
    color: CardColor,
    rarity: CardRarity,
    target: CardTarget
) : AbstractKeywordColorerCard(
    id = id,
    name = name,
    img = img,
    cost = cost,
    rawDescription = rawDescription,
    type = type,
    color = color,
    rarity = rarity,
    target = target
) {
    var needsArtRefresh = false

    init {
        if (this is RollForArt) {
            if (CardLibrary.getAllCards() != null && CardLibrary.getAllCards().isNotEmpty()) {
                CardArtRoller.computeCard(this)
            } else {
                needsArtRefresh = true
            }
        }
    }

    override fun getPortraitImage(): Texture = when (this) {
        is RollForArt -> getRolledPortrait(this)
        else -> super.getPortraitImage()
    }

    override fun update() {
        super.update()
        if (needsArtRefresh) {
            CardArtRoller.computeCard(this)
            needsArtRefresh = false
        }
    }
}