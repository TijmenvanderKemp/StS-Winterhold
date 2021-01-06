package winterhold.cards

import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings

abstract class AbstractWinterholdCard(
        id: String,
        img: String,
        cost: Int,
        type: CardType,
        color: CardColor,
        rarity: CardRarity,
        target: CardTarget,
        val cardStrings: CardStrings = CardCrawlGame.languagePack.getCardStrings(id)
) : AbstractKeywordColorerCard(
        id,
        cardStrings.NAME,
        img,
        cost,
        cardStrings.DESCRIPTION,
        type,
        color,
        rarity,
        target
)
