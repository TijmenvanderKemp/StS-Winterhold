package winterhold.schooldraft

import com.megacrit.cardcrawl.cards.CardGroup
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import winterhold.WinterholdMod
import winterhold.cards.AbstractWinterholdCard
import winterhold.cards.School
import winterhold.patches.CenterGridCardSelectScreen
import winterhold.schooldraft.cards.AlterationSchoolChoiceCard
import winterhold.schooldraft.cards.ConjurationSchoolChoiceCard
import winterhold.schooldraft.cards.DestructionSchoolChoiceCard
import winterhold.schooldraft.cards.EnchantmentSchoolChoiceCard
import winterhold.schooldraft.cards.IllusionSchoolChoiceCard
import winterhold.schooldraft.cards.RestorationSchoolChoiceCard
import winterhold.schooldraft.cards.SchoolChoiceCard

object DraftTwoSchoolsOnFloorZeroService {
    private const val numberOfSchoolsNeeded = 2
    private var schoolsReadyToShow = false
    private val firstChoiceCardGroup = CardGroup(CardGroup.CardGroupType.UNSPECIFIED)
    private val secondChoiceCardGroup = CardGroup(CardGroup.CardGroupType.UNSPECIFIED)
    private val masterSchoolChoices: List<AbstractWinterholdCard> = listOf(
        AlterationSchoolChoiceCard(),
        ConjurationSchoolChoiceCard(),
        DestructionSchoolChoiceCard(),
        EnchantmentSchoolChoiceCard(),
        IllusionSchoolChoiceCard(),
        RestorationSchoolChoiceCard(),
    )
    val chosenSchools = mutableListOf<School>()


    fun doDraftAction() {
        if (AbstractDungeon.gridSelectScreen.selectedCards.isNotEmpty()) {
            processSchoolChoice()
        }
        if (chosenSchools.size >= numberOfSchoolsNeeded) {
            returnToNormalState()
            eliminateSchoolsThatWereNotChosenFromCardPool()
            println("Chose: $chosenSchools")
            println(
                """Card pool: 
                |${AbstractDungeon.srcCommonCardPool}
                |${AbstractDungeon.srcUncommonCardPool}
                |${AbstractDungeon.srcRareCardPool}
            """.trimMargin()
            )
            return
        }
        if (schoolsReadyToShow.not()) {
            makeSchoolsReadyToShow()
        }
        if (schoolSelectScreenIsNotBeingShownToUser()) {
            showSchoolSelectScreenToUser()
        }
    }

    private fun returnToNormalState() {
        // Cancel further draft actions
        WinterholdMod.inDraftTwoSchoolsPhase = false
        // Make sure choices get reshuffled next time
        schoolsReadyToShow = false
        CenterGridCardSelectScreen.centerGridSelect = false
    }

    private fun eliminateSchoolsThatWereNotChosenFromCardPool() {
        AbstractDungeon.srcCommonCardPool.group.removeIf { it is AbstractWinterholdCard && it.school !in chosenSchools }
        AbstractDungeon.srcUncommonCardPool.group.removeIf { it is AbstractWinterholdCard && it.school !in chosenSchools }
        AbstractDungeon.srcRareCardPool.group.removeIf { it is AbstractWinterholdCard && it.school !in chosenSchools }
    }

    private fun makeSchoolsReadyToShow() {
        val (firstBatch, secondBatch) = masterSchoolChoices.shuffled().chunked(3)
        firstBatch.forEach { firstChoiceCardGroup.addToTop(it) }
        secondBatch.forEach { secondChoiceCardGroup.addToTop(it) }
        schoolsReadyToShow = true
    }

    private fun schoolSelectScreenIsNotBeingShownToUser() = (AbstractDungeon.isScreenUp &&
            AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID).not()

    private fun showSchoolSelectScreenToUser() {
        CenterGridCardSelectScreen.centerGridSelect = true
        AbstractDungeon.gridSelectScreen.open(
            getGroupToChooseFrom(), 1, false,
            CardCrawlGame.languagePack.getUIString("winterhold:ChooseSchool").TEXT[0]
        )
    }

    private fun getGroupToChooseFrom() = when (chosenSchools.size) {
        0 -> firstChoiceCardGroup
        1 -> secondChoiceCardGroup
        else -> throw Exception("Don't know which options to offer the user when they've already drafted ${chosenSchools.size} schools")
    }

    private fun processSchoolChoice() {
        val choice = AbstractDungeon.gridSelectScreen.selectedCards[0]!! as AbstractWinterholdCard
        chosenSchools.add(choice.school)
        (choice as SchoolChoiceCard).onPick()
        AbstractDungeon.gridSelectScreen.selectedCards.clear()
    }
}