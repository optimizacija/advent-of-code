package day11

import day11.intCode.IntCodeProgram
import day11.paintRobot.PaintRobot
import day11.robotProgramConnector.RobotProgramConnector

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
