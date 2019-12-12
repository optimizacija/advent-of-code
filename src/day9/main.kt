package day9

import java.math.BigInteger

fun <T> MutableList<T>.clone(): MutableList<T> {
    val mutableList = mutableListOf<T>()
    mutableList.addAll(this)
    return mutableList
}

// only for when int >= 0
fun BigInteger.toDigits(digitCount: Int): List<Int> {
    return this.toString().padStart(digitCount, '0').toCharArray().map { it - '0' }
}

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
    AdjustRelativeBase(9, 2),
    Halt(99, 1);

    companion object {
        fun fromInt(value: Int): IntOpCode {
            return values().find { it.value == value }!!
        }
    }
}

class IntCodeProgram {
    var intCode: MutableList<BigInteger>
        private set

    var inputFun: () -> BigInteger
    var outputFun: (BigInteger) -> Unit

    private var opCode: IntOpCode = IntOpCode.Invalid
    private val modes = arrayOf(0, 0, 0)
    private val args = arrayOf(0, 0, 0).map { it.toBigInteger() }.toMutableList()
    private var jumped = false
    private var instructionPointer = 0
    private var relativeBase = 0


    constructor(
        code: MutableList<BigInteger>,
        inputFunCallback: () -> BigInteger,
        outputFunCallback: (BigInteger) -> Unit
    ) {
        intCode = MutableList(100000) { 0.toBigInteger() }
        for (i in code.indices) {
            intCode[i] = code[i]
        }
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

    fun runUpToOpCode(intOpCode: IntOpCode): Boolean {
        // returns false if halted before getting to output
        while (intOpCode != getNextIntOpCode()) {
            if (!step()) return false
        }
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
        val digits = intCode[instructionPointer].toDigits(5)

        // opCode
        val opDigits = digits.takeLast(2)
        opCode = IntOpCode.fromInt(opDigits[0] * 10 + opDigits[1])

        // read arg modes
        val modeDigits = digits.dropLast(2).reversed()
        for (i in modeDigits.indices) {
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

    private fun read(argIndex: Int): BigInteger {
        when (modes[argIndex]) {
            // position
            0 -> return intCode[args[argIndex].toInt()]
            // immediate
            1 -> return args[argIndex]
            // relative
            2 -> return intCode[relativeBase + args[argIndex].toInt()]
            else -> throw Exception("Invalid mode")
        }
    }

    private fun write(value: BigInteger) {
        intCode[args[opCode.length - 2].toInt()] = value
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
            IntOpCode.AdjustRelativeBase -> execAdjustRelativeBase()
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

        if (val1 != 0.toBigInteger()) {
            instructionPointer = read(1).toInt()
            jumped = true
        }
    }

    private fun execJumpZero() {
        val val1 = read(0)

        if (val1 == 0.toBigInteger()) {
            instructionPointer = read(1).toInt()
            jumped = true
        }
    }

    private fun execLessThan() {
        val val1 = read(0)
        val val2 = read(1)

        if (val1 < val2) {
            write(1.toBigInteger())
        } else {
            write(0.toBigInteger())
        }
    }

    private fun execEquals() {
        val val1 = read(0)
        val val2 = read(1)

        if (val1 == val2) {
            write(1.toBigInteger())
        } else {
            write(0.toBigInteger())
        }
    }

    private fun execAdjustRelativeBase() {
        relativeBase += read(0).toInt()
    }

}

fun main() {

    // arr0.filter { it >= 0 }.map { it.toDigits(10) }.forEach { println(it) }

    val program = IntCodeProgram(arr.clone(), { print("input: ");readLine()!!.toBigInteger() }, { println(it) })
    program.run()
    println(program.intCode)
}

