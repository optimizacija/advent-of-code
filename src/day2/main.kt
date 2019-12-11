package day2

import java.lang.Exception


enum class IntOpCode(val value: Int) {
    Add(1),
    Mul(2),
    Halt(99)
}


class IntCodeProgram {
    private var instructionPointer = 0
    var intCode: IntArray
        private set

    constructor(code: IntArray) {
        intCode = code
    }

    private fun step() {
        instructionPointer += 4
    }

    fun run() {
        while (true) {
            val command = intCode[instructionPointer]
            when (command) {
                day5.IntOpCode.Halt.value -> return
                day5.IntOpCode.Add.value -> execAdd()
                day5.IntOpCode.Mul.value -> execMul()
                else -> throw Exception()
            }

            step()
        }
    }

    private fun execAdd() {
        execInstruction { val1, val2 -> val1 + val2 }
    }

    private fun execMul() {
        execInstruction { val1, val2 -> val1 * val2 }
    }

    private fun execInstruction(action: (Int, Int) -> Int) {
        // read
        val addr1 = intCode[instructionPointer + 1]
        val addr2 = intCode[instructionPointer + 2]
        val addr3 = intCode[instructionPointer + 3]

        val val1 = intCode[addr1]
        val val2 = intCode[addr2]

        // write
        intCode[addr3] = action(val1, val2)
    }

}

fun main() {

    val arr: IntArray = intArrayOf(
        1,
        12,
        2,
        3,
        1,
        1,
        2,
        3,
        1,
        3,
        4,
        3,
        1,
        5,
        0,
        3,
        2,
        13,
        1,
        19,
        1,
        5,
        19,
        23,
        2,
        10,
        23,
        27,
        1,
        27,
        5,
        31,
        2,
        9,
        31,
        35,
        1,
        35,
        5,
        39,
        2,
        6,
        39,
        43,
        1,
        43,
        5,
        47,
        2,
        47,
        10,
        51,
        2,
        51,
        6,
        55,
        1,
        5,
        55,
        59,
        2,
        10,
        59,
        63,
        1,
        63,
        6,
        67,
        2,
        67,
        6,
        71,
        1,
        71,
        5,
        75,
        1,
        13,
        75,
        79,
        1,
        6,
        79,
        83,
        2,
        83,
        13,
        87,
        1,
        87,
        6,
        91,
        1,
        10,
        91,
        95,
        1,
        95,
        9,
        99,
        2,
        99,
        13,
        103,
        1,
        103,
        6,
        107,
        2,
        107,
        6,
        111,
        1,
        111,
        2,
        115,
        1,
        115,
        13,
        0,
        99,
        2,
        0,
        14,
        0
    )

    val arrTest = intArrayOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50)


    for (noun in 0..99) {
        for (verb in 0..99) {
            val copyArr = arr.copyOf()
            copyArr[1] = noun
            copyArr[2] = verb
            val interpreter = IntCodeProgram(copyArr)
            interpreter.run()

            if (interpreter.intCode[0] == 19690720) {
                println(100 * noun + verb)
                return
            }
        }
    }
}