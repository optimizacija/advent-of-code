package day13.connector

import day13.intCode.IntCodeProgram
import day13.screenRenderer.TileId
import day13.screenRenderer.ScreenRenderer

class Connector {
    private val program: IntCodeProgram
    private val renderer: ScreenRenderer

    private var outputIndex = 0
    private val vector = arrayOf(0, 0)
    private var ballX = 0
    private var paddleX = 0
    private var paddleDir = 0


    constructor(program: IntCodeProgram, renderer: ScreenRenderer) {
        this.program = program
        this.renderer = renderer

        program.inputFun = {
            paddleDir.toBigInteger()
        }
        program.outputFun = {
            when (outputIndex) {
                0 -> vector[0] = it.toInt()
                1 -> vector[1] = it.toInt()
                2 -> if (vector[0] == -1) println("score: $it")
                else {
                    val tileId = TileId.fromInt(it.toInt())
                    renderer.setTile(Pair(vector[0], vector[1]), tileId)
                    when (tileId) {
                        TileId.Ball -> {
                            ballX = vector[0]
                            paddleDir = (ballX - paddleX).coerceAtLeast(-1).coerceAtMost(1)
                        }
                        TileId.HorizontalPaddle -> {
                            paddleX = vector[0]
                            paddleDir = (ballX - paddleX).coerceAtLeast(-1).coerceAtMost(1)
                        }
                        else -> {
                        }
                    }
                }
                else -> error("invalid outputIndex: $outputIndex")
            }
            outputIndex = (outputIndex + 1) % 3
        }
    }
}

/*
input function =
{
val image = renderer.genImage()
day11.print(image)
println()
var command = -1000000
while (true) {
    try {
        print(">")
        command = readLine()!!.toInt()
        if (command >= -1 && command <= 1)
            break
    } catch (err: Throwable) {
    }
}
command
}
 */
