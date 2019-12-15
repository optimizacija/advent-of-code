package day10

import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.sqrt

fun angle(x: Double, y: Double): Double {
    var at = atan2(y, x)
    if (at < 0) {
        at += 2 * PI
    }
    return at
}

fun Pair<Int, Int>.angle(): Double {
    return angle(-second.toDouble(), first.toDouble())
}

fun Pair<Int, Int>.length2(): Double {
    return (first * first + second * second).toDouble()
}

operator fun Pair<Int, Int>.minus(other: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first - other.first, this.second - other.second)
}

fun Pair<Int, Int>.normalized(): Pair<Int, Int> {
    val divider = gcd(this)
    return Pair(this.first / divider, this.second / divider)
}

fun gcd(value: Pair<Int, Int>): Int {

    var n1 = value.first
    var n2 = value.second
    // Always set to positive
    n1 = if (n1 > 0) n1 else -n1
    n2 = if (n2 > 0) n2 else -n2
    if (n1 == 0 && n2 == 0) return 1
    if (n1 == 0) return n2
    if (n2 == 0) return n1
    while (n1 != n2) {
        if (n1 > n2)
            n1 -= n2
        else
            n2 -= n1
    }

    return n1
}


fun main() {

    val arr = arr0
    val dim = sqrt(arr.size.toFloat()).toInt()

    val asteroids =
        arr.withIndex().filter { it.value == 1 }.map { Pair(it.index % dim, it.index / dim) }.toMutableList()
    val uniqueDirections = asteroids.map { a -> asteroids.map { b -> (a - b).normalized() }.distinct() }
    val visibilities = uniqueDirections.map { it.size - 1 }
    val max = visibilities.withIndex().maxBy { it.value }!!
    val station = asteroids[max.index]

    println(asteroids)
    println(uniqueDirections)
    println(visibilities)
    println("$station = ${max.value}")
    println("PI/2: ${PI / 2.0}")

    asteroids.remove(station)
    var asteroidSet = asteroids.toSet()

    var removed = 0
    while (!asteroids.isEmpty()) {
        val groupedAsteroids = asteroidSet.groupBy { (it - station).normalized() }
        val visibleAsteroids = groupedAsteroids.map { it.value.minBy { (it - station).length2() }!! }

        println(groupedAsteroids)
        println("visi: $visibleAsteroids")

        val totalRemoved = removed + visibleAsteroids.size
        println("removed: $removed")
        if (totalRemoved < 200) {
            asteroidSet = asteroidSet.subtract(visibleAsteroids)
            removed = totalRemoved
            continue
        }

        val radiallySortedAsteroids = visibleAsteroids.toList().sortedBy { (it - station).angle() }
        val winningAsteroid = radiallySortedAsteroids[200 - 1 - removed]
        // println("sorted: ${radiallySortedAsteroids}}")
        println(winningAsteroid)
        break
    }

    /*
    // 11, 13
    println("asdf")
    val vectors = arrayOf(
        Pair(11, 12) - station,
        Pair(12, 12) - station,
        Pair(12, 13) - station,
        Pair(12, 14) - station,
        Pair(11, 14) - station,
        Pair(10, 14) - station,
        Pair(10, 13) - station,
        Pair(10, 12) - station
    )

    for (vector in vectors) {
        println("$vector = ${vector.angle()}")
    }
    */
}

