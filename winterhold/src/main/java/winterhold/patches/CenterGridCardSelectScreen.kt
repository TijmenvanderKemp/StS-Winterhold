package winterhold.patches

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen

@SpirePatch(clz = GridCardSelectScreen::class, method = "updateCardPositionsAndHoverLogic")
object CenterGridCardSelectScreen {
    var centerGridSelect = false
    private var save_isJustForConfirming = false

    @JvmStatic
    @SpirePrefixPatch
    fun Prefix(__instance: GridCardSelectScreen) {
        save_isJustForConfirming = __instance.isJustForConfirming
        if (centerGridSelect) {
            __instance.isJustForConfirming = true
        }
    }

    @JvmStatic
    @SpirePostfixPatch
    fun Postfix(__instance: GridCardSelectScreen) {
        __instance.isJustForConfirming = save_isJustForConfirming
    }
}
