package day13.screenRenderer

class ScreenRenderer {

    val tiles = mutableMapOf<Pair<Int, Int>, TileId>()

    fun setTile(position: Pair<Int, Int>, id: TileId) {
        tiles[position] = id
    }

    fun genImage(): Array<Array<Int>> {
        val x1 = tiles.map { it.key.first }.min()!!
        val x2 = tiles.map { it.key.first }.max()!!
        val y1 = tiles.map { it.key.second }.min()!!
        val y2 = tiles.map { it.key.second }.max()!!

        val dx = x2 - x1 + 1
        val dy = y2 - y1 + 1

        println("$x2 - $x1 = $dx")
        println("$y2 - $y1 = $dy")

        val result = Array(dy) {
            Array(dx) { 0 }
        }

        for (x in 0 until dx) {
            for (y in 0 until dy) {
                result[y][x] = (tiles[Pair(x + x1, y + y1)] ?: TileId.Empty).value
            }
        }

        return result
    }
}