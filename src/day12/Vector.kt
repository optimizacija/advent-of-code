package day12

data class Vector(var x: Int = 0, var y: Int = 0, var z: Int = 0) {

    operator fun minus(other: Vector): Vector {
        return Vector(x - other.x, y - other.y, z - other.z)
    }

    operator fun plus(other: Vector): Vector {
        return Vector(x + other.x, y + other.y, z + other.z)
    }

    fun energy(): Int {
        return abs(x) + abs(y) + abs(z)
    }

    private fun abs(value: Int): Int {
        return if (value < 0) -value else value
    }

}