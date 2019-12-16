package day12.part2

data class Moon(var position: Array<Int>, var velocity: Array<Int> = arrayOf(0, 0, 0)) {

    fun gravitateBothCoordIdx(other: Moon, coordIdx: Int) {
        if (position[coordIdx] > other.position[coordIdx]) {
            velocity[coordIdx] -= 1
            other.velocity[coordIdx] += 1
        } else if (position[coordIdx] < other.position[coordIdx]) {
            velocity[coordIdx] += 1
            other.velocity[coordIdx] -= 1
        }
    }

    fun applyVelocity(coordIdx: Int) {
        position[coordIdx] += velocity[coordIdx]
    }

    fun getCoordPair(coordIdx: Int): Pair<Int, Int> {
        return Pair(position[coordIdx], velocity[coordIdx])
    }
}