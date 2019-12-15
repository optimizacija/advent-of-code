package day12

class MoonForceSimulator {

    val moons = mutableListOf<Moon>()

    fun run(stepCount: Int) {
        for (i in 0 until stepCount) {
            step()
            // println("after ${i + 1} steps: ${totalEnergy()}")
        }
    }

    fun step() {
        calcVelocitiies()
        applyForces()
    }

    private fun calcVelocitiies() {
        for (i in moons.indices) {
            val moonI = moons[i]
            for (j in (i + 1) until moons.size) {
                val moonJ = moons[j]
                moonI.gravitateBoth(moonJ)
                // println("moon$i: ${moonI.velocity}, moon$j: ${moonJ.velocity}")
            }
        }

    }

    private fun applyForces() {
        for (moon in moons) {
            moon.applyVelocity()
        }
    }


    fun totalEnergy(): Int {
        return moons.sumBy { it.calcEnergy() }
    }
}