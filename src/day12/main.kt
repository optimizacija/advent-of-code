package day12

fun main() {
    val simulator = MoonForceSimulator()
    val moons = arr0.map { Moon(it) }
    simulator.moons.addAll(moons.map { it.copy() })

    simulator.run(1000)
    println(simulator.totalEnergy())


}