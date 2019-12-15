package day11.PaintRobot

enum class TurnCommand(val value: Int) {
    TurnLeft(0),
    TurnRight(1);

    companion object {
        fun fromInt(value: Int): TurnCommand {
            return values().find { it.value == value }!!
        }
    }
}