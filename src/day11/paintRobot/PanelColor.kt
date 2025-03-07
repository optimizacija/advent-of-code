package day11.paintRobot


enum class PanelColor(val value: Int) {
    Black(0),
    White(1);

    companion object {
        fun fromInt(value: Int): PanelColor {
            return values().find { it.value == value }!!
        }
    }
}
