package day13

import day13.intCode.IntCodeProgram
import day13.connector.Connector
import day13.screenRenderer.TileId
import day13.screenRenderer.ScreenRenderer

fun main() {
    val array = arr.clone()
    array[0] = 2.toBigInteger() // free gameplay

    val program = IntCodeProgram(array)
    val renderer = ScreenRenderer()
    val connector = Connector(program, renderer)

    program.run()
    println(renderer.tiles.size)
    println(renderer.tiles.count { (key, value) -> value == TileId.Block })
    /*
    val image = renderer.genImage()
    day11.print(image)
    println()
     */
}
