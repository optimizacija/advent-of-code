package day5

enum class IntOpCode(val value: Int, val length: Int) {
    Add(1, 4),
    Mul(2, 4),
    Input(3, 2),
    Output(4, 2),
    JumpNonZero(5, 3),
    JumpZero(6, 3),
    LessThan(7, 4),
    Equals(8, 4),
    Halt(99, 1);

    companion object {
        fun fromInt(value: Int): IntOpCode {
            return IntOpCode.values().find { it.value == value }!!
        }
    }
}

class IntCodeProgram {
    private var instructionPointer = 0
    var intCode: IntArray
        private set

    private val inputFun: () -> Int
    private val outputFun: (Int) -> Unit

    private var opCode: IntOpCode = IntOpCode.Halt
    private val modes = arrayOf(0, 0, 0)
    private val args = arrayOf(0, 0, 0)
    private var jumped = false


    constructor(code: IntArray, inputFunCallback: () -> Int, outputFunCallback: (Int) -> Unit) {
        intCode = code
        inputFun = inputFunCallback
        outputFun = outputFunCallback
    }

    fun run() {
        while (true) {
            readModeCode()
            if (shouldHalt()) return
            readArgs()
            executeCommand()
            tryStep()
        }
    }

    private fun readModeCode() {
        // read op code
        val num = intCode[instructionPointer]
        val str = num.toString()
        opCode = IntOpCode.fromInt(str.takeLast(2).toInt())

        // read arg modes
        val modeStr = str.dropLast(2).padStart(opCode.length - 1, '0')
        val modeDigits = modeStr.toCharArray().map { it - '0' }.reversed()
        for (i in 0..(modeDigits.size - 1)) {
            modes[i] = modeDigits[i]
        }
    }

    private fun shouldHalt(): Boolean {
        return opCode == IntOpCode.Halt
    }


    private fun readArgs() {
        for (i in 0..(opCode.length - 2)) {
            args[i] = intCode[instructionPointer + i + 1]
        }
    }

    private fun read(argIndex: Int): Int {
        when (modes[argIndex]) {
            // position
            0 -> return intCode[args[argIndex]]
            // immediate
            1 -> return args[argIndex]
            else -> throw Exception("Invalid mode")
        }
    }

    private fun write(value: Int) {
        intCode[args[opCode.length - 2]] = value
    }

    private fun tryStep() {
        if (jumped)
            jumped = false
        else
            instructionPointer += opCode.length

        for (i in 0..2) {
            args[i] = -1000000
            modes[i] = -1000000
        }
    }

    private fun executeCommand() {
        when (opCode) {
            IntOpCode.Add -> execAdd()
            IntOpCode.Mul -> execMul()
            IntOpCode.Input -> execInput()
            IntOpCode.Output -> execOutput()
            IntOpCode.JumpNonZero -> execJumpNonZero()
            IntOpCode.JumpZero -> execJumpZero()
            IntOpCode.LessThan -> execLessThan()
            IntOpCode.Equals -> execEquals()
            IntOpCode.Halt -> return
        }
    }

    private fun execAdd() {
        val val1 = read(0)
        val val2 = read(1)

        write(val1 + val2)
    }

    private fun execMul() {
        val val1 = read(0)
        val val2 = read(1)

        write(val1 * val2)
    }

    private fun execInput() {
        write(inputFun())
    }

    private fun execOutput() {
        outputFun(read(0))
    }

    private fun execJumpNonZero() {
        val val1 = read(0)

        if (val1 != 0) {
            instructionPointer = read(1)
            jumped = true
        }
    }

    private fun execJumpZero() {
        val val1 = read(0)

        if (val1 == 0) {
            instructionPointer = read(1)
            jumped = true
        }
    }

    private fun execLessThan() {
        val val1 = read(0)
        val val2 = read(1)

        if (val1 < val2) {
            write(1)
        } else {
            write(0)
        }
    }

    private fun execEquals() {
        val val1 = read(0)
        val val2 = read(1)

        if (val1 == val2) {
            write(1)
        } else {
            write(0)
        }
    }
}

fun main() {
    val interpreter = IntCodeProgram(arr0.clone(),
        {
            5
            // readLine()!!.toInt()
        },
        { println(it) })
    interpreter.run()
    // println(interpreter.intCode.map { it.toString() })
}