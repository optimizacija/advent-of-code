package day11.paintRobot

import day11.plus

class PaintRobot {

    val visitedPanels = mutableMapOf(
        Pair(Pair(0, 0), PanelColor.White) // PART 1 - change to Black
    )
    private var position = Pair(0, 0)
    private var direction = PaintRobotDirection.Up

    fun getCurrentPanelColor(): PanelColor {
        return visitedPanels[position] ?: PanelColor.Black
    }

    fun getCurrentPosition(): Pair<Int, Int> {
        return position
    }

    fun paint(color: PanelColor) {
        visitedPanels[position] = color
    }

    fun turnAdvance(command: TurnCommand) {
        when (command) {
            TurnCommand.TurnLeft -> turnLeft()
            TurnCommand.TurnRight -> turnRight()
        }
        advance()
    }

    private fun turnRight() {
        direction = direction.right()
    }

    private fun turnLeft() {
        direction = direction.left()
    }

    private fun advance() {
        position += direction.vector
    }

    fun genPanelImage(): Array<Array<Int>> {
        val x1 = visitedPanels.map { it.key.first }.min()!!
        val x2 = visitedPanels.map { it.key.first }.max()!!
        val y1 = visitedPanels.map { it.key.second }.min()!!
        val y2 = visitedPanels.map { it.key.second }.max()!! +1

        val dx = x2 - x1
        val dy = y2 - y1

        // println("$x2 - $x1 = $dx")
        // println("$y2 - $y1 = $dy")

        val result = Array(dy) {
            Array(dx) { 0 }
        }

        for (x in 0 until dx) {
            for (y in 0 until dy) {
                result[y][x] = (visitedPanels[Pair(x + x1, y + y1)] ?: PanelColor.Black).value
            }
        }

        return result
    }
}