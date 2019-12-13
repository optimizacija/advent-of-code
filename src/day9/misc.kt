package day9

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
