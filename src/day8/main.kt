package day8

import java.io.File

fun main() {
    val layers = readLayers()
    val leastZerosLayer = layers.minBy { it.count { it == '0' } }!!
    // val result = leastZerosLayer.count { it == '1' } * leastZerosLayer.count { it == '2' } // part 1

    val newlayer = CharArray(leastZerosLayer.length)
    for (i in leastZerosLayer.indices) {
        var value = '2'
        for(j in layers.indices) {
            if(layers[j][i] != '2')  {
                value = layers[j][i]
                break
            }
        }
        newlayer[i] = value
    }

    println(newlayer)
}

fun readLayers(): List<String> {
    return File("src/day8/data.txt").readLines()
}
