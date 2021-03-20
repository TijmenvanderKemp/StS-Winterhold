package winterhold.patches

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen

@SpirePatch(clz = GridCardSelectScreen::class, method = "updateCardPositionsAndHoverLogic")
object CenterGridCardSelectScreen {
    var centerGridSelect = false
    private var save_isJustForConfirming = false

    @Suppress("unused")
    @JvmStatic
    fun prefix(__instance: GridCardSelectScreen) {
        save_isJustForConfirming = __instance.isJustForConfirming
        if (centerGridSelect) {
            __instance.isJustForConfirming = true
        }
    }

    @Suppress("unused")
    @JvmStatic
    fun postfix(__instance: GridCardSelectScreen) {
        __instance.isJustForConfirming = save_isJustForConfirming
    }
}
