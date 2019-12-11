package day7

enum class IntOpCode(val value: Int, val length: Int) {
    Invalid(-1, -1),
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
            return values().find { it.value == value }!!
        }
    }
}

class IntCodeProgram {
    private var instructionPointer = 0
    var intCode: IntArray
        private set

    var inputFun: () -> Int
    var outputFun: (Int) -> Unit

    var opCode: IntOpCode = IntOpCode.Invalid
        private set
    private val modes = arrayOf(0, 0, 0)
    private val args = arrayOf(0, 0, 0)
    private var jumped = false


    constructor(code: IntArray, inputFunCallback: () -> Int, outputFunCallback: (Int) -> Unit) {
        intCode = code
        inputFun = inputFunCallback
        outputFun = outputFunCallback
    }

    fun run() {
        while (step()) {
        }
    }

    fun step(): Boolean {
        readModeCode()
        if (shouldHalt()) return false
        readArgs()
        executeCommand()
        updateInstructionPointer()
        return true
    }

    fun runToOpCode(intOpCode: IntOpCode): Boolean {
        // returns false if halted before getting to output
        do {
            if (!step()) return false
        } while (opCode != intOpCode)
        return true
    }

    fun runToOpCodes(intOpCodes: Array<IntOpCode>): Boolean {
        // returns false if halted before getting to output
        do {
            if (!step()) return false
        } while (intOpCodes.all { it != opCode })
        return true
    }

    fun runUpToOpCodes(intOpCodes: Array<IntOpCode>): Boolean {
        // returns false if halted before getting to output
        while (intOpCodes.all { it != getNextIntOpCode() }) {
            if (!step()) return false
        }
        return true
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

    fun getNextIntOpCode(): IntOpCode {
        return IntOpCode.fromInt(intCode[instructionPointer].toString().takeLast(2).toInt())
    }

    fun shouldHalt(): Boolean {
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

    private fun updateInstructionPointer() {
        if (jumped)
            jumped = false
        else
            instructionPointer += opCode.length
    }

    private fun executeCommand() {
        when (opCode) {
            IntOpCode.Invalid -> error("tried executing IntOpCode.Invalid")
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
    var totalThrust = 0
    lateinit var totalDigits: List<Int>

    for (i in 55555..99999) {
        val digits = intToDigits(i, 5)
        if (digits.any { it < 5 })
            continue

        if (digits.distinct().size != digits.size)
            continue

        // val thrust = getNormalThrustOutput(arr, digits) // part 1
        val thrust = getLoopBackTrustOutput(arr, digits) // part 2

        if (thrust > totalThrust) {
            totalThrust = thrust
            totalDigits = digits
        }
    }

    println(totalThrust)
    println(totalDigits)
    /*

    val thrust = getLoopBackTrustOutput(arr, listOf(9, 8, 7, 6, 5)) // part 2
    println("thrust $thrust")
     */
}

fun intToDigits(value: Int, digitCount: Int): List<Int> {
    return value.toString().padStart(digitCount, '0').toCharArray().map { it - '0' }
}

fun getLoopBackTrustOutput(data: IntArray, ampPhases: List<Int>): Int {
    val signals = intArrayOf(0, -1, -1, -1, -1)

    val interpreters = List(5) { index ->
        IntCodeProgram(
            data.clone(),
            { println("one ${ampPhases[index]}"); ampPhases[index] },
            { println("two $it"); signals[(index + 1) % 5] = it })
    }

    println("before")
    // init with amplitude phasees
    for (interpreter in interpreters) {
        interpreter.runToOpCode(IntOpCode.Input)
    }
    println("after")

    // change input function
    for (index in 0..4) {
        val interpreter = interpreters[index]
        interpreter.inputFun = {
            println("one ${signals[index]}");
            val signal = signals[index]
            signals[index] = -1
            signal
        }
    }

    while (!interpreters.last().shouldHalt()) {
        forLoop@ for (index in 0..4) {
            val interpreter = interpreters[index]
            if (!interpreter.runUpToOpCodes(arrayOf(IntOpCode.Input, IntOpCode.Output)))
                continue
            val nextCode = interpreter.getNextIntOpCode()
            when (nextCode) {
                IntOpCode.Input -> if (signals[index] == -1) continue@forLoop
                IntOpCode.Output -> if (signals[(index + 1) % 5] != -1) continue@forLoop
                else -> error("wrong IntOpCode $nextCode")
            }
            interpreter.runToOpCodes(arrayOf(IntOpCode.Input, IntOpCode.Output))
        }
    }
    println("juan")
    println(signals.contentToString())

    return signals[0]
}

fun getNormalThrustOutput(data: IntArray, ampPhases: List<Int>): Int {
    var signal = 0
    for (j in 0..4) {
        var phaseProvided = false
        val interpreter = IntCodeProgram(data.clone(), {
            if (!phaseProvided) {
                phaseProvided = true
                ampPhases[j]
            } else {
                signal
            }
        }, {
            signal = it
        })

        interpreter.run()
    }

    return signal
}
