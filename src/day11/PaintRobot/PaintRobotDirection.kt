package day11.PaintRobot

enum class PaintRobotDirection(val id: Int, val vector: Pair<Int, Int>) {
    Up(0, Pair(0, 1)),
    Down(1, Pair(0, -1)),
    Left(2, Pair(-1, 0)),
    Right(3, Pair(1, 0));

    fun left(): PaintRobotDirection {
        return when (this) {
            Up -> Left
            Down -> Right
            Left -> Down
            Right -> Up
        }
    }

    fun right(): PaintRobotDirection {
        return when (this) {
            Up -> Right
            Down -> Left
            Left -> Up
            Right -> Down
        }
    }
}