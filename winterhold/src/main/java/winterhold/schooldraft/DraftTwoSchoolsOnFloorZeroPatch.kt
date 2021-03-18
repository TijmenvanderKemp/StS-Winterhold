package winterhold.schooldraft

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.events.AbstractEvent
import winterhold.WinterholdMod
import winterhold.characters.Archmage

@SpirePatch(clz = AbstractEvent::class, method = "onEnterRoom")
open class DraftTwoSchoolsOnFloorZeroPatch {
    companion object {
        @JvmStatic
        @SpirePostfixPatch
        fun Postfix(__instance: AbstractEvent?) {
            if (AbstractDungeon.floorNum == 0 && AbstractDungeon.player.chosenClass == Archmage.Enums.ARCHMAGE) {
                DraftTwoSchoolsOnFloorZeroService.chosenSchools.clear()
                WinterholdMod.inDraftTwoSchoolsPhase = true
            }
        }
    }
}