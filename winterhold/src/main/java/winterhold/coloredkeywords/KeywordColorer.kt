package winterhold.coloredkeywords

import winterhold.spelldamage.SpellDamageType

object KeywordColorer {
    fun replaceColoredKeywords(text: String): String {
        var newText = text
        for (keyword in SpellDamageType.values()) {
            val color: String = keyword.color.rgb.let { Integer.toHexString(it) }.substring(2)
            newText = newText.replace(
                    keyword.fullName,
                    "[#$color]${keyword.prettyName}[]"
            )
        }
        return newText
    }

    fun String.colorKeywords(): String = replaceColoredKeywords(this)
}