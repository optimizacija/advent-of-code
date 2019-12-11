package day4

fun main() {
    val start = 125730
    val end = 579381

    var acc = 0
    for (i in start..end) {
        val digits = i.toString().toCharArray().map { it.toInt() - 48 }

        val pairDigits = digits.zip(digits.drop(1))

        // is sorted
        if (!pairDigits.all { (a, b) -> a <= b })
            continue

        val digitGroups = digits.withIndex().groupBy { it.value }.mapValues { it.value.map { it.index } }

        // has unsurrounded pairs
        if (digitGroups.keys.any(fun(key: Int): Boolean {
                if (digitGroups[key] == null) return false
                return digitGroups[key]!!.count() == 2
            }))
            ++acc

        // has pairs
        // if (pairDigits.any { (a, b) -> a == b })
        // ++acc
    }

    println(acc)
}