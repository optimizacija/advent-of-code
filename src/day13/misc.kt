package day13

import java.math.BigInteger

fun <T> MutableList<T>.clone(): MutableList<T> {
    val mutableList = mutableListOf<T>()
    mutableList.addAll(this)
    return mutableList
}

// only for when int >= 0
fun BigInteger.toDigits(digitCount: Int): List<Int> {
    if (this < 0.toBigInteger()) // TODO("test only, should be removed")
        throw error("value should not be negative")

    return this.toString().padStart(digitCount, '0').toCharArray().map { it - '0' }
}

fun Array<Long>.toBigIntegerMutableList(): MutableList<BigInteger> {
    return this.map { it.toBigInteger() }.toMutableList()
}

fun Array<Int>.toBigIntegerMutableList(): MutableList<BigInteger> {
    return this.map { it.toBigInteger() }.toMutableList()
}


operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(first + other.first, second + other.second)
}

fun print(matrix: Array<Array<Int>>) {
    for(x in matrix.indices) {
        for(y in matrix[0].indices) {
            print(matrix[x][y])
        }
        println()
    }
}
