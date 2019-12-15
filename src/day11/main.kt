package day11

import day11.IntCode.IntCodeProgram
import day11.PaintRobot.PaintRobot
import day11.RobotProgramConnector.RobotProgramConnector

fun main() {
    val program = IntCodeProgram(arr.clone())
    val robot = PaintRobot()
    val connector = RobotProgramConnector(program, robot)

    program.run()
    println(robot.visitedPanels.size)
    val image = robot.genPanelImage()
    print(image)
    println()
}
