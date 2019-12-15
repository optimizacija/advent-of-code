package day11.robotProgramConnector

import day11.intCode.IntCodeProgram
import day11.paintRobot.PaintRobot
import day11.paintRobot.PanelColor
import day11.paintRobot.TurnCommand

class RobotProgramConnector {
    private val program: IntCodeProgram
    private val robot: PaintRobot

    private var outputIndex = 0

    constructor(program: IntCodeProgram, robot: PaintRobot) {
        this.program = program
        this.robot = robot

        program.inputFun = { robot.getCurrentPanelColor().value.toBigInteger() }
        program.outputFun = {
            when (outputIndex) {
                0 -> robot.paint(PanelColor.fromInt(it.toInt()))
                1 -> robot.turnAdvance(TurnCommand.fromInt(it.toInt()))
                else -> error("invalid outputIndex: $outputIndex")
            }
            outputIndex = (outputIndex + 1) % 2
        }
    }
}
