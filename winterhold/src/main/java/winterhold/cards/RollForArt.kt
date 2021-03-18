package winterhold.cards

import com.badlogic.gdx.graphics.Texture
import winterhold.vex.CardArtRoller

interface RollForArt {
    fun getRolledPortrait(card: AbstractHasPortraitImageCard): Texture = CardArtRoller.getPortraitTexture(card)
}
