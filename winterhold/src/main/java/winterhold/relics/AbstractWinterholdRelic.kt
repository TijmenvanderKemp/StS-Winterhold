package winterhold.relics

import basemod.abstracts.CustomRelic
import com.badlogic.gdx.graphics.Texture
import com.megacrit.cardcrawl.cards.AbstractCard

abstract class AbstractWinterholdRelic(
    id: String,
    texture: Texture,
    outline: Texture,
    tier: RelicTier,
    landingSound: LandingSound,
    val color: AbstractCard.CardColor? = null
) : CustomRelic(id, texture, outline, tier, landingSound)