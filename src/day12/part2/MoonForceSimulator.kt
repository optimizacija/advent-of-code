package day12.part2

class MoonForceSimulator {

    val moons = mutableListOf<Moon>()

    fun step(coordIdx: Int) {
        calcVelocitiies(coordIdx)
        applyForces(coordIdx)
    }

    private fun calcVelocitiies(coordIdx: Int) {
        for (i in moons.indices) {
            val moonI = moons[i]
            for (j in (i + 1) until moons.size) {
                val moonJ = moons[j]
                moonI.gravitateBothCoordIdx(moonJ, coordIdx)
            }
        }

    }

    private fun applyForces(coordIdx: Int) {
        for (moon in moons) {
            moon.applyVelocity(coordIdx)
        }
    }

    fun getCoordPairs(coordIdx: Int): List<Pair<Int, Int>> {
        return moons.map { it.getCoordPair(coordIdx) }
    }

}