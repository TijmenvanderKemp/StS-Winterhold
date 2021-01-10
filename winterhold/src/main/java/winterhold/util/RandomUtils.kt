package winterhold.util

import com.megacrit.cardcrawl.random.Random

fun Random.nextInt(range: IntRange) = this.random(range.first, range.last)
fun <T> Random.pick(list: List<T>) = this.nextInt(list.indices).let { list[it] }