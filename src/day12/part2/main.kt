package day12.part2

fun main() {
    val simulator = MoonForceSimulator()
    val moons = arr0.map { Moon(it) }
    simulator.moons.addAll(moons.map { it.copy() })

    for (coordIdx in 0 until 3) {

        val coordSet = mutableMapOf<List<Pair<Int, Int>>, Int>()
        var i = 0
        var range = 0
        while (true) {
            val pairs = simulator.getCoordPairs(coordIdx)
            if (coordSet.containsKey(pairs)) {
                range = i - coordSet[pairs]!!
                break
            }

            coordSet[pairs] = i
            simulator.step(coordIdx)
            ++i
        }

        println("arr[$coordIdx] = (${i - range}, $range)")
    }

    // perform LCM directly on output, if start value (i - range) is 0
}
