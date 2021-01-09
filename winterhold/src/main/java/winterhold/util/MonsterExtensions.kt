package winterhold.util

import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.monsters.MonsterGroup

val MonsterGroup.stillFightingMonsters: List<AbstractMonster>
    get() = monsters.filter { it.isStillFighting }
val AbstractMonster.isStillFighting: Boolean
    get() = !isDeadOrEscaped