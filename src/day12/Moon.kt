package day12

data class Moon(var position: Vector, var velocity: Vector = Vector()) {

    fun gravitateBoth(other: Moon) {
        // x
        if (position.x > other.position.x) {
            velocity.x -= 1
            other.velocity.x += 1
        } else if (position.x < other.position.x) {
            velocity.x += 1
            other.velocity.x -= 1
        }
        // y
        if (position.y > other.position.y) {
            velocity.y -= 1
            other.velocity.y += 1
        } else if (position.y < other.position.y) {
            velocity.y += 1
            other.velocity.y -= 1
        }
        // z
        if (position.z > other.position.z) {
            velocity.z -= 1
            other.velocity.z += 1
        } else if (position.z < other.position.z) {
            velocity.z += 1
            other.velocity.z -= 1
        }
    }

    fun applyVelocity() {
        position += velocity
    }

    fun calcEnergy(): Int {
        return position.energy() * velocity.energy()
    }
}