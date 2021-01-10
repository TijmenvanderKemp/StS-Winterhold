package winterhold.powers

import com.megacrit.cardcrawl.powers.AbstractPower
import winterhold.coloredkeywords.KeywordColorer.colorKeywords

abstract class AbstractWinterholdPower : AbstractPower() {
    fun setDescription(description: String) {
        this.description = description
            .replace("{amount}", amount.toString())
            .colorKeywords()
    }
}