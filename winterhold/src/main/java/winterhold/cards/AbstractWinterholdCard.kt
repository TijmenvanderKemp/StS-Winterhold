package winterhold.cards

import com.megacrit.cardcrawl.core.CardCrawlGame

abstract class AbstractWinterholdCard(
    id: String,
    img: String,
    cost: Int,
    type: CardType,
    color: CardColor,
    rarity: CardRarity,
    target: CardTarget
) : AbstractKeywordColorerCard(
    id,
    CardCrawlGame.languagePack.getCardStrings(id).NAME,
    img,
    cost,
    CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION,
    type,
    color,
    rarity,
    target
)
