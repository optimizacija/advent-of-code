package day9

fun main() {
    val program = IntCodeProgram(arr.clone(), { print("> "); readLine()!!.toBigInteger() }, { println(it) })
    program.run()
    println(program.intCode)
}

