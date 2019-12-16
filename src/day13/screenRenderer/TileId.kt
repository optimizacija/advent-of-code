package day13.screenRenderer

enum class TileId(val value: Int) {
    Empty(0),
    Wall(1),
    Block(2),
    HorizontalPaddle(3),
    Ball(4);

    companion object {
        fun fromInt(value: Int): TileId {
            return values().find { it.value == value }!!
        }
    }
}
